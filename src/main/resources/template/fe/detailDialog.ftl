<template>
  <el-dialog title="详情" :visible.sync="visible" append-to-body top="50px" width="800px" :before-close="handleClose">
    <el-descriptions>
<#list generateInfo.columnList as column>
  <#if "${column.columnCamelName}"?matches("deleted")>
  <#elseif "${column.columnCamelName}"?ends_with("ed")>
      <el-descriptions-item label="${column.columnComment}">{{ data.${column.columnCamelName} | dictFilter('whether') }}</el-descriptions-item>
  <#elseif "${column.columnCamelName}"?ends_with("able")>
      <el-descriptions-item label="${column.columnComment}">{{ data.${column.columnCamelName} | dictFilter('whether') }}</el-descriptions-item>
  <#elseif "${column.columnCamelName}"?matches(".*?(Status|Type|Strategy).*")>
      <el-descriptions-item label="${column.columnComment}">{{ data.${column.columnCamelName} | dictFilter('${column.columnCamelName}') }}</el-descriptions-item>
  <#elseif "${column.columnCamelName}"?matches("deleted")>
  <#else>
      <el-descriptions-item label="${column.columnComment}">{{ data.${column.columnCamelName} }}</el-descriptions-item>
  </#if>
</#list>
    </el-descriptions>
  </el-dialog>
</template>

<script>
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
  created() {
  },
  methods: {
    handleClose() {
      this.$emit('update:visible', false)
    }
  }
}
</script>
