<script setup>
import { ref } from 'vue';

// 模拟数据
const nutritionGoals = ref([
  { name: '蛋白质', target: '50g', consumed: '30g', progress: 60 },
  { name: '碳水化合物', target: '200g', consumed: '150g', progress: 75 },
  { name: '脂肪', target: '70g', consumed: '40g', progress: 57 },
]);

const recommendedFoods = ref([
  { name: '鸡胸肉', calories: '165kcal', protein: '31g' },
  { name: '三文鱼', calories: '208kcal', protein: '20g' },
  { name: '燕麦', calories: '68kcal', protein: '2.4g' },
  { name: '牛油果', calories: '160kcal', protein: '2g' },
]);

const mealPlan = ref([
  { time: '早餐', food: '燕麦粥 + 水煮蛋 + 牛油果' },
  { time: '午餐', food: '烤鸡胸肉 + 糙米饭 + 西兰花' },
  { time: '晚餐', food: '煎三文鱼 + 藜麦沙拉' },
]);
</script>

<template>
  <div class="profile-container">
  <div class="nutrition-plan-container ">
    <!-- 标题 -->
    <h1 class="title">营养饮食规划</h1>

    <!-- 营养目标 -->
    <div class="nutrition-goals">
      <h2 class="section-title">每日营养目标</h2>
      <div class="goals-list">
        <div v-for="goal in nutritionGoals" :key="goal.name" class="goal-item">
          <div class="goal-info">
            <span class="goal-name">{{ goal.name }}</span>
            <span class="goal-consumed">{{ goal.consumed }} / {{ goal.target }}</span>
          </div>
          <div class="progress-bar">
            <div
                class="progress"
                :style="{ width: goal.progress + '%' }"
            ></div>
          </div>
        </div>
      </div>
    </div>

    <!-- 推荐食物 -->
    <div class="recommended-foods">
      <h2 class="section-title">推荐食物</h2>
      <div class="foods-list">
        <div v-for="food in recommendedFoods" :key="food.name" class="food-item">
          <span class="food-name">{{ food.name }}</span>
          <span class="food-details">{{ food.calories }} · {{ food.protein }} 蛋白质</span>
        </div>
      </div>
    </div>

    <!-- 饮食计划 -->
    <div class="meal-plan">
      <h2 class="section-title">今日饮食计划</h2>
      <div class="meals-list">
        <div v-for="meal in mealPlan" :key="meal.time" class="meal-item">
          <span class="meal-time">{{ meal.time }}</span>
          <span class="meal-food">{{ meal.food }}</span>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<style scoped>
/* 整体容器 */
.profile-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #ff9a9e 0%, #fad0c4 100%);
  padding: 20px;
}
.nutrition-plan-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background: linear-gradient(135deg, #f6d365, #fda085);
  border-radius: 15px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
  color: #333;
}

/* 标题 */
.title {
  text-align: center;
  font-size: 2.5rem;
  color: #fff;
  margin-bottom: 30px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
}

/* 分区标题 */
.section-title {
  font-size: 1.8rem;
  color: #fff;
  margin-bottom: 20px;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
}

/* 营养目标 */
.nutrition-goals {
  background: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 20px;
}

.goal-item {
  margin-bottom: 15px;
}

.goal-info {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
  font-size: 1.1rem;
}

.goal-name {
  font-weight: bold;
}

.goal-consumed {
  color: #666;
}

.progress-bar {
  width: 100%;
  height: 10px;
  background: #e0e0e0;
  border-radius: 5px;
  overflow: hidden;
}

.progress {
  height: 100%;
  background: #e67e22;
  border-radius: 5px;
  transition: width 0.3s ease;
}

/* 推荐食物 */
.recommended-foods {
  background: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 20px;
}

.foods-list {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: 15px;
}

.food-item {
  background: #fff;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.food-name {
  font-size: 1.2rem;
  font-weight: bold;
  display: block;
  margin-bottom: 5px;
}

.food-details {
  font-size: 0.9rem;
  color: #666;
}

/* 饮食计划 */
.meal-plan {
  background: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 10px;
}

.meals-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.meal-item {
  background: #fff;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.meal-time {
  font-size: 1.1rem;
  font-weight: bold;
}

.meal-food {
  font-size: 1rem;
  color: #666;
}

/* 响应式设计 */
@media (max-width: 600px) {
  .title {
    font-size: 2rem;
  }

  .section-title {
    font-size: 1.5rem;
  }

  .goal-info {
    font-size: 1rem;
  }

  .food-name {
    font-size: 1rem;
  }

  .meal-time {
    font-size: 1rem;
  }

  .meal-food {
    font-size: 0.9rem;
  }
}
</style>