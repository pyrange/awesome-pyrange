<!--
 * ${generateInfo.tableComment}详情
 * @Author: ${generateInfo.author}
 * @Date: ${generateInfo.date}
-->
<template>
  <el-dialog title="详情" :visible.sync="visible" append-to-body top="50px" width="800px" :before-close="handleClose">
    <el-descriptions>
<#list generateInfo.columnList as column>
  <#if "${column.columnCamelName}"?matches("deleted|isDel|isDelete|isDeleted")>
  <#elseif "${column.columnCamelName}"?ends_with("ed")>
      <el-descriptions-item label="${column.columnComment}">{{ dict.type.whether[data.${column.columnCamelName}] || "-" }}</el-descriptions-item>
  <#elseif "${column.columnCamelName}"?ends_with("able")>
      <el-descriptions-item label="${column.columnComment}">{{ dict.type.whether[data.${column.columnCamelName}] || "-" }}</el-descriptions-item>
  <#elseif "${column.columnCamelName}"?matches(".*?(status|Status|type|Type|strategy|Strategy|pattern|Pattern).*")>
      <el-descriptions-item label="${column.columnComment}">{{ dict.type.${column.columnCamelName}[data.${column.columnCamelName}] || "-" }}</el-descriptions-item>
  <#elseif "${column.columnCamelName}"?matches(".*?(img|Ima|image|Image|photo|Photo).*")>
      <el-descriptions-item label="${column.columnComment}">
        <el-image
                style="width: 100px; height: 100px"
                :src="data.${column.columnCamelName}"
                fit="contain"></el-image>
      </el-descriptions-item>
  <#else>
      <el-descriptions-item label="${column.columnComment}">{{ data.${column.columnCamelName} }}</el-descriptions-item>
  </#if>
</#list>
    </el-descriptions>
  </el-dialog>
</template>

<script>
export default {
  dicts: ['whether', <#list generateInfo.columnList as column><#if "${column.columnCamelName}"?matches(".*?(status|Status|type|Type|strategy|Strategy|pattern|Pattern).*")>'${column.columnCamelName}', </#if></#list>],
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
