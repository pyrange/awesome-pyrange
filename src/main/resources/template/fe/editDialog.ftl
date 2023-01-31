<template>
  <el-dialog title="编辑" :visible.sync="visible" append-to-body top="50px" width="800px" :before-close="handleClose">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="120px">
<#list generateInfo.columnList as column>
  <#if "${column.columnCamelName}"?ends_with("ed")>
      <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
        <el-radio-group v-model="ruleForm.${column.columnCamelName}">
          <el-radio :label="0">否</el-radio>
          <el-radio :label="1">是</el-radio>
        </el-radio-group>
      </el-form-item>
  <#elseif "${column.columnCamelName}"?ends_with("able")>
    <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
      <el-radio-group v-model="ruleForm.${column.columnCamelName}">
        <el-radio :label="0">否</el-radio>
        <el-radio :label="1">是</el-radio>
      </el-radio-group>
    </el-form-item>
  <#elseif "${column.columnCamelName}"?ends_with("date")>
    <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
      <el-date-picker v-model="ruleForm.${column.columnCamelName}" type="date" value-format="yyyy-MM-dd" placeholder="选择日期" />
    </el-form-item>
  <#elseif "${column.columnCamelName}"?ends_with("time")>
    <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
      <el-date-picker v-model="ruleForm.${column.columnCamelName}" type="date" value-format="yyyy-MM-dd" placeholder="选择日期" />
    </el-form-item>
  <#elseif "${column.columnCamelName}"?matches(".*?(Status|Type|Strategy).*")>
    <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
      <el-select v-model="ruleForm.${column.columnCamelName}" placeholder="请选择">
        <el-option label="" value="全部" />
        <el-option :label="0" value="开启" />
        <el-option :label="1" value="关闭" />
      </el-select>
    </el-form-item>
  <#else>
    <el-form-item label="${column.columnComment}" prop="${column.columnCamelName}">
      <el-input v-model="ruleForm.${column.columnCamelName}" />
    </el-form-item>
  </#if>
</#list>
      <el-form-item>
        <el-button type="primary" :loading="btnLoading" @click="handleSubmit">保存</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import request from '@/api/axios'
export default {
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    data: {
      type: Object,
      default() {
        return {}
      }
    }
  },
  data() {
    return {
      btnLoading: false,
      ruleForm: {},
      rules: {
<#list generateInfo.columnList as column>
  <#if "${column.nullable}" =="NO">
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
    this.ruleForm = Object.assign({}, this.data)
  },
  methods: {
    // 取消
    handleClose() {
      this.$refs['ruleForm'].resetFields()
      this.$emit('update:visible', false)
    },
    // 保存
    handleSubmit() {
      this.btnLoading = true
      this.$refs['ruleForm'].validate(async(valid) => {
        if (valid) {
          const data = this.ruleForm
          const res = await request({
            url: '${generateInfo.moduleNameWithSlash}',
            method: 'put',
            data
          })
          if (res.status === 200) {
            this.$message.success('修改成功')
            this.$emit('success')
          }
        } else {
          this.btnLoading = false
        }
      })
    }
  }
}
</script>
