<template>
  <div>
    <el-container>
      <el-aside :width="asideWidth" style="min-height: 100vh;background-color: #001529">
        <div style="height:60px; line-height:60px;color:white; display:flex; align-items: center; justify-content:center;">
          <img src="@/assets/logo1.jpg" alt="" style="width: 40px;height: 40px">
          <span class="logo-titles" tyle="margin-left: 5px;font-size:20px" v-show="!isCollapse">nana</span>
        </div>


        <el-menu :collapse="isCollapse" :collapse-transition="false" router background-color="#001529" text-color="rgba(255,255,255,0.65)"  active-text-color="#fff" style="border: none" :default-active="$route.path">
          <el-menu-item index="/">
              <i class="el-icon-house"></i>
              <span slot="title">系统首页</span>
          </el-menu-item>
<!--          只有一级标签 index才会生效-->
          <el-menu-item index="/1">
            <template slot="title">
              <i class="el-icon-house"></i>
              <span>个人课表</span>
            </template>
          </el-menu-item>
          <el-menu-item index="/2">
            <template slot="title">
              <i class="el-icon-house"></i>
              <span>成绩管理</span>
            </template>
          </el-menu-item>
          <el-menu-item index="/3">
            <template slot="title">
              <i class="el-icon-house"></i>
              <span>选课信息</span>
            </template>
          </el-menu-item>
          <el-submenu>
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>信息管理</span>
            </template>
            <el-menu-item>用户信息</el-menu-item>
            <el-menu-item>管理员信息</el-menu-item>
          </el-submenu>
        </el-menu>

      </el-aside>
      <el-container>

        <el-header>
          <i :class="collapseIcon" style="font-size:26px" @click="handleCollapse"></i>
          <el-breadcrumb separator-class="el-icon-arrow-right" style="margin:20px">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/User' }">用户管理</el-breadcrumb-item>
          </el-breadcrumb>

          <div style="flex: 1; width: 0; display:flex; align-items:center; justify-content: flex-end">
<!--            <i class="el-icon-full-screen" style="font-size: 26px"></i>-->
            <i class="el-icon-full-screen" style="font-size:20px" @click="handleFull"></i>
            <el-dropdown placement="bottom">
            <div style="display:flex; align-items:center; cursor:default">
              <img src="@/assets/logo1.jpg" alt="" style="width:40px; height: 40px" >
              <span>管理员</span>
            </div>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>个人信息</el-dropdown-item>
                <el-dropdown-item>修改密码</el-dropdown-item>
                <el-dropdown-item @click="">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>

        </el-header>

        <el-main>
        <div style="box-shadow: 0 0 10px rgba(0,0,0,.1);padding: 10px 20px;border-radius:5px;margin-bottom:10px">
          下午好，nana！
        </div>
          <el-card style="width:500px">
            <div slot="header" class="clearfix">
              <span>好好学习，天天向上</span>
            </div>
            <div>
              学习是一种信仰
              <div style="margin-top:20px">
                <div style="margin:10px 0"><strong>主题色</strong></div>
                <el-button type="primary">按钮</el-button>
                <el-button type="success">按钮</el-button>
                <el-button type="warning">按钮</el-button>
                <el-button type="danger">按钮</el-button>
                <el-button type="info">按钮</el-button>
              </div>
            </div>
          </el-card>
        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script>

export default {
  name: 'HomeView',
  data(){
    return{
      isCollapse: false,
      asideWidth: '200px',
      collapseIcon:'el-icon-s-fold'
    }
  },
  methods:{
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
