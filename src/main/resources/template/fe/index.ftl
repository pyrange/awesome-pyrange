<template>
  <div class="app-container">
    <!-- 搜索 -->
    <div class="search">
      <div class="search-item">
        <el-input v-model="keyword" size="small" clearable suffix-icon="el-icon-search"
          style="width: auto;" placeholder="输入关键字搜索" @blur="onKeywordChange"@keyup.enter.native="$event.target.blur()"/>
      </div>
    </div>
    <!-- 列表 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" fit highlight-current-row>
<#list generateInfo.columnList as column>
      <el-table-column align="center" prop="${column.columnCamelName}" label="${column.columnComment}">
  <#if "${column.columnCamelName}"?ends_with("ed")>
        <template slot-scope="{row}">
          {{ row.${column.columnCamelName} | dictFilter('whether') }}
        </template>
  <#elseif "${column.columnCamelName}"?matches(".*?(Status|Type|Strategy).*")>
        <template slot-scope="{row}">
          {{ row.${column.columnCamelName} | dictFilter('${column.columnCamelName}') }}
        </template>
  </#if>
      </el-table-column>
</#list>
      <el-table-column align="center" label="操作" width="300">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="handleDetail(row)">查看</el-button>
          <el-button type="primary" size="mini" @click="handleEdit(row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDel(row.${generateInfo.primaryKeyCamel})">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="footer">
      <Paging
              v-if="listData && listData.length"
              :page="page"
              @current-change="mixOnPageIndexChange"
              @size-change="mixOnPageSizeChange"
      />
    </div>
    <!-- 详情弹窗 -->
    <DetailDialog v-if="detailDialogData.visible" :visible.sync="detailDialogData.visible" :data="detailDialogData.detail" />
    <!-- 编辑弹窗 -->
    <EditDialog v-if="editDialogData.visible" :visible.sync="editDialogData.visible" :data="editDialogData.detail" @success="getTableListData" />
  </div>
</template>


<script>
  import paging from '@/mixins/paging'
  import request from '@/api/axios'
  import DetailDialog from './component/${generateInfo.moduleName}DetailDialog.vue'
  import EditDialog from './component/${generateInfo.moduleName}EditDialog.vue'
  export default {
    components: {
      DetailDialog,
      EditDialog
    },
    mixins: [paging],
    data() {
      return {
        listData: null,
        keyword: '',
        page: {
          total: 0,
          pageNum: 1,
          pageSize: 20
        },
        detailDialogData: {
          visible: false,
          detail: {}
        },
        editDialogData: {
          visible: false,
          detail: {}
        },
        listLoading: true
      }
    },
    created() {
      this.getTableListData(1)
    },
    methods: {
      // 获取列表数据
      async getTableListData(num) {
        if (num) {
          this.page.pageNum = num
        }
        this.listLoading = true
        const params = {
          keyword: this.keyword,
          pageNum: this.page.pageNum,
          pageSize: this.page.pageSize
        }
        const res = await request({
          url: 'api/${generateInfo.moduleNameWithSlash}',
          method: 'get',
          params
        })
        if (res.status === 200) {
          this.listData = res.data.list
          this.page.total = res.data.totalItems
        }
        this.listLoading = false
      },
      onKeywordChange() {
        this.getTableListData(1)
      },
      // 查看详情
      async handleDetail(row) {
        const res = await request({
          url: `api/${generateInfo.moduleNameWithSlash}/<#noparse>${row.</#noparse>${generateInfo.primaryKeyCamel}}`,
          method: 'get'
        })
        if (res.status === 200 && res.data) {
          this.detailDialogData.detail = row
          this.detailDialogData.visible = true
        }
      },
      // 修改
      async handleEdit(row) {
        const res = await request({
          url: `api/${generateInfo.moduleNameWithSlash}/<#noparse>${row.</#noparse>${generateInfo.primaryKeyCamel}}`,
          method: 'get'
        })
        if (res.status === 200 && res.data) {
          this.editDialogData.detail = row
          this.editDialogData.visible = true
        }
      },
      handleDel(${generateInfo.primaryKeyCamel}) {
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          request({
            url: `api/${generateInfo.moduleNameWithSlash}/${generateInfo.primaryKeyCamel}`,
            method: 'delete'
          }).then(() => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      }
    }
  }
</script>
