<script setup>

import LoginForm from "@/components/Auth/LoginForm.vue";
import RegisterForm from "@/components/Auth/RegisterForm.vue";
import {reactive} from "vue";

const options=reactive({
  isLoginComponent:true
})
</script>

<template>
  <div style="text-align: center" >
    <LoginForm v-model:isLoginComponent="options.isLoginComponent" v-if="options.isLoginComponent"/>
    <RegisterForm v-model:isLoginComponent="options.isLoginComponent" v-else/>
  </div>
</template>

<style scoped>

</style>