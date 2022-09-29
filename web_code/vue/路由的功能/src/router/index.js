 
  import VueRouter from 'vue-router'
  import Home from '../components/Home.vue'
  import Course from '../components/Course.vue'


  const router =  new VueRouter ({

    routes: [
        {
               path: '/home',
               component: Home
        },
        {
            path: '/course',
            component: Course
     }
    ]
  });

  export default router;



