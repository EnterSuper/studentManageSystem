<template>
  <div>
    <!-- 选择年份、学期和周数 -->
    <el-select v-model="year" placeholder="请选择年份">
      <!-- 这里可以根据实际需要添加更多的选项 -->
      <el-option label="2022" value="2022"></el-option>
      <el-option label="2023" value="2023"></el-option>
      <el-option label="2024" value="2024"></el-option>
    </el-select>

    <el-select v-model="semester" placeholder="请选择学期">
      <el-option label="Spring" value="Spring"></el-option>
      <el-option label="Fall" value="Fall"></el-option>
    </el-select>

    <el-button @click="fetchSchedule">查询</el-button>

    <!-- 显示课表信息 -->
    <el-card style="width: 100%">
      <div slot="header" class="clearfix">
        <span>成绩</span>
      </div>
      <div>
        <el-table :data="schedule" style="width: 100%">
          <el-table-column label="课程" prop="title"  width="180"></el-table-column>
          <el-table-column label="学分" prop="credits"  width="180"></el-table-column>
          <el-table-column label="成绩" prop="grade"  width="180"></el-table-column>
          <el-table-column label="类型" prop="type"  width="180"></el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      year: '',
      semester: '',
      week: 5,
      schedule: []
    };
  },
  methods: {
    fetchSchedule() {
      this.$request.get('/schedule', {
        params: {
          year: this.year,
          semester: this.semester,
          week: this.week
        }
      }).then(response => {
        console.log("111111111111111")
        console.log(response.data);
        this.schedule = response.data;
      });
    }
  },
  mounted() {
    this.fetchSchedule();
  }
};
</script>

<style scoped>
</style>