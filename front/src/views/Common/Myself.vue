<script setup>
import { ref } from 'vue';

const user = ref({
  name: 'admin',
  email: 'admin@qq.com',
  bio: '前端开发者，热爱编程和设计。',
  avatar: "https://img.zcool.cn/community/04b4wf2hltswlkpp4qebcs3937.png", // 你可以替换成你自己的头像链接
});
</script>

<template>
  <div class="profile-container">
    <div class="profile-card">
      <div class="avatar-container">
        <img :src="user.avatar" alt="Avatar" class="avatar" />
      </div>
      <h1 class="name">{{ user.name }}</h1>
      <p class="email">{{ user.email }}</p>
      <p class="bio">{{ user.bio }}</p>
      <div class="stats">
        <div class="stat">
          <span class="stat-number">0</span>
          <span class="stat-label">粉丝</span>
        </div>
        <div class="stat">
          <span class="stat-number">0</span>
          <span class="stat-label">关注</span>
        </div>
        <div class="stat">
          <span class="stat-number">1</span>
          <span class="stat-label">文章</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.profile-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #ff9a9e 0%, #fad0c4 100%);
  padding: 20px;
}

.profile-card {
  background: white;
  border-radius: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 30px;
  max-width: 400px;
  width: 100%;
  text-align: center;
}

.avatar-container {
  margin-bottom: 20px;
}

.avatar {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  border: 4px solid #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.name {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 10px;
}

.email {
  font-size: 16px;
  color: #666;
  margin-bottom: 20px;
}

.bio {
  font-size: 14px;
  color: #888;
  margin-bottom: 20px;
}

.stats {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}

.stat {
  text-align: center;
}

.stat-number {
  font-size: 20px;
  font-weight: bold;
  display: block;
}

.stat-label {
  font-size: 14px;
  color: #666;
}
</style>