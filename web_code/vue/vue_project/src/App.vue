<template>
<h1>{{mess}}</h1>
  <hr/>
  <!-- <HelloWorld  :msg ="123 " :transmitt="tt"/> -->
  <hr/>
  <FirstCustomer  :handleChange="handleChange"  :list="list"></FirstCustomer>
  <hr/> 
  <SecondCustomer :deleteClick="deleteClick"></SecondCustomer>
  
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import FirstCustomer from './components/FirstCustomer.vue'
import SecondCustomer from './components/SecondCustomer.vue'

export default {
  name: 'App',
  data(){
      return {
          mess: "",
          list: ['tom','lisi','zhangsan']
      }
  },
  components: {
    // HelloWorld,
    SecondCustomer,
    FirstCustomer
},
  methods: {   
     tt(i){
        this.mess = i
     },
     handleChange(i){
        console.log(i)
     },
     deleteClick(){
         this.list.pop(); 
     }
  }
}
</script>

<style>

</style>