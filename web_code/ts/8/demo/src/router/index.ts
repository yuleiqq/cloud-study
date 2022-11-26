import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '@/components/HelloWorld.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    redirect: 'login'
  },

  {
    path: '/login',
    name: 'login',
    component: ()=>import('../components/login/Login.vue')
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
