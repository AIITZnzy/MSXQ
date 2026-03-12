<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import food3 from '@/assets/images/麻辣香锅.png';
import food2 from '@/assets/images/红烧肉.png';
import food1 from '@/assets/images/寿司拼盘.png';
import food4 from "@/assets/images/北京烤鸭.png";
import food5 from '@/assets/images/四川火锅.png';
import food6 from '@/assets/images/意大利披萨.png';
const route = useRoute();
const food = ref({});

onMounted(() => {
  const foodId = route.params.id;
  // 根据 foodId 获取美食详情数据
  fetchFoodDetail(foodId).then(data => {
    food.value = data;
  });
});

function fetchFoodDetail(foodId) {
  // 模拟获取美食详情
  if (foodId==1)
  {
    return Promise.resolve({
      id: foodId,
      name: '麻辣香锅',
      description: '香辣可口，多种食材搭配，味道丰富。',
      image: food3,
      author: '美食达人小张',
      steps: [
        "准备食材：牛肉片、鸡肉片、虾仁、豆腐、土豆片、藕片、青椒、红椒、洋葱等。",
        "将食材分别焯水或油炸至半熟，捞出备用。",
        "热锅凉油，放入花椒、干辣椒、姜片、蒜片爆香。",
        "加入火锅底料炒香，倒入适量清水煮开。",
        "将焯水或油炸过的食材放入锅中，翻炒均匀。",
        "加入盐、鸡精、生抽、老抽调味，继续翻炒至食材入味。",
        "最后撒上香菜和芝麻，出锅装盘即可。"
      ]
    });
  }
  if (foodId==2)
  {
    return Promise.resolve({
      id: foodId,
      name: '红烧肉',
      description: '肥而不腻，入口即化，经典家常菜。',
      image: food2,
      author: '厨艺大师老王',
      steps: [
        "准备食材：五花肉 500 克，生姜 3 片，大葱 1 段，八角 2 颗，桂皮 1 小块，香叶 2 片，冰糖 30 克，生抽 2 汤匙，老抽 1 汤匙，料酒 2 汤匙，清水适量，盐适量。",
        "处理五花肉：将五花肉切成 3 厘米见方的小块。锅中加入冷水，放入五花肉块，加入 2 片姜和 1 汤匙料酒，大火煮开后撇去浮沫，捞出五花肉沥干备用。",
        "炒糖色：锅中加入少量油，放入冰糖，用小火慢慢炒至冰糖完全融化，变成棕红色的糖浆。注意不要炒过头，否则会发苦。",
        "煸炒五花肉：将焯水后的五花肉块倒入糖色中，快速翻炒，使每块肉均匀裹上糖色。加入姜片、葱段、八角、桂皮和香叶，继续翻炒出香味。",
        "调味炖煮：加入 2 汤匙生抽、1 汤匙老抽和 1 汤匙料酒，翻炒均匀。倒入适量清水，水量以刚好没过五花肉为宜。大火煮开后，转小火盖上锅盖，慢炖 40 分钟。",
        "收汁：40 分钟后，打开锅盖，转大火收汁。根据个人口味加入适量盐调味，继续翻炒至汤汁浓稠，均匀裹在肉块上。",
        "出锅装盘：将红烧肉盛出，撒上少许葱花或香菜点缀即可。"
      ]
    });
  }
  if (foodId==3)
  {
    return Promise.resolve({
      id: foodId,
      name: '寿司拼盘',
      description: '新鲜食材，精致摆盘，日式风味。',
      image: food1,
      author: '寿司师傅小李',
      steps: [
        "准备食材：寿司米 300 克，米醋 2 汤匙，糖 1 汤匙，盐 1/2 茶匙，新鲜三文鱼 200 克，新鲜金枪鱼 200 克，牛油果 1 个，黄瓜 1 根，蟹棒 4 根，海苔片 4 张，日式酱油、芥末、姜片适量。",
        "煮寿司米：将寿司米洗净，浸泡 30 分钟后煮熟。煮好后趁热加入米醋、糖和盐，轻轻翻拌均匀，放凉备用。",
        "准备配料：将三文鱼和金枪鱼切成薄片，牛油果去皮去核后切片，黄瓜切成细条，蟹棒撕成细丝。",
        "制作寿司卷：将海苔片铺在竹帘上，均匀铺上一层寿司米，留出 2 厘米的边缘。在米饭中间放上黄瓜条、牛油果片和蟹棒丝，用竹帘卷紧，切成小段。",
        "制作握寿司：取一小团寿司米，捏成椭圆形，在顶部放上一片三文鱼或金枪鱼，轻轻按压固定。",
        "摆盘：将寿司卷和握寿司整齐摆放在盘中，搭配牛油果片、黄瓜条等装饰。",
        "调味：准备小碟，倒入日式酱油，加入适量芥末和姜片，搭配寿司食用。"
      ]
    });
  }
  if (foodId==4)
  {
    return Promise.resolve({
      id: foodId,
      name: '北京烤鸭',
      description: '外酥里嫩，香气扑鼻，肥而不腻',
      image: food4,
      author: 'admin',
      steps: [
        "准备食材：整只鸭子（约 2.5 公斤），麦芽糖 50 克，白醋 50 毫升，料酒 50 毫升，清水适量，葱段、姜片适量，甜面酱、黄瓜条、葱丝、荷叶饼适量。",
        "处理鸭子：将鸭子洗净，去除内脏和多余脂肪。用开水烫一遍鸭皮，使鸭皮收缩，晾干备用。",
        "制作脆皮水：将麦芽糖、白醋、料酒和清水混合，加热至糖完全融化，制成脆皮水。",
        "涂抹脆皮水：用刷子将脆皮水均匀涂抹在鸭皮上，挂在通风处晾干 6-8 小时，重复涂抹 2-3 次，直到鸭皮变干。",
        "烤制鸭子：将鸭子挂在烤炉中，用果木炭火烤制。先烤鸭背，再烤鸭胸，直到鸭皮呈金黄色，外酥里嫩，约需 45 分钟。",
        "片鸭：将烤好的鸭子取出，趁热片成薄片，皮肉分离，摆盘备用。",
        "准备配菜：将黄瓜切成条，葱切成细丝，甜面酱加热备用。",
        "食用方法：用荷叶饼包裹鸭肉片、黄瓜条、葱丝，蘸上甜面酱，卷起来食用。"
      ]
    });
  }
  if (foodId==5)
  {
    return Promise.resolve({
      id: foodId,
      name: '四川火锅',
      description: '麻辣鲜香，热气腾腾，食材丰富',
      image: food5,
      author: 'admin',
      steps: [
        "准备食材：牛油火锅底料 200 克，干辣椒 50 克，花椒 20 克，生姜 1 块，大蒜 5 瓣，大葱 1 根，豆瓣酱 2 汤匙，料酒 2 汤匙，清水适量，牛肉片、羊肉片、毛肚、黄喉、虾滑、豆腐、土豆片、藕片、金针菇、生菜等适量。",
        "制作火锅底料：热锅凉油，放入牛油火锅底料，小火炒至融化。加入干辣椒、花椒、姜片、蒜片和葱段，炒出香味。",
        "加入调味料：放入豆瓣酱炒香，加入料酒和适量清水，煮开后转小火熬制 10 分钟，使汤底更加浓郁。",
        "准备蘸料：将蒜末、香菜、葱花、花生碎、芝麻酱、辣椒油、酱油、醋等按个人口味调配成蘸料。",
        "准备食材：将牛肉片、羊肉片、毛肚、黄喉等肉类食材切片，豆腐、土豆片、藕片等蔬菜食材洗净切好，虾滑挤成小球备用。",
        "开始涮火锅：将熬好的火锅底料倒入火锅中，加热至沸腾。先涮肉类食材，再涮蔬菜和豆制品，最后涮虾滑等海鲜类食材。",
        "享用火锅：将涮好的食材蘸上调配好的蘸料，趁热食用，享受麻辣鲜香的口感。"
      ]
    });
  }
  if (foodId==6)
  {
    return Promise.resolve({
      id: foodId,
      name: '意大利披萨',
      description: '薄脆可口，芝士浓郁，配料多样。',
      image: food6,
      author: 'admin',
      steps: [
        "准备食材：高筋面粉 300 克，干酵母 5 克，温水 180 毫升，橄榄油 1 汤匙，盐 1 茶匙，番茄酱 100 克，马苏里拉芝士 200 克，意大利香肠片 100 克，蘑菇 50 克，青椒 1 个，洋葱 1/2 个，黑橄榄适量，新鲜罗勒叶适量。",
        "制作面团：将高筋面粉、干酵母、温水、橄榄油和盐混合，揉成光滑的面团。盖上湿布，放置在温暖处发酵 1 小时，直到面团变成两倍大。",
        "准备配料：将蘑菇切片，青椒和洋葱切丝，意大利香肠切片，马苏里拉芝士切碎备用。",
        "擀制面饼：将发酵好的面团排气，擀成圆形或方形的薄饼，厚度约 0.5 厘米。",
        "涂抹番茄酱：在面饼上均匀涂抹一层番茄酱，边缘留出 1 厘米的空隙。",
        "添加配料：撒上马苏里拉芝士，铺上意大利香肠片、蘑菇片、青椒丝、洋葱丝和黑橄榄。",
        "烘烤披萨：将披萨放入预热至 220°C 的烤箱中，烤 10-12 分钟，直到饼底金黄酥脆，芝士融化并微微起泡。",
        "装饰与享用：取出披萨，撒上新鲜罗勒叶，趁热切片食用。"
      ]
    });
  }

}
</script>

