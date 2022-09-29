import { createApp } from 'vue'
import App from './App.vue'

import VueRouter from 'vue-router'
import router from './router/index'

createApp(App).mount('#app').use(VueRouter).mount(router)
