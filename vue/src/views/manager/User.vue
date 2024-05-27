<template>
  <div>
    <el-card style="width: 100%">
      <div slot="header" class="clearfix">
        <span>用户数据</span>
      </div>
      <div>
        <el-table :data="users">
          <el-table-column label="ID" prop="id"></el-table-column>  <!--prop属性是什么呢：指定数据源的字段,意思就是将数据源(数据库)的id字段(属性)渲染到表格中-->
          <el-table-column label="用户名" prop="username"></el-table-column>
          <el-table-column label="姓名" prop="name"></el-table-column>
          <el-table-column label="地址" prop="address"></el-table-column>
<!--          <el-table-column label="邮箱" prop="email"></el-table-column>-->
          <el-table-column label="操作">
          <template v-slot="scope">
            <el-button type="primary" plain>编辑</el-button>
            <el-button type="danger" plain>删除</el-button>
          </template>
          </el-table-column>
        </el-table>

      </div>
    </el-card>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "User",
  data() {
    return {
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
  created() {

  },
  methods: {}
}
</script>

<style scoped>

</style>