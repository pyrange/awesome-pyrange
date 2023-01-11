<template>
  <div class="app-container">
    <!-- 搜索 -->
    <div class="search">
      <div class="search-item">
        <el-input
          v-model="keyword"
          size="small"
          clearable
          suffix-icon="el-icon-search"
          style="width: auto;"
          placeholder="输入关键字搜索"
          @blur="onKeywordChange"
          @keyup.enter.native="$event.target.blur()"
        />
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
      <el-table-column align="center" label="操作" width="200">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="handleDetail(row.${generateInfo.primaryKeyLowerCamel})">查看</el-button>
          <el-button type="primary" size="mini" @click="handleEdit(row.${generateInfo.primaryKeyLowerCamel})">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDel(row.${generateInfo.primaryKeyLowerCamel})">删除</el-button>
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
    <DetailDialog :visible.sync="detailDialogData.visible" :data="detailDialogData.detail" />
    <!-- 编辑弹窗 -->
    <EditDialog
      :visible.sync="detailDialogData.visible"
      :data="detailDialogData.detail"
      @success="getTableListData"
    />
  </div>
</template>

<script>
import paging from '@/mixins/paging'
import { getList, getDetail } from '@/api/table'
import DetailDialog from './components/DetailDialog.ftl'
import EditDialog from './components/EditDialog.vue'
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
      const res = await getList(params)
      if (res.status === 200) {
        this.listData = res.data.list
        this.page.total = res.data.totalItems
      }
      this.listLoading = false
    },
    // 查看详情
    async handleDetail(id) {
      const res = await getDetail(id)
      if (res.status === 200 && res.data) {
        this.detailDialogData.detail = res.data
        this.detailDialogData.visible = true
      }
    },
    // 修改
    async handleEdit(id) {
      const res = await getDetail(id)
      if (res.status === 200 && res.data) {
        this.editDialogData.detail = res.data
        this.editDialogData.visible = true
      }
    },
    handleDel() {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!'
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
