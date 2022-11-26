import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vant from 'vant';
import 'vant/lib/index.css';
import http from './axios/axiosConfig'

import './mock'

const app = createApp(App);

app.config.globalProperties.$http=http;

app.use(store).use(router).use(vant).mount('#app')



