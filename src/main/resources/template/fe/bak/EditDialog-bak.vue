<template>
  <el-dialog title="编辑" :visible.sync="visible" append-to-body top="50px" width="800px">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="120px">

      <el-form-item label="姓名" prop="name">
        <el-input v-model="ruleForm.name" />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="ruleForm.age" />
      </el-form-item>
      <el-form-item label="日期" prop="date">
        <el-date-picker v-model="ruleForm.date" type="date" value-format="yyyy-MM-dd" placeholder="选择日期" />
      </el-form-item>
      <!-- 字典 -->
      <el-form-item label="类型" prop="type">
        <el-select v-model="ruleForm.type" placeholder="请选择">
          <el-option label="" value="全部" />
          <el-option :label="0" value="开启" />
          <el-option :label="1" value="关闭" />
        </el-select>
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-radio-group v-model="ruleForm.type">
          <el-radio :label="0">开启</el-radio>
          <el-radio :label="1">关闭</el-radio>
        </el-radio-group>
      </el-form-item>
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
        name: [{ required: true, message: '请输入名称', trigger: 'blur' }],
        age: [{ required: true, message: '请输入年龄', trigger: 'blur' }],
        type: [{ required: true, message: '请选择类型', trigger: 'change' }]
      }
    }
  },
  watch: {
    visible(val) {
      if (val) {
        this.ruleForm = Object.assign({}, this.data)
      }
    }
  },
  methods: {
    // 取消
    handleCancel() {
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
            url: `/api/table/list`,
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
