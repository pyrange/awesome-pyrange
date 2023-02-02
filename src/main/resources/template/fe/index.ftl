<template>
  <div class="app-container">
    <!-- 搜索 -->
    <el-row :gutter="20">
      <el-col :span="21">
        <el-form ref="searchForm" :inline="true" class="search-box" :model="searchData">
          <el-form-item class="search-item" label="关键字搜索：" prop="keyword">
            <el-input v-model="searchData.keyword" clearable suffix-icon="el-icon-search"
                      placeholder="请输入关键字搜索" @keyup.enter.native="handleSearch" />
          </el-form-item>
          <el-form-item class="search-item" label="状态：" prop="status">
            <el-select v-model="searchData.status" style="width: 100%" placeholder="请选择" filterable
                       @change="handleSearch">
              <el-option label="全部" :value="''"> </el-option>
              <el-option label="无效" :value="0"> </el-option>
              <el-option label="有效" :value="1"> </el-option>
            </el-select>
          </el-form-item>
          <el-form-item class="search-item" label="日期筛选：" prop="date">
            <el-date-picker v-model="searchData.date" style="width: 100%" type="daterange"
                            start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd"
                            @change="handleSearch">
            </el-date-picker>
          </el-form-item>
          <!-- 默认 需要收起 -->
          <el-form-item v-show="!isFold" class="search-item" label="启用：" prop="disable">
            <el-switch v-model="searchData.disable" :active-value="1" :inactive-value="0"  @change="handleSearch">
            </el-switch>
          </el-form-item>
          <el-form-item v-show="!isFold" class="search-item" label="是否启用：" prop="disable">
            <el-select v-model="searchData.disable" style="width: 100%" placeholder="请选择" filterable
                       @change="handleSearch">
              <el-option label="关闭" :value="0"> </el-option>
              <el-option label="开启" :value="1"> </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="3" style="text-align: right;">
        <!-- 重置搜索表单 -->
        <svg-icon iconClass="clear" style="font-size: 20px; cursor: pointer;" @click="resetField"></svg-icon>
        <!-- 展开/收起 -->
        <span class="fold" @click="isFold = !isFold"> {{ isFold ? '展开' : '收起' }} <i :class=" isFold ? 'el-icon-arrow-down' : 'el-icon-arrow-up'"></i> </span>
      </el-col>
    </el-row>

    <!-- 按钮栏 -->
    <div class="btn-tools">
      <div class="btns">
        <el-button size="mini" type="primary" @click="handleAdd">添加</el-button>
      </div>
      <div>
        <span style="font-size: 14px; margin-right: 10px;" @click="handleSearch()">刷新<i class="el-icon-refresh"></i></span>
      </div>
    </div>
    <!-- 列表 -->
    <el-table v-loading="listLoading" :data="listData" element-loading-text="Loading" fit highlight-current-row>
<#list generateInfo.columnList as column>
  <#if generateInfo.primaryKey == column.columnCamelName || "${column.columnCamelName}"?matches("deleted|isDel|isDelete|isDeleted|createUserName|createUserId|updateUserName|updateUserId|updateTime")>
  <#else>
      <el-table-column align="center" prop="${column.columnCamelName}" <#if "${column.columnJavaTypeName}"?matches("LocalDateTime")>width="140" </#if>label="${column.columnComment}">
    <#if column_index == 1>
        <template slot-scope="{row}">
          <el-link type="primary" @click="handleDetail(row)">{{row.${column.columnCamelName}}}</el-link>
        </template>
    <#elseif "${column.columnCamelName}"?ends_with("ed")>
        <template slot-scope="{row}">
          {{ row.${column.columnCamelName} | dictFilter('whether') }}
        </template>
    <#elseif "${column.columnCamelName}"?matches(".*?(Status|Type|Strategy).*")>
        <template slot-scope="{row}">
          {{ row.${column.columnCamelName} | dictFilter('${column.columnCamelName}') }}
        </template>
    </#if>
      </el-table-column>
  </#if>
</#list>
      <el-table-column align="center" label="操作" fixed="right" width="150">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="handleEdit(row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDel(row.${generateInfo.primaryKeyLowerCamel})">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="footer">
      <Paging v-if="listData && listData.length" :page="page"
        @current-change="mixOnPageIndexChange" @size-change="mixOnPageSizeChange" />
    </div>
    <!-- 详情弹窗 -->
    <detailDialog v-if="detailDialogData.visible" :visible.sync="detailDialogData.visible" :data="detailDialogData.detail" />
    <!-- 编辑弹窗 -->
    <editDialog v-if="editDialogData.visible" :visible.sync="editDialogData.visible" :data="editDialogData.detail" @success="initTable" />
    <!-- 新增 -->
    <addDrawer v-if="addDrawerVisible" :drawer.sync="addDrawerVisible" @success="addSuccess" />
  </div>
</template>

<script>
import paging from '@/mixins/paging'
import request from '@/api/axios'
import detailDialog from './component/detailDialog.vue'
import editDialog from './component/editDialog.vue'
import addDrawer from './component/addDrawer.vue'
export default {
  components: {
    detailDialog,
    editDialog,
    addDrawer
  },
  mixins: [paging],
  data() {
    return {
      listData: null,
      isFold: true,
      addDrawerVisible: false,
      listLoading: true,
      searchData: {
        keyword: '',
        status: '',
        date: null,
        disable: 1
      },
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
      }
    }
  },
  created() {
    this.initTable(1)
  },
  methods: {
    // 获取列表数据
    async initTable(num) {
      if (num) {
        this.page.pageNum = num
      }
      this.listLoading = true
      const params = {
        keyword: this.searchData.keyword,
        status: this.searchData.status,
        startDate: this.searchData.date ? this.searchData.date[0] : '',
        endDate: this.searchData.date ? this.searchData.date[1] : '',
        pageNum: this.page.pageNum,
        pageSize: this.page.pageSize
      }
      const res = await request({
        url: '${generateInfo.moduleNameWithSlash}',
        method: 'get',
        params
      })
      if (res.status === 200) {
        this.listData = res.data.list
        this.page.total = res.data.totalItems
      }
      this.listLoading = false
    },

    // 重置查询条件
    resetField() {
      this.$refs['searchForm'].resetFields()
      this.initTable(1)
    },

    // 搜索查询
    handleSearch() {
      this.initTable(1)
    },

    // 查看详情
    async handleDetail(row) {
      const res = await request({
        url: `${generateInfo.moduleNameWithSlash}/<#noparse>${row.</#noparse>${generateInfo.primaryKeyLowerCamel}}`,
        method: 'get'
      })
      if (res.status === 200 && res.data) {
        this.detailDialogData.detail = res.data
        this.detailDialogData.visible = true
      }
    },

    // 打开添加
    handleAdd() {
      this.addDrawerVisible = true
    },

    // 添加成功 回调
    addSuccess() {
      this.addDrawerVisible = false
      this.initTable(1)
    },

    // 修改
    async handleEdit(row) {
      const res = await request({
        url: `${generateInfo.moduleNameWithSlash}/<#noparse>${row.</#noparse>${generateInfo.primaryKeyLowerCamel}}`,
        method: 'get'
      })
      if (res.status === 200 && res.data) {
        this.editDialogData.detail = res.data
        this.editDialogData.visible = true
      }
    },

    // 删除
    handleDel(${generateInfo.primaryKeyLowerCamel}) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request({
          url: `${generateInfo.moduleNameWithSlash}/<#noparse>${</#noparse>${generateInfo.primaryKeyLowerCamel}}`,
          method: 'delete'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.handleSearch()
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