<template>
  <div class="login-container">
    <div class="form-container">
      <div class="form-title">欢迎登陆教务系统</div>
      <el-form :model="user" :rules="rules" ref="loginRef" class="form">
        <el-form-item prop="username" class="form-item">
          <el-input prefix-icon="el-icon-user" size="medium" v-model="user.username" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item prop="password" class="form-item">
          <el-input prefix-icon="el-icon-lock" size="medium" v-model="user.password" show-password placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item prop="code" style="width: 100%;">
          <div style="display: flex; width: 100%;">
            <el-input placeholder="请输入验证码" prefix-icon="el-icon-circle-check" size="medium" v-model="user.code" style="flex: 1; margin-right: 10px;"></el-input>
            <div style="flex: 1;">
              <valid-code @update:value="getCode"></valid-code>
            </div>
          </div>
        </el-form-item>
        <el-form-item class="form-item">
          <el-button type="primary" @click="login" class="login-button">登 陆</el-button>
        </el-form-item>
        <div class="register-link">还没有账号？
          <span @click="$router.push('/register')">注册</span>
          <span @click="handleForgetPass" style="margin-left: 10px">忘记密码</span>
        </div>
      </el-form>
    </div>

    <el-dialog title="忘记密码" :visible.sync="forgetPassDialogVis" width="30%">
      <el-form :model="forgetUserForm" label-width="80px" style="padding-right: 20px">
        <el-form-item label="用户名">
          <el-input v-model="forgetUserForm.username" autocomplete="off" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="forgetUserForm.phone" autocomplete="off" placeholder="请输入手机号"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="forgetPassDialogVis = false">取 消</el-button>
        <el-button type="primary" @click="resetPassword">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import ValidCode from "@/conponents/ValidCode.vue";
export default {
  name: "login",
  components: {
    ValidCode
  },
  data() {
    const validateCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'))
      } else if(value !== this.code){
        callback(new Error('验证码错误'))
      } else {
        callback()
      }
    }; // 此处添加了括号闭合
    return {
      code: '', // 验证码组件传递来的
      user: {
        code: '', // 表单中用户输入的验证码
        username: '',
        password: ''
      },
      rules: {
        username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      },
      forgetUserForm: {},   // 忘记密码的表单数据
      forgetPassDialogVis: false
    }
  },
  methods: {
    getCode(code) {
      this.code = code.toLowerCase()
    },
    login() {
      this.$refs['loginRef'].validate((valid) => {
        if (valid) {
          this.$request.post('/login', this.user).then(res => {
            if (res.code === '200') {
              this.$router.push('/')
              this.$message.success('登陆成功')
              localStorage.setItem("honey-user", JSON.stringify(res.data))  // 将用户信息存储到本地
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
    handleForgetPass() {   //  初始化表单的数据
      this.forgetUserForm = {}
      this.forgetPassDialogVis = true
    },
    resetPassword() {
      this.$request.put('/password', this.forgetUserForm).then(res => {
        if (res.code === '200') {
          this.$message.success('重置成功')
          this.forgetPassDialogVis = false
        } else {
          this.$message.error(res.msg)
        }
      })
    },
  }
}
</script>


<style scoped>
.login-container {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url('~@/assets/background_login.png');
  background-size: cover;
}

.form-container {
  background-color: rgba(255, 255, 255, 0.3);
  width: 30%;
  border-radius: 10px;
  overflow: hidden;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(3px);
}

.form-title {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;
}

.form-item {
  width: 100%;
  margin-bottom: 20px;
}

.login-button {
  width: 100%
}

.register-link {
  text-align: center;
  margin-top: 20px;
}

.register-link span {
  color: #0f9876;
  cursor: pointer;
}
</style>
