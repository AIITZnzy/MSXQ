<script setup>
import router from "@/router/index.js";
import ThemeSwitch from "@/components/ThemeSwitch.vue";

</script>

<template>
  <nav class="bg-gray-300 text-black fixed top-0 left-0 w-full shadow-md z-10 shadow-gray-200 dark:bg-gray-800">
    <div class="container mx-auto flex justify-between items-center py-4 px-6">
      <!-- 左侧：网站logo -->
      <div @click="router.push('/')" class="flex items-center cursor-pointer">
        <div class="flex items-center cursor-pointer">
          <img src="@/assets/images/吃货星球logo.png" alt="User Avatar" class="h-8 rounded-full"/>
          <span class="ml-2">吃货星球</span>
        </div>
      </div>

      <div @click="router.push('/')" class="flex items-center cursor-pointer">
        <div class="flex items-center cursor-pointer">
            <span>首页</span>
        </div>
      </div>

      <div @click="router.push('/new')" class="cursor-pointer">
        营养饮食规划
      </div>

      <div @click="router.push('/notes')" class="cursor-pointer">
        笔记
      </div>

      <div @click="router.push('/ai')" class="cursor-pointer">
        智能营养规划AI
      </div>

      <!-- 右侧：用户头像 -->
      <div @click="router.push('/myself')" class="flex items-center gap-2 cursor-pointer">
<!--          加上了:读取的是一个带有返回值的代码-->
<!--        <img v-if="useUserStore().user!== null" :src="useUserStore().user.avator" alt="User Avatar" class="h-8 rounded-full"/>-->
        <img src="https://img.zcool.cn/community/04b4wf2hltswlkpp4qebcs3937.png" alt="User Avatar" class="h-8 rounded-full"/>
        <ThemeSwitch/>
      </div>
      <div @click="router.push('/auth')" class="flex items-center cursor-pointer">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 9V5.25A2.25 2.25 0 0 0 13.5 3h-6a2.25 2.25 0 0 0-2.25 2.25v13.5A2.25 2.25 0 0 0 7.5 21h6a2.25 2.25 0 0 0 2.25-2.25V15M12 9l-3 3m0 0 3 3m-3-3h12.75" />
        </svg>
      </div>
    </div>
  </nav>

  <main class="basic-color mt-16">
    <router-view/>
  </main>

</template>

<style scoped>

</style>