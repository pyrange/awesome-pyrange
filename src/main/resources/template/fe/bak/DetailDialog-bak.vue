<template>
  <el-dialog title="详情" :visible.sync="visible" append-to-body top="50px" width="800px">
    <el-descriptions>
      <el-descriptions-item label="用户名">{{ data.userName }}</el-descriptions-item>
      <el-descriptions-item label="手机号">{{ data.phone }}</el-descriptions-item>
      <el-descriptions-item label="居住地">{{ data.city }}</el-descriptions-item>
      <el-descriptions-item label="状态">
        {{ data.status | dictFilter('status') }}
      </el-descriptions-item>
      <el-descriptions-item label="类型">
        {{ data.type | dictFilter('type') }}
      </el-descriptions-item>
      <el-descriptions-item label="联系地址">{{ data.address }}</el-descriptions-item>
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
  }
}
</script>
