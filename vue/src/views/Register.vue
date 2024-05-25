<template>
  <div class = "background" style="height: 100vh; display: flex; align-items: center; justify-content: center; background-color: #ecb1db">
    <div style="display: flex; background-color: white; width: 50%; border-radius: 10px; overflow: hidden">
      <div style="flex: 1; position: relative;">
        <img src="@/assets/register.png" alt="" style="width: 100%; height: 100%; object-fit: cover;">
      </div>
      <div style="flex: 1; display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100%">
        <el-form :model="user" style="width: 80%" :rules="rules" ref="registerRef">
          <div style="font-size: 20px; font-weight: bold; text-align: center; margin-bottom: 20px; margin-top: 25px;">欢迎注册教务系统</div>
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user" size="medium" placeholder="请输入账号" v-model="user.username"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" size="medium" show-password placeholder="请输入密码" v-model="user.password"></el-input>
          </el-form-item>
          <el-form-item prop="confirmPass">
            <el-input prefix-icon="el-icon-lock" size="medium" show-password placeholder="请确认密码" v-model="user.confirmPass"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="info" style="width: 100%; background-color: #0f9876" @click="register">注 册</el-button>
          </el-form-item>
          <div style="display: flex">
            <div style="flex: 1">已经有账号了？请 <span style="color: #0f9876; cursor: pointer" @click="$router.push('/login')">登录</span></div>
          </div>
        </el-form>
      </div>
    </div>

  </div>
</template>

<script>

export default {
  name: "Register",
  data() {
    // 验证码校验
    const validatePassword = (rule, confirmPass, callback) => {
      if (confirmPass === '') {
        callback(new Error('请确认密码'))
      } else if (confirmPass !== this.user.password) {
        callback(new Error('两次输入的密码不一致'))
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
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        confirmPass: [
          { validator: validatePassword, trigger: 'blur' }
        ],
      }
    }
  },
  created() {

  },
  methods: {
    register() {
      this.$refs['registerRef'].validate((valid) => {
        if (valid) {
          // 验证通过
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
.el-button--primary {
  background-color: #0f9876; /* 你的按钮颜色 */
  border-color: #0f9876;
}
.background {
  background-image: url("../assets/background_register.png");
  background-size: cover;
  background-repeat: no-repeat;
}
</style>