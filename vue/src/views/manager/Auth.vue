<template>
  <div class="error-page">
    <h1>Oops! 出错了</h1>
    <p>很抱歉，您的权限不够。</p>
    <button @click="goToHomePage">返回主页</button>
  </div>
</template>

<script>
export default {
  methods: {
    goToHomePage() {
      this.$router.push('/Home'); // 跳转到主页路径
    }
  }
}
</script>

<style scoped>
.error-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 80vh;
  font-family: Arial, sans-serif;
}

.error-page h1 {
  font-size: 2rem;
  margin-bottom: 1rem;
}

.error-page p {
  font-size: 1.2rem;
  margin-bottom: 2rem;
}

.error-page button {
  padding: 0.8rem 1.5rem;
  font-size: 1rem;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.error-page button:hover {
  background-color: #0056b3;
}
</style>
