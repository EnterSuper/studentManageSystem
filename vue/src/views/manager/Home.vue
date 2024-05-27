<template>
  <div>
<!--        <el-main>-->
<!--          <div style="box-shadow: 0 0 10px rgba(0,0,0,.1);padding: 10px 20px;border-radius:5px;margin-bottom:10px">-->
<!--            下午好，{{user.username}}！-->
<!--          </div>-->
<!--          <div style="display: flex">-->
<!--            <el-card style="width: 50%; margin-right: 10px">-->
<!--              <div slot="header" class="clearfix">-->
<!--                <span>好好学习，天天向上</span>-->
<!--              </div>-->
<!--              <div>-->
<!--                学习是一种信仰-->
<!--                <div style="margin-top:20px">-->
<!--                  <div style="margin:10px 0"><strong>主题色</strong></div>-->
<!--                  <el-button type="primary">按钮</el-button>-->
<!--                  <el-button type="success">按钮</el-button>-->
<!--                  <el-button type="warning">按钮</el-button>-->
<!--                  <el-button type="danger">按钮</el-button>-->
<!--                  <el-button type="info">按钮</el-button>-->
<!--                </div>-->
<!--              </div>-->
<!--            </el-card>-->
<!--          </div>-->
<!--        </el-main>-->
    <el-main>
      <div class="greeting-box">
        下午好，{{user.username}}！
      </div>
      <div class="card-container">
        <el-card class="custom-card">
          <div slot="header" class="card-header">
            <span>好好学习，天天向上</span>
          </div>
          <div class="card-content">
            学习是一种信仰
          </div>
        </el-card>
      </div>
    </el-main>

  </div>
</template>

<script>

import axios from "axios";
import request from "@/utils/request";  // 引入封装的axios请求,这里的request是什么呢：封装的axios请求

export default {
  name: 'HomeView',
  data(){
    return{
      isCollapse: false,
      asideWidth: '200px',
      collapseIcon:'el-icon-s-fold',
      users: [],
      user:JSON.parse(localStorage.getItem('honey-user')||{})
    }
  },
  mounted() {  // 页面加载完毕后执行
    // axios.get('http://localhost:9090/user/selectAll').then(res=>{  // axios.get()方法是什么呢：发送get请求
    //   console.log(res.data);
    //   this.users = res.data.data
    // })
    request.get('/user/selectAll').then(res=>{  // request.get()方法是什么呢：封装的axios请求
      console.log(res);
      this.users = res.data
    })
  },
  methods:{
    logout(){
      this.$confirm('确定退出登录吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        localStorage.removeItem('honey-user')  // 移除本地存储的用户信息
        this.$router.push('/login')
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消退出'
        });
      });
    },
    handleCollapse() {
      this.isCollapse =!this.isCollapse
      this.asideWidth =this.isCollapse? '64px':'200px'
      this.collapseIcon =this.collapseIcon? 'el-icon-s-unfold':'el-icon-s-fold'
    },
    handleFull(){
      document.documentElement.requestFullscreen()
    }
  }
}

</script>
<style scoped>
/deep/ .el-menu--inline .el-menu-item {
  background-color: #000c17 !important;
  padding-left: 49px !important;
}
/deep/ .el-menu--inline{
  background-color: #000c17 !important;
}
.greeting-box {
  box-shadow: 0 0 10px rgba(0, 0, 0, .1);
  padding: 10px 20px;
  border-radius: 5px;
  margin-bottom: 10px;
}

.card-container {
  display: flex;
}

.custom-card {
  width: 50%;
  margin-right: 10px;
}

.card-header {
  padding: 10px;
  background-color: #f5f7fa;
}

.card-content {
  padding: 20px;
}

/* 使用更高优先级的选择器 */
.el-menu >>> .el-menu-item:hover,
.el-menu >>> .el-submenu__title:hover {
  color: #fff !important;
}
.el-menu >>> .el-submenu__title:hover i{
  color: #fff !important;
}
.el-menu-item.is-active {
  background-color:  #1890ff !important;
  border-radius:5px !important;
  margin: 4px !important;
}
.el-menu-item{
  height:  40px !important;
  line-height:  40px !important;
  margin: 4px !important;
}
.el-submenu__title.is-active {
  height:  40px !important;
  line-height:  40px !important;
}
.el-aside{
  transition: width .3s;
}
.logo-title{
  margin-left:5px;
  font-size:20px;
  transition:all .3s;
}
.el-header
{
  box-shadow: 2px 0 6px rgba(0,21,41,.35);
  display:flex;
  align-items:center;
}
</style>
