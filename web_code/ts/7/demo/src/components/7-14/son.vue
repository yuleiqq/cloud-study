<template>
    <div>
         <h2>我是组件son </h2>
         <grand-son></grand-son>
    </div>
</template>
<script lang="ts">
import {defineComponent} from 'vue';
import GrandSon from './grandSon.vue'
export default defineComponent({
    name: "Son",
    components:{
        GrandSon  
    }
    
})
</script>
<style scoped>
</style>