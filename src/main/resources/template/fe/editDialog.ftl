<!--
 * ${generateInfo.tableComment}编辑
 * @Author: ${generateInfo.author}
 * @Date: ${generateInfo.date}
-->
<template>
  <el-dialog title="编辑" :visible.sync="visible" append-to-body top="50px" width="800px" :before-close="handleClose">
    <el-form ref="formData" :model="formData" :rules="rules" label-width="120px">
<#list generateInfo.columnList as column>
  <#if generateInfo.primaryKey == column.columnName || "${column.columnCamelName}"?matches("deleted|isDel|isDelete|isDeleted|createUserName|createUserId|createTime|updateUserName|updateUserId|updateTime")>
  <#elseif "${column.columnCamelName}"?ends_with("ed")>
      <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
        <el-radio-group v-model="formData.${column.columnCamelName}">
          <el-radio :label="0">否</el-radio>
          <el-radio :label="1">是</el-radio>
        </el-radio-group>
      </el-form-item>
  <#elseif "${column.columnCamelName}"?ends_with("able")>
      <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
        <el-radio-group v-model="formData.${column.columnCamelName}">
          <el-radio :label="0">否</el-radio>
          <el-radio :label="1">是</el-radio>
        </el-radio-group>
      </el-form-item>
  <#elseif "${column.columnCamelName}"?matches(".*?(Id)")>
      <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
        <el-input v-model="formData.${column.columnCamelName}" />
        <!--
        <el-select v-model="formData.${column.columnCamelName}" multiple filterable
           remote reserve-keyword  placeholder="请输入关键词"
           :remote-method="remoteMethod" :loading="true">
            <el-option
              v-for="item in ${column.columnCamelName}Dict"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
        </el-select>
        -->
      </el-form-item>
  <#elseif "${column.columnCamelName}"?ends_with("Date")>
      <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
        <el-date-picker v-model="formData.${column.columnCamelName}" type="date" value-format="yyyy-MM-dd" placeholder="选择日期" />
      </el-form-item>
  <#elseif "${column.columnCamelName}"?ends_with("Time")>
      <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
        <el-date-picker v-model="formData.${column.columnCamelName}" type="date" value-format="yyyy-MM-dd" placeholder="选择日期" />
      </el-form-item>
  <#elseif "${column.columnCamelName}"?matches(".*?(status|Status|type|Type|strategy|Strategy|pattern|Pattern).*")>
    <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
      <el-select v-model="formData.${column.columnCamelName}" placeholder="请选择${column.columnComment}" filterable clearable>
        <el-option v-for="item in ${column.columnCamelName}Dict"
                   :key="item.code"
                   :label="item.name"
                   :value="item.code">
        </el-option>
        <el-option v-for="(name, code) in dict.type.${column.columnCamelName}" :key="code" :label="name" :value="code"></el-option>
      </el-select>
    </el-form-item>
  <#elseif "${column.columnJavaTypeName}"?matches("Integer")>
      <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
        <el-input-number v-model="formData.${column.columnCamelName}" :min="1" :max="1000000" :precision="0" label="${column.columnComment}"></el-input-number>
      </el-form-item>
  <#elseif "${column.columnJavaTypeName}"?matches("BigDecimal")>
      <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
        <el-input-number v-model="formData.${column.columnCamelName}" :min="1" :max="1000000" label="${column.columnComment}"></el-input-number>
      </el-form-item>
  <#else>
      <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
        <el-input v-model="formData.${column.columnCamelName}" />
      </el-form-item>
  </#if>
</#list>
      <el-form-item>
        <el-button type="primary" :loading="btnLoading" @click="handleSubmit">保存</el-button>
        <el-button @click="handleClose">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import request from '@/api/axios'
export default {
  dicts: ['whether', <#list generateInfo.columnList as column><#if "${column.columnCamelName}"?matches(".*?(status|Status|type|Type|strategy|Strategy|pattern|Pattern).*")>'${column.columnCamelName}', </#if></#list>],
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    ${generateInfo.primaryKeyLowerCamel}: {
      type: Number,
      default: null
    }
  },
  data() {
    return {
      btnLoading: false,
      formData: {},
      rules: {
<#list generateInfo.columnList as column>
  <#if generateInfo.primaryKey == column.columnCamelName || "${column.columnCamelName}"?matches("deleted|isDel|isDelete|isDeleted|createUserName|createUserId|createTime|updateUserName|updateUserId|updateTime")>
  <#elseif "${column.nullable}" =="NO">
        ${column.columnCamelName}: [
          { required: true, message: '请输入${column.columnComment}', trigger: 'blur' },
    <#if column.columnJavaTypeName == 'String'>
          { min: 1, max: ${column.characterMaximumLength}, message: '长度在 1 到 ${column.characterMaximumLength} 个字符', trigger: 'blur' }
    </#if>
        ],
  </#if>
</#list>
      }
    }
  },
  created() {
    request({
      url: `${generateInfo.moduleNameWithSlash}/<#noparse>${this.</#noparse>${generateInfo.primaryKeyLowerCamel}}`,
      method: 'get'
    })
    .then((res) => {
      this.formData = res.data
    })
  },
  methods: {
    // 取消
    handleClose() {
      this.$refs['formData'].resetFields()
      this.$emit('update:visible', false)
    },
    // 保存
    handleSubmit() {
      this.btnLoading = true
      this.$refs['formData'].validate(async(valid) => {
        if (valid) {
          try {
            const res = await request({
              url: '${generateInfo.moduleNameWithSlash}',
              method: 'put',
              data: this.formData
            })
            if (res.status === 200) {
              this.$message.success('修改成功')
              this.$emit('success')
              this.$emit('update:visible', false)
            }
          } catch (error) {
            console.log(error);
          }
        }
        this.btnLoading = false
      })
    }
  }
}
</script>
