<script setup>
import { RouterLink, RouterView } from 'vue-router'
import {useThemeStore} from "@/stores/theme.js";
const themeStroe = useThemeStore();
themeStroe.initTheme();
</script>

<template>
  <RouterView />
</template>

<style scoped>


</style>
