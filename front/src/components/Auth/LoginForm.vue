<script setup>
import {post} from "@/net/index.js";
import {reactive} from "vue";
import {message} from "ant-design-vue";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
const props=defineProps(['isLoginComponent'])
const emits=defineEmits(["update:isLoginComponent"]);
const goAnotherForm=()=>{
  emits("update:isLoginComponent",false)
}
const [messageApi, contextHolder] = message.useMessage();
const loginForm=reactive({
  username:'',
  password:''
})
const loginHandler=()=>{
  post('api/user/login',{
    username:loginForm.username,
    password:loginForm.password
  },(message,data)=>{
    messageApi.success(message+'3s后为您转到首页')
    setTimeout(()=>{
      router.push('/')
    },3000)
  },(message,data)=>{
    messageApi.warning(message)
  },(message,data)=>{
    messageApi.error("发生错误请联系管理员")
  })
}

</script>

<template>
  <contextHolder/>
  <div class="loginbg">
    <div class="a">
      <div class="b"></div>
      <div class="c">
        <div class="d text-center">
          <h1>登入</h1>
          <br><br>
          <input type="text" class="e" id="username" v-model="loginForm.username" placeholder="USER NAME">
          <input type="password" id="password" v-model="loginForm.password" class="e" placeholder="PASSWORD">
          <div class="link-container">
            <div @click="goAnotherForm" class="text-blue-400 hover:text-blue-500 hover:underline cursor-pointer">注册账号</div>
            <div>忘记密码？</div>
          </div>
          <button @click="loginHandler()" class="g">Login</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.loginbg{
  /* 设置背景渐变 */
  background-image: linear-gradient(to left,
  #9c88ff,#3cadeb);
  min-height: 100vh; /* 确保背景图片覆盖整个视口高度 */
  display: flex;
  justify-content: center;
}
.a{
  position:relative;
  top: 100px;
  width: 1100px;
  height: 550px;
  box-shadow: 0 5px 15px rgba(0,0,0,.8);
  display: flex;
}
.b{
  width: 800px;
  height: 550px;
  background-image: url('@/assets/images/草莓蛋糕.jpg');
  /* 让图片适应大小 */
  background-size: cover;
}
.c{
  width: 300px;
  height: 550px;
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
}
.d{
  width: 250px;
  height: 500px;
}
.d h1{
  font: 900 30px '';
}
.e{
  width: 230px;
  margin: 20px 0;
  outline: none;
  border: 0;
  padding: 10px;
  border-bottom: 3px solid rgb(80,80,170);
  font: 900 16px '';
}
.f{
  float: right;
  margin: 10px 0;
}
.g{
  position: absolute;
  margin: 20px;
  bottom: 40px;
  display: block;
  width: 200px;
  height: 60px;
  font: 900 30px '';
  text-decoration: none;
  line-height: 50px;
  border-radius: 30px;
  background-image: linear-gradient(to left,
  #9c88ff,#3cadeb);
  text-align: center;
}
.link-container {
  display: flex;
  justify-content: space-between;
  margin: 10px 0;
}
</style>