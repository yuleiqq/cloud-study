<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <!-- <HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/> -->
   <!-- <Home></Home> -->

   <computed-demo></computed-demo>
</template>

 <script lang="ts">
import { defineComponent } from 'vue';
// import HelloWorld from './components/HelloWorld.vue';
// import Home  from './components/Home.vue'
import ComputedDemo from './components/computed/ComputedDemo.vue'

export default defineComponent({
  name: 'App',
  components: {
  //   HelloWorld
  // Home
  ComputedDemo
  }
});
</script> -->


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
