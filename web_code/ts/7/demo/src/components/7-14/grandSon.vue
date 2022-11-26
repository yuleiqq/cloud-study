<template>
    <div>
        <h2 :style="{color}">我是GrandSon组件</h2>
    </div>
</template>
<script lang="ts">
import {defineComponent,inject} from 'vue';
export default defineComponent({

    name: 'GrandSon',
    setup(){
        const color:undefined =inject("color");
        return {
            color,
        }
    }

})
</script>
<style scoped>
</style>