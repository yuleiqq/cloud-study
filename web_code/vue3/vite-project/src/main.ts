import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import http from './axios/axiosConfig'
import router from './router'

const app =  createApp(App);

app.config.globalProperties.$http=http;

app.use(ElementPlus).use(router);

app.mount('#app');
