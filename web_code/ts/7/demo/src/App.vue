<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <!-- <HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/> -->

  <!-- <set-up></set-up> -->
  
  <!-- <reactive></reactive> -->

  <!-- <ref></ref> -->
  <!-- {{msg}} <br>
  <hr>
  <setup-2 :msg="msg"   @xxx="xxx" ></setup-2> -->

  <grand></grand>







  
</template>

<script lang="ts">
import { defineComponent,ref} from 'vue';
import HelloWorld from './components/HelloWorld.vue';

// import SetUp from './components/7-5/demo.vue';
// import Ref from './components/7-6/demo.vue';
// import Reactive from './components/7-7/demo.vue'
// import setup2 from './components/7-9/demo.vue'
import Grand from './components/7-14/grand.vue'




export default defineComponent({
  name: 'App',
  components: {
    // HelloWorld,
    // SetUp,
    // Reactive,
    // setup2
    Grand
  },

  setup(props, ctx) {

    const msg = ref("我是msg");

    function xxx(value:string){
       msg.value += value;
    }
    return {
      xxx ,
      msg
    }
  }

});
</script>

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
