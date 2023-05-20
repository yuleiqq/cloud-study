import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '@/components/HelloWorld.vue'

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
    component: ()=>import('../components/Home/home.vue'),
    redirect: 'HomeTop',
    children:[
      {
        path: '/homeTop',
        name: 'HomeTop',
        component: ()=>import('../components/Home/components/top.vue')
      },
      {
        path: '/shopCart',
        name: 'ShopCart',
        component: ()=>import('../components/Shop/shopCart.vue')
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


//路由导航守卫
router.beforeEach((to,from,next )=>{

  const token = window.sessionStorage.getItem("token");
  if(to.path == '/login'){
      next();
  }else {
    if(token){
        next();
    }else{
      next('login')
    }
  }

});

export default router
