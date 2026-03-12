<script setup>
import {onMounted, reactive} from 'vue';
import router from "@/router/index.js";
import {get} from "@/net/index.js";

const options = reactive({
  blogs: []
})

const initData = () => {
  get('api/blog/getInroduce', {}, (message, data) => {
    options.blogs = data
  })
}

onMounted(() => {
  initData()
})
</script>


<template>
  <div class="min-h-screen bg-gray-50 p-8">
    <!-- 标题 -->
    <h1 class="text-3xl font-bold text-center mb-8 text-gray-800">我的笔记</h1>

    <!-- 笔记列表 -->
    <div class="max-w-2xl mx-auto space-y-6">
      <div v-for="blog in options.blogs" :key="blog.blog.id"
           class="bg-white rounded-xl p-6 shadow-md hover:shadow-lg transition-shadow duration-300">
        <h1 class="text-2xl font-bold text-blue-600 mb-4">{{ blog.blog.title }}</h1>
        <p class="text-gray-700 leading-relaxed">{{ blog.blog.content }}</p>
        <div class="mt-4 text-right">
          <span class="text-sm text-gray-500">作者：{{ blog.username }}</span>
        </div>
      </div>
    </div>

    <!-- 添加笔记按钮 -->
    <div class="fixed bottom-8 left-1/2 transform -translate-x-1/2">
      <button @click="router.push('/addnotes')"
              class="bg-blue-500 text-white py-3 px-6 rounded-full shadow-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 transition-all duration-300">
        添加笔记
      </button>
    </div>
  </div>
</template>


<style scoped>
/* 自定义样式 */
.min-h-screen {
  min-height: 100vh;
}

.max-w-2xl {
  max-width: 42rem;
}

.mx-auto {
  margin-left: auto;
  margin-right: auto;
}

.bg-white {
  background-color: white;
}

.rounded-xl {
  border-radius: 1rem;
}

.shadow-md {
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
}

.text-blue-600 {
  color: #2563eb;
}

.text-gray-700 {
  color: #4a5568;
}

.text-gray-500 {
  color: #6b7280;
}

.hover\:shadow-lg:hover {
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
}

.transition-shadow {
  transition: box-shadow 0.3s ease;
}

.fixed {
  position: fixed;
}

.bottom-8 {
  bottom: 2rem;
}

.left-1\/2 {
  left: 50%;
}

.transform {
  transform: translateX(-50%);
}

.py-3 {
  padding-top: 0.75rem;
  padding-bottom: 0.75rem;
}

.px-6 {
  padding-left: 1.5rem;
  padding-right: 1.5rem;
}

.rounded-full {
  border-radius: 9999px;
}

.focus\:ring-2:focus {
  box-shadow: 0 0 0 2px rgba(59, 130, 246, 0.5);
}
</style>