<template>
  <div class="login-container">
    <div class="form-container">
      <div class="form-title">欢迎注册教务系统</div>
      <el-form :model="user" :rules="rules" ref="registerRef" class="form">
        <el-form-item prop="username" class="form-item">
          <el-input prefix-icon="el-icon-user" size="medium" v-model="user.username" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item prop="password" class="form-item">
          <el-input prefix-icon="el-icon-lock" size="medium" v-model="user.password" show-password placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPass" class="form-item">
          <el-input placeholder="请确认密码" prefix-icon="el-icon-lock" size="medium" v-model="user.confirmPass"></el-input>
        </el-form-item>

        <el-form-item prop="role" class="form-item">
          <el-radio-group v-model="user.role">
            <el-radio label="学生" style="color: black;"></el-radio>
            <el-radio label="管理员" style="color: black;"></el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item class="form-item">
          <el-button type="primary" @click="register" class="register-button">注 册</el-button>
        </el-form-item>
        <div class="login-link">已经有账号？<span @click="$router.push('/login')">登录</span></div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    // 验证密码是否一致
    const validatePassword = (rule, confirmPass, callback) => {
      if (confirmPass === '') {
        callback(new Error('请确认密码'))
      } else if (confirmPass !== this.user.password) {
        callback(new Error('两次输入密码不一致'))
      } else {
        callback()
      }
    }
    return {
      user: {
        username: '',
        password: '',
        confirmPass: ''
      },
      rules: {
        username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        confirmPass: [{ validator: validatePassword, trigger: 'blur' }],
        role: [{ required: true, message: '请选择角色', trigger: 'blur' }]
      }
    }
  },
  methods: {
    // 注册方法
    register() {
      this.$refs['registerRef'].validate((valid) => {
        if (valid) {
          // 验证通过，发送注册请求
          this.$request.post('/register', this.user).then(res => {
            if (res.code === '200') {
              this.$router.push('/login')
              this.$message.success('注册成功')
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.login-container {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url('~@/assets/background_register.png');
  background-size: cover;
}

.form-container {
  background-color: rgba(255, 255, 255, 0.3); /* 半透明的白色背景 */
  width: 35%; /* 调整容器宽度 */
  border-radius: 10px;
  overflow: hidden;
  padding: 20px; /* 添加内边距 */
  display: flex;
  flex-direction: column;
  align-items: center; /* 水平居中 */
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3); /* 添加阴影 */
  backdrop-filter: blur(3px); /* 添加模糊效果 */

}

.form-title {
  font-size: 24px; /* 调整标题字体大小 */
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px; /* 添加下边距 */
}

.form-item {
  width: 100%; /* 输入框和按钮占满容器宽度 */
  margin-bottom: 20px; /* 调整表单项之间的间距 */
}

.register-button {
  width: 100% /*按钮宽度占满容器宽度 */
}

.login-link {
  text-align: center;
  margin-top: 20px;
}

.login-link span {
  color: #0f9876;
  cursor: pointer;
}
</style>
