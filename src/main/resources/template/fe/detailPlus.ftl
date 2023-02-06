<!--
 * ${generateInfo.tableComment}详情
 * @Author: ${generateInfo.author}
 * @Date: ${generateInfo.date}
-->
<template>
  <div class="app-container">
    <el-card>
      <el-descriptions>
<#list generateInfo.columnList as column>
  <#if "${column.columnCamelName}"?matches("deleted|isDel|isDelete|isDeleted")>
  <#elseif "${column.columnCamelName}"?ends_with("ed")>
        <el-descriptions-item label="${column.columnComment}">{{ dict.type.whether[detailData.${column.columnCamelName}] || "-" }}</el-descriptions-item>
  <#elseif "${column.columnCamelName}"?ends_with("able")>
        <el-descriptions-item label="${column.columnComment}">{{ dict.type.whether[detailData.${column.columnCamelName}] || "-" }}</el-descriptions-item>
  <#elseif "${column.columnCamelName}"?matches(".*?(status|Status|type|Type|strategy|Strategy|pattern|Pattern).*")>
        <el-descriptions-item label="${column.columnComment}">{{ dict.type.${column.columnCamelName}[detailData.${column.columnCamelName}] || "-" }}</el-descriptions-item>
  <#elseif "${column.columnCamelName}"?matches(".*?(img|Ima|image|Image|photo|Photo).*")>
        <el-descriptions-item label="${column.columnComment}">
          <el-image
                  style="width: 100px; height: 100px"
                  :src="detailData.${column.columnCamelName}"
                  fit="contain"></el-image>
        </el-descriptions-item>
  <#else>
        <el-descriptions-item label="${column.columnComment}">{{ detailData.${column.columnCamelName} }}</el-descriptions-item>
  </#if>
</#list>
      </el-descriptions>
    </el-card>
    <el-card style="margin-top: 20px;">
      <el-tabs v-model="activeName" @tab-click="handleTabClick">
        <el-tab-pane label="用户管理" name="1">
          <el-table v-loading="listLoading" :data="listData" max-height="480" element-loading-text="Loading" fit highlight-current-row>
            <el-table-column align="center" prop="id" label="ID" />
            <el-table-column align="center" prop="Name" label="名称" />
            <el-table-column align="center" prop="createTime" label="数据创建时间" />
            <el-table-column align="center" label="操作" width="200">
              <template>
                <el-button type="primary" size="mini">编辑</el-button>
                <el-button type="danger" size="mini">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!-- 分页 -->
          <div class="footer">
            <Paging v-if="listData && listData.length" :page="page" @current-change="mixOnPageIndexChange"
                    @size-change="mixOnPageSizeChange" />
          </div>
        </el-tab-pane>
        <el-tab-pane label="配置管理" name="2">配置管理</el-tab-pane>
        <el-tab-pane label="角色管理" name="3">角色管理</el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
  import paging from '@/mixins/paging'
  import request from '@/api/axios'
  export default {
    dicts: ['whether', <#list generateInfo.columnList as column><#if "${column.columnCamelName}"?matches(".*?(status|Status|type|Type|strategy|Strategy|pattern|Pattern).*")>'${column.columnCamelName}', </#if></#list>],
    mixins: [paging],
    data() {
      return {
        activeName: '1',
        detailData: {},
        listLoading: false,
        listData: []
      }
    },
    created() {
      this.getTableListData(1)
      this.get${generateInfo.moduleNameUppercaseCamel}Detail()
    },
    methods: {
      // 获取列表数据
      async getTableListData(num) {
        if (num) {
          this.page.pageNum = num
        }
        this.listLoading = true
        const params = {
          pageNum: this.page.pageNum,
          pageSize: this.page.pageSize
        }
        const res = await request({
          url: '${generateInfo.moduleNameLowercase}',
          method: 'get',
          params
        })
        if (res.status === 200) {
          this.listData = res.data.list
          this.page.total = res.data.totalItems
        }
        this.listLoading = false
      },
      // 获取${generateInfo.tableComment}信息数据
      async get${generateInfo.moduleNameUppercaseCamel}Detail() {
        // 拿到路由上传递的id参数 通过id 请求详情接口
        let ${generateInfo.primaryKeyLowerCamel} = this.$route.params.${generateInfo.primaryKeyLowerCamel}
        const res = await request({
          url: `${generateInfo.moduleNameWithSlash}/<#noparse>${</#noparse>${generateInfo.primaryKeyLowerCamel}}`,
          method: 'get'
        })
        if (res.status === 200 && res.data) {
          this.detailData = res.data
        }
        //
      },
      // 切换tabs 回调方法
      handleTabClick(tab) {
        console.log(tab.name);
      }
    }
  }
</script>

<style>

</style>
