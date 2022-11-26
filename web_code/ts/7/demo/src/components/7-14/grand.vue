<template>
    <div>
        <h2> 我是grand 组件</h2>
        当前的颜色{{color}}

        <button @click="changeColor('yellow')">黄色</button>
        <button @click="changeColor('red')">红色</button>
        <button @click="changeColor('green')">绿色</button>

    </div>
    <son></son>
</template>
<script lang="ts">
import {defineComponent,ref,provide} from 'vue';
import Son from './son.vue'


export default defineComponent({

    name: 'Grand',
    components:{
        Son
    },
    setup(props, ctx) {
        
        const color = ref('yellow');
        provide('color',color)
        function changeColor(val:string){
                color.value= val;
        }
        return {
            color ,
            changeColor
        }
    },

})
</script>
<style scoped>
</style>