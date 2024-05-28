<template>
  <div>
    <el-container>
      <el-aside :width="asideWidth" style="min-height: 100vh;background-color: #001529">
        <div style="height:60px; line-height:60px;color:white; display:flex; align-items: center; justify-content:center;">
          <i class="el-icon-user-solid" style="font-size:25px"></i>
          <span class="logo-titles" style="margin-left: 5px;font-size:20px" v-show="!isCollapse">{{user.username}}</span>
        </div>


        <el-menu :collapse="isCollapse" :collapse-transition="false" router background-color="#001529" text-color="rgba(255,255,255,0.65)"  active-text-color="#fff" style="border: none" :default-active="$route.path">
          <el-menu-item index="/home">
            <template slot="title">
              <i class="el-icon-house"></i>
              <span>系统首页</span>
            </template>
          </el-menu-item>
<!--          只有一级标签 index才会生效-->
          <el-menu-item index="/class">
            <template slot="title">
              <i class="el-icon-house"></i>
              <span>个人课表</span>
            </template>
          </el-menu-item>
          <el-menu-item index="/grade">
            <template slot="title">
              <i class="el-icon-house"></i>
              <span>成绩管理</span>
            </template>
          </el-menu-item>
<!--          链接新创建的页面-->
          <el-submenu index="info" v-if="user.role==='管理员'">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>信息管理</span>
            </template>
            <el-menu-item index="/user">用户信息</el-menu-item>

          </el-submenu>
        </el-menu>

      </el-aside>
      <el-container>

        <el-header>
          <i class="collapseIcon" style="font-size:26px" @click="handleCollapse"></i>
          <el-breadcrumb separator-class="el-icon-arrow-right" style="margin:20px">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/User' }">用户管理</el-breadcrumb-item>
          </el-breadcrumb>

          <div style="flex: 1; width: 0; display:flex; align-items:center; justify-content: flex-end">
            <i class="el-icon-full-screen" style="font-size:15px;margin-right: 10px" @click="handleFull"></i>
            <el-dropdown placement="bottom">
            <div style="display:flex; align-items:center; cursor:default">
              <i class="el-icon-user-solid" style="font-size: 24px;margin-right: 8px"></i>
              <span style="margin-right: 10px">{{user.name}}</span>
            </div>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="$router.push('/person')">个人信息</el-dropdown-item>
                <el-dropdown-item @click.native="$router.push('/password')">修改密码</el-dropdown-item>
                <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>

        </el-header>
        <!--        主体区域-->
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
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