<template>
  <div class="food-detail-container">
    <div class="food-detail-card">
      <img :src="food.image" :alt="food.name" class="food-detail-image" />
      <div class="food-detail-content">
        <h1 class="food-detail-title">{{ food.name }}</h1>
        <p class="food-detail-description">{{ food.description }}</p>
        <p class="food-detail-author">分享者：{{ food.author }}</p>
      </div>
      <!-- 新增菜谱做法教程 -->
      <div class="recipe-steps">
        <h2 class="recipe-steps-title">做法教程</h2>
        <ul class="steps-list">
          <li v-for="(step, index) in food.steps" :key="index" class="step-item">
            <span class="step-number">{{ index + 1 }}</span>
            <span class="step-text">{{ step }}</span>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 整体容器样式 */
.food-detail-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #f9f9f9, #e0e0e0);
  padding: 20px;
}

/* 卡片样式 */
.food-detail-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  background: white;
  border-radius: 15px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  max-width: 800px;
  width: 100%;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.food-detail-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 15px 40px rgba(0, 0, 0, 0.2);
}

/* 图片样式 */
.food-detail-image {
  width: 100%;
  height: 300px;
  object-fit: cover;
  border-bottom: 5px solid #e67e22;
}

/* 内容区域样式 */
.food-detail-content {
  padding: 25px;
  text-align: center;
}

