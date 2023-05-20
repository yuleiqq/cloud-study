<template>
    <router-view/>
    <home-bottom></home-bottom>
</template>
<script lang="ts">
import {defineComponent} from 'vue';
import HomeBottom from '@/components/Home/components/bottom.vue'
export default defineComponent({
    name: 'Home',
    components:{
        HomeBottom
    }
})
</script>
<style scoped>
</style>