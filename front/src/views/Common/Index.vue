<script setup>
import image1 from '@/assets/images/牛排.jpg';
import image2 from '@/assets/images/烤宽面.jpg';
import image3 from '@/assets/images/小龙虾.jpg';
import food1 from '@/assets/images/寿司拼盘.png';
import food2 from '@/assets/images/红烧肉.png';
import food3 from '@/assets/images/麻辣香锅.png';
import food4 from '@/assets/images/北京烤鸭.png';
import food5 from '@/assets/images/四川火锅.png';
import food6 from '@/assets/images/意大利披萨.png';
import { ref, computed, onMounted, onBeforeUnmount } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

// 定义数据
const images = ref([image1, image2, image3]);
const currentIndex = ref(0);
const intervalId = ref(null);

// 计算属性
const currentImage = computed(() => {
  return images.value[currentIndex.value];
});

// 生命周期钩子
onMounted(() => {
  startSlider();
});

onBeforeUnmount(() => {
  stopSlider();
});

// 方法
function startSlider() {
  intervalId.value = setInterval(() => {
    nextImage();
  }, 3000); // 每隔3秒切换图片
}

function stopSlider() {
  clearInterval(intervalId.value);
}

function nextImage() {
  currentIndex.value = (currentIndex.value + 1) % images.value.length;
}

const foods = ref([
  {
    id: 1,
    name: '麻辣香锅',
    description: '香辣可口，多种食材搭配，味道丰富。',
    image: food3,
    author: '美食达人小张',
  },
  {
    id: 2,
    name: '红烧肉',
    description: '肥而不腻，入口即化，经典家常菜。',
    image: food2,
    author: '厨艺大师老王',
  },
  {
    id: 3,
    name: '寿司拼盘',
    description: '新鲜食材，精致摆盘，日式风味。',
    image: food1,
    author: '寿司师傅小李',
  },
  {
    id: 4,
    name: '北京烤鸭',
    description: '外酥里嫩，香气扑鼻，肥而不腻',
    image: food4,
    author: 'admin',
  },
  {
    id: 5,
    name: '四川火锅',
    description: '麻辣鲜香，热气腾腾，食材丰富',
    image: food5,
    author: 'admin',
  },
  {
    id: 6,
    name: '意大利披萨',
    description: '薄脆可口，芝士浓郁，配料多样。',
    image: food6,
    author: 'admin',
  }
]);

// 搜索功能
const searchQuery = ref('');
const filteredFoods = ref(foods.value);

const searchFoods = () => {
  filteredFoods.value = foods.value.filter((food) =>
      food.name.toLowerCase().includes(searchQuery.value.toLowerCase())
  );
};

// 跳转到美食详情页
function goToFoodDetail(foodId) {
  router.push({ name: 'food', params: { id: foodId } });
}


</script>

<template>
  <div id="app" class="grid grid-cols-[1fr,8fr,1fr]">
    <div></div>
    <div class="image-container">
      <img
          :src="currentImage"
          alt="Slider Image"
          class="slider-image"
      />
      <div class="underline-container">
        <span
            v-for="(image, index) in images"
            :key="index"
            :class="['underline-icon', { active: index === currentIndex }]"
            @click="currentIndex = index"
        ></span>
      </div>
    </div>
    <div></div>
  </div>

  <div class="home-page">
    <!-- 顶部导航栏 -->
    <header class="header">
      <h1 class="logo">美食分享平台</h1>
      <div class="search-bar">
        <input
            v-model="searchQuery"
            type="text"
            placeholder="搜索美食..."
            @input="searchFoods"
        />
        <button @click="searchFoods">搜索</button>
      </div>
    </header>

    <!-- 美食卡片列表 -->
    <main class="food-list">
      <div
          v-for="food in filteredFoods"
          :key="food.id"
          class="food-card"
          @click="goToFoodDetail(food.id)"
      >
        <img :src="food.image" :alt="food.name" class="food-image" />
        <div class="food-info">
          <h2 class="food-name">{{ food.name }}</h2>
          <p class="food-description">{{ food.description }}</p>
          <p class="food-author">分享者：{{ food.author }}</p>
        </div>
      </div>
    </main>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.image-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
}

.slider-image {
  width: auto; /* 自动调整宽度 */
  height: 600px; /* 固定高度 */
  max-width: 100%; /* 最大宽度为容器的100% */
}

.underline-container {
  position: absolute;
  bottom: 10px;
  display: flex;
  justify-content: center;
  width: 100%;
}

.underline-icon {
  width: 20px;
  height: 4px;
  background-color: #ccc;
  margin: 0 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.underline-icon.active {
  background-color: #333;
}

.home-page {
  font-family: Arial, sans-serif;
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.logo {
  font-size: 24px;
  font-weight: bold;
  color: #e67e22;
}

.search-bar {
  display: flex;
  gap: 10px;
}

.search-bar input {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 200px;
}

.search-bar button {
  padding: 8px 12px;
  background-color: #e67e22;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.search-bar button:hover {
  background-color: #d35400;
}

.food-list {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
}

.food-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  transition: transform 0.2s;
}

.food-card:hover {
  transform: translateY(-5px);
}

.food-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.food-info {
  padding: 15px;
}

.food-name {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #333;
}

.food-description {
  font-size: 14px;
  color: #666;
  margin-bottom: 10px;
}

.food-author {
  font-size: 12px;
  color: #888;
}
</style>