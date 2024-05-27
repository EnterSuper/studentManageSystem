<script>
export default {
  name: "成绩查询",
  data(){
    return{
      year:'',
      semester:'',
      grades:[
        { id:1,course:'心理健康',credit:'3.0',grade:'99',type:'必修'},
        { id:2,course:'马克思主义政治经济学',credit:'4.0',grade:'66',type:'跨学科选修'},
        { id:3,course:'大学体育',credit:'2.0',grade:'66',type:'必修'},
        { id:4,course:'湘谈四史',credit:'1.0',grade:'缺考',type:'选修'},
        { id:5,course:'心肺复苏与急救',credit:'2.0',grade:'95',type:'必修'},
        { id:6,course:'数学分析',credit:'4.0',grade:'66',type:'必修'},
        { id:7,course:'声乐礼仪',credit:'1.5',grade:'66',type:'必修'},
        { id:8,course:'电影评鉴',credit:'1.0',grade:'66',type:'选修'},
      ]
    }
  },
  methods:{
    yearselect(){
      console.log(this.year)
    },
    semesterselect(){
      console.log(this.semester)
    }
  }
}
</script>

<template>
  <div style="padding: 100px;border: 20px #ccc">
    <h1>成绩查询</h1>
    <div>
      <div style="padding: 10px">
        <!--        选择-->
        <el-row>
          年份
          <el-select v-model="year" @change="yearselect">
            <el-option value="全部"></el-option>
            <el-option value="2022"></el-option>
            <el-option value="2023"></el-option>
            <el-option value="2024"></el-option>
          </el-select>
          学期
          <el-select v-model="semester" @change="semesterselect">
            <el-option value="全部"></el-option>
            <el-option value="春季"></el-option>
            <el-option value="秋季"></el-option>
          </el-select>
        </el-row>
      </div>
    </div>
    <div style="padding: 10px">
      <!--        成绩-->
      <el-row table="margin:100px 0">
        <el-table :data="grades" border :header-cell-style="{background:'aliceblue',fontSize:'14px',textAlign:'center'}">
          <el-table-column label="序号" prop="id" align="center"></el-table-column>
          <el-table-column label="课程" prop="course" align="center"></el-table-column>
          <el-table-column label="学分" prop="credit" align="center"></el-table-column>
          <el-table-column label="成绩" prop="grade" align="center"></el-table-column>
          <el-table-column label="类型" prop="type" align="center"></el-table-column>
        </el-table>
      </el-row>
    </div>
  </div>
</template>

<style scoped>

</style>