<template>
    <div>
        {{msg}} <br>
        <button @click="changeMsg ">改变</button>
    </div>
</template>
<script lang="ts">
import {defineComponent,ref} from 'vue';
export default defineComponent({
     
      name:"ref",
      setup(){

         // let msg ="sdfsdf";
         
         const msg = ref("yulei")

         function changeMsg(){
            msg.value +="1508"
            console.log(msg.value)
         }

         return {
            msg,
            changeMsg
         }

      }


})
</script>
<style scoped>
</style>