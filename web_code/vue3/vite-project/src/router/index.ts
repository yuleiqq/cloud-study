import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
const routes: Array<RouteRecordRaw> = [
    {
      path: '/',
      name: 'root',
      redirect: 'login'
    },
  
    {
      path: '/login',
      name: 'login',
      component: ()=>import('../components/login/Login.vue')
    },
    {
      path: '/home',
      name: 'Home',
      component: ()=>import('../components/HelloWorld.vue'),
    },
  ]

  const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
  })
  
  
  export default router