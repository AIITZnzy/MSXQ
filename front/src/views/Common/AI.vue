<script setup>
import { onMounted, ref } from 'vue'

import { get, post } from '@/net/index.js'

const userInput = ref('');
const messages = ref([]);

// 模拟 AI 响应
const askAI = async () => {
  if (!userInput.value.trim()) return;

  // 用户消息
  messages.value.push({ text: userInput.value, isUser: true });

  // 模拟 AI 的回答
  const aiAnswer = ref("你好，我是AI大模型！");
  await get('/ai/chat', {
    message: userInput.value
  }, (message,data) => {
    aiAnswer.value = data;
    console.log(userInput.value + ":" + aiAnswer.value);
    // 清空输入框
    userInput.value = '';
    messages.value.push({ text: aiAnswer.value, isUser: false });
  });
};
onMounted(()=>{
  messages.value.push({ text: "**欢迎来到AI问答界面**，你可以向我问任何问题，我会尽力回答  **（AI也会出错！回答仅供参考！）** ", isUser: false });
})
</script>


<template>
  <div class="min-h-screen flex flex-col justify-between bg-gradient-to-b from-indigo-50 to-blue-200 p-6">
    <!-- 聊天窗口 -->
    <div class="flex-1 overflow-auto p-4 space-y-4 bg-white rounded-lg shadow-lg shadow-indigo-500/30">
      <!-- 渲染聊天消息 -->
      <div v-for="(message, index) in messages" :key="index" class="flex items-start animate-fade-in"
           :class="message.isUser ? 'flex-row-reverse' : 'flex-row'">

        <!-- 用户头像 -->
        <div v-if="message.isUser" class="flex-shrink-0 ml-auto">
          <div class="w-10 h-10 rounded-full bg-gradient-to-b from-blue-400 to-blue-600 text-white text-lg font-bold flex items-center justify-center">
            User
          </div>
        </div>

        <!-- AI 头像 -->
        <div v-else class="flex-shrink-0 mr-auto">
          <div class="w-10 h-10 rounded-full bg-gradient-to-b from-gray-400 to-gray-600 text-white text-lg font-bold flex items-center justify-center">
            AI
          </div>
        </div>

        <!-- 消息内容 -->
        <div :class="message.isUser ? 'flex-1 text-right' : 'flex-1'" class="p-2">
          <!-- 消息气泡 -->
          <div class=" p-6 rounded-xl shadow-xl max-w-xs mx-auto relative transition-all duration-300 ease-in-out transform hover:scale-105"
               :class="message.isUser ? 'bg-blue-100 border border-blue-300 mr-10' : 'bg-gray-100 border border-gray-300 ml-10'">
            <!-- 渲染 Markdown 内容 -->
            <div >{{message.text}}</div>
          </div>
        </div>
      </div>
    </div>

    <!-- 输入框始终显示 -->
    <div class="mt-4 flex items-center space-x-4">
      <div class="flex-1">
        <input
            v-model="userInput"
            type="text"
            placeholder="Ask me anything..."
            class="w-full p-6 border-2 border-gray-300 rounded-3xl bg-white shadow-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition-all duration-300 ease-in-out hover:shadow-2xl placeholder-gray-500 text-lg"
            @keydown.enter="askAI"
        />
      </div>
      <button
          @click="askAI"
          class="p-6 bg-gradient-to-r from-blue-500 to-indigo-600 text-white rounded-3xl hover:bg-gradient-to-r hover:from-indigo-600 hover:to-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50 transition duration-300 ease-in-out transform hover:scale-105"
      >
        <span class="text-xl">发送</span>
      </button>
    </div>
  </div>
</template>

<style scoped>
/* 自定义动画效果 */
@keyframes fadeIn {
  0% {
    opacity: 0;
    transform: translateY(20px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}

.animate-fade-in {
  animation: fadeIn 0.5s ease-out;
}

input:focus, button:focus {
  outline: none;
}

/* 保证输入框始终在页面底部 */
div.flex-1 {
  flex-grow: 1;
  overflow-y: auto;
}

div.mt-4 {
  margin-top: 20px;
}
</style>