<!--
 * ${generateInfo.tableComment}添加
 * @Author: ${generateInfo.author}
 * @Date: ${generateInfo.date}
-->
<template>
  <el-drawer title="新建${generateInfo.tableComment}" :visible.sync="drawer" direction="rtl" :size="500" :before-close="handleClose">
    <el-alert title="新建提示" type="warning" :closable="false" show-icon />
    <el-form :model="formData" :rules="rules" ref="formData" label-width="100px" style="padding: 20px 40px 20px 20px;">
      <h3>基本信息</h3>
<#list generateInfo.columnList as column>
  <#if generateInfo.primaryKey == column.columnName || "${column.columnCamelName}"?matches("deleted|createUserName|createUserId|createTime|updateUserName|updateUserId|updateTime")>
  <#elseif "${column.columnCamelName}"?matches("(can|allow).*")>
      <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
        <el-switch v-model="formData.${column.columnCamelName}" :active-value="1" :inactive-value="0"></el-switch>
      </el-form-item>
  <#elseif "${column.columnCamelName}"?matches(".*?(ed|able)")>
      <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
        <el-switch v-model="formData.${column.columnCamelName}" :active-value="1" :inactive-value="0"></el-switch>
      </el-form-item>
  <#elseif "${column.columnCamelName}"?matches(".*?(Id)")>
      <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
        <el-input v-model="formData.${column.columnCamelName}" />
        <!--
        <el-select v-model="formData.${column.columnCamelName}" placeholder="请输入关键词" filterable clearable
            remote :remote-method="${column.columnCamelName}DictRemote">
          <el-option
            v-for="item in ${column.columnCamelName}Dict"
            :key="item.code"
            :label="item.name"
            :value="item.code">
          </el-option>
        </el-select>
        -->
      </el-form-item>
  <#elseif "${column.columnCamelName}"?matches(".*?(Date|Time).*")>
      <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
        <el-date-picker v-model="formData.${column.columnCamelName}" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss" type="datetime"
          placeholder="选择日期时间" />
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
        <el-input v-model="formData.${column.columnCamelName}"></el-input>
      </el-form-item>
  </#if>
</#list>

      <el-form-item>
        <el-button type="primary" :loading="btnLoading" @click="submitForm()">立即创建</el-button>
        <el-button @click="resetForm()">重置</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<script>
import request from '@/api/axios'
export default {
  dicts: ['whether', <#list generateInfo.columnList as column><#if "${column.columnCamelName}"?matches(".*?(status|Status|type|Type|strategy|Strategy|pattern|Pattern).*")>'${column.columnCamelName}', </#if></#list>],
  props: {
    drawer: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      formData: {},
      rules: {
<#list generateInfo.columnList as column>
  <#if generateInfo.primaryKey == column.columnName || "${column.columnCamelName}"?matches("deleted|createUserName|createUserId|createTime|updateUserName|updateUserId|updateTime")>
  <#elseif "${column.nullable}" =="NO">
        ${column.columnCamelName}: [
          { required: true, message: '请输入${column.columnComment}', trigger: 'blur' },
    <#if column.columnJavaTypeName == 'String'>
          { min: 1, max: ${column.characterMaximumLength}, message: '长度在 1 到 ${column.characterMaximumLength} 个字符', trigger: 'blur' }
    </#if>
        ],
  </#if>
</#list>
      },
      btnLoading: false
    }
  },
  methods: {
    handleClose() {
      this.$emit('update:drawer', false)
    },
    submitForm() {
      this.btnLoading = true
      this.$refs['formData'].validate(async (valid) => {
        if (valid) {
          try {
            const res = await request({
              url: `${generateInfo.moduleNameWithSlash}`,
              method: 'post',
              data: this.formData
            })
            if (res.status === 200) {
              this.$message({
                type: 'success',
                message: '添加成功!'
              })
              this.$emit('success')
              this.$emit('update:drawer', false)
            }
          } catch (error) {
            console.log(error);
          }
        }
        this.btnLoading = false
      });
    },
    resetForm() {
      this.$refs['formData'].resetFields();
    }
  }
}
</script>

<style>

</style>
