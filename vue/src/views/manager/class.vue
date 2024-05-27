<script>
export default {
  name: "课表查询",
  data(){
    return{
      year:'',
      semester:'',
      week:'',
      schedule:[
        { time:'1-2',monday:'数据库原理',tuesday:'运筹学'     ,wednesday:'数据库原理'  ,thursday:'',friday:'',saturday:'',sunday:''},
        { time:'3-4',monday:'操作系统'  ,tuesday:''         ,wednesday:''          ,thursday:'',friday:'大学体育4',saturday:'',sunday:''},
        { time:'5-6',monday:''        ,tuesday:''          ,wednesday:'web前端开发',thursday:'操作系统',friday:'',saturday:'',sunday:''},
        { time:'7-8',monday:''        ,tuesday:''          ,wednesday:''         ,thursday:'',friday:'',saturday:'',sunday:''},
        { time:'9-11',monday:''       ,tuesday:'web前端开发',wednesday:''         ,thursday:'',friday:'',saturday:'',sunday:''}
      ]
    }
  },
  methods:{
    yearselect(){
      console.log(this.year)
    },
    semesterselect(){
      console.log(this.semester)
    },
    weekselect(){
      console.log(this.week)
    }
  }
}
</script>

<template>
  <div style="padding: 100px;border: 20px #ccc">
    <h1>课表查询</h1>
    <div>
      <div style="padding: 10px">
        <!--        选择-->
        <el-row>
          年份
          <el-select v-model="year" @change="yearselect">
            <el-option value="2022"></el-option>
            <el-option value="2023"></el-option>
            <el-option value="2024"></el-option>
            <el-option value="2025"></el-option>
            <el-option value="2026"></el-option>
          </el-select>
          学期
          <el-select v-model="semester" @change="semesterselect">
            <el-option value="春季"></el-option>
            <el-option value="秋季"></el-option>
          </el-select>
          周期
          <el-select v-model="week" @change="weekselect">
            <el-option value="第1周"></el-option>
            <el-option value="第2周"></el-option>
            <el-option value="第3周"></el-option>
            <el-option value="第4周"></el-option>
            <el-option value="第5周"></el-option>
            <el-option value="第6周"></el-option>
            <el-option value="第7周"></el-option>
            <el-option value="第8周"></el-option>
            <el-option value="第9周"></el-option>
            <el-option value="第10周"></el-option>
            <el-option value="第11周"></el-option>
            <el-option value="第12周"></el-option>
            <el-option value="第13周"></el-option>
            <el-option value="第14周"></el-option>
            <el-option value="第15周"></el-option>
            <el-option value="第16周"></el-option>
            <el-option value="第17周"></el-option>
            <el-option value="第18周"></el-option>
            <el-option value="第19周"></el-option>
            <el-option value="第20周"></el-option>
          </el-select>
        </el-row>
      </div>
    </div>
    <div style="padding: 10px">
      <!--        成绩-->
      <el-row table="margin:100px 0">
        <el-table :data="schedule" border :header-cell-style="{background:'aliceblue',fontSize:'14px',textAlign:'center'}">
          <el-table-column label="时间" prop="time" align="center"></el-table-column>
          <el-table-column label="星期一" prop="monday" align="center"></el-table-column>
          <el-table-column label="星期二" prop="tuesday" align="center"></el-table-column>
          <el-table-column label="星期三" prop="wednesday" align="center"></el-table-column>
          <el-table-column label="星期四" prop="thursday" align="center"></el-table-column>
          <el-table-column label="星期五" prop="friday" align="center"></el-table-column>
          <el-table-column label="星期六" prop="saturday" align="center"></el-table-column>
          <el-table-column label="星期日" prop="sunday" align="center"></el-table-column>
        </el-table>
      </el-row>
    </div>
  </div>
</template>

<style scoped>

</style>