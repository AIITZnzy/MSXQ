import '@/assets/css/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from "axios";
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import App from './App.vue'
import router from './router'

const app = createApp(App)
axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.withCredentials = true

//后端基础url 之后在请求时只用填写路径 Axios会自动以该url为基础添加路径
app.config.globalProperties.$axios = axios
//应用axios配置

app.use(createPinia())
app.use(router).use(ElementPlus).use(Antd)

app.mount('#app')
