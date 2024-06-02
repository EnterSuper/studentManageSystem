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

    <el-select v-model="week" placeholder="请选择周数">
      <!-- 这里可以根据实际需要添加更多的选项 -->
      <el-option label="第1周" value="1"></el-option>
      <el-option label="第2周" value="2"></el-option>
      <el-option label="第3周" value="3"></el-option>
      <el-option label="第4周" value="4"></el-option>
      <el-option label="第5周" value="5"></el-option>
      <el-option label="第6周" value="6"></el-option>
      <el-option label="第7周" value="7"></el-option>
      <el-option label="第8周" value="8"></el-option>
      <el-option label="第9周" value="9"></el-option>
      <el-option label="第10周" value="10"></el-option>
      <el-option label="第11周" value="11"></el-option>
      <el-option label="第12周" value="12"></el-option>
      <el-option label="第13周" value="13"></el-option>
      <el-option label="第14周" value="14"></el-option>
      <el-option label="第15周" value="15"></el-option>
      <el-option label="第16周" value="16"></el-option>
      <el-option label="第17周" value="17"></el-option>
      <el-option label="第18周" value="18"></el-option>
    </el-select>

    <el-button @click="fetchSchedule">查询</el-button>

    <!-- 显示课表信息 -->
    <el-table :data="schedule" style="width: 100%">
      <!-- 这里可以根据实际需要添加更多的列 -->
      <el-table-column prop="time" label="时间" width="180"></el-table-column>
      <el-table-column prop="monday" label="星期一" width="180"></el-table-column>
      <el-table-column prop="tuesday" label="星期二" width="180"></el-table-column>
      <el-table-column prop="wednesday" label="星期三" width="180"></el-table-column>
      <el-table-column prop="thursday" label="星期四" width="180"></el-table-column>
      <el-table-column prop="friday" label="星期五" width="180"></el-table-column>
      <el-table-column prop="saturday" label="星期六" width="180"></el-table-column>
      <el-table-column prop="sunday" label="星期日" width="180"></el-table-column>
      <!-- ... -->
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      year: '',
      semester: '',
      week: '',
      schedule: []
    };
  },
  methods: {
    fetchSchedule() {
      // 这里可以发送一个请求到后端 API 端点，获取课表信息
      // 例如：
      this.$request.get('/schedule', {
        params: {
          year: this.year,
          semester: this.semester,
          week: this.week,
          id: this.id,
        }
      }).then(response => {
        this.schedule = response.data;
      });
    }
  }
};
</script>

<style scoped>

</style>