/* 标题样式 */
.food-detail-title {
  font-size: 2.5rem;
  color: #333;
  margin-bottom: 15px;
  font-weight: bold;
}

/* 描述样式 */
.food-detail-description {
  font-size: 1.1rem;
  color: #666;
  line-height: 1.6;
  margin-bottom: 20px;
}

/* 作者样式 */
.food-detail-author {
  font-size: 1rem;
  color: #888;
  font-style: italic;
}

/* 菜谱做法教程样式 */
.recipe-steps {
  width: 100%;
  padding: 20px;
  background: #fafafa;
  border-top: 1px solid #eee;
}

.recipe-steps-title {
  font-size: 1.8rem;
  color: #333;
  margin-bottom: 20px;
  text-align: center;
  font-weight: bold;
}

.steps-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.step-item {
  display: flex;
  align-items: flex-start;
  margin-bottom: 15px;
  padding: 10px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.step-item:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
}

.step-number {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 30px;
  height: 30px;
  background: #e67e22;
  color: white;
  border-radius: 50%;
  font-weight: bold;
  margin-right: 15px;
  flex-shrink: 0;
}

.step-text {
  font-size: 1rem;
  color: #555;
  line-height: 1.5;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .food-detail-card {
    flex-direction: column;
  }

  .food-detail-image {
    height: 200px;
  }

  .food-detail-title {
    font-size: 2rem;
  }

  .food-detail-description {
    font-size: 1rem;
  }

  .recipe-steps-title {
    font-size: 1.5rem;
  }

  .step-text {
    font-size: 0.9rem;
  }
}
</style>