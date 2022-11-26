<template>
    <div>
        <button @click="emitXXX">分发事件</button>
        {{msg}}
    </div>
</template>
<script lang="ts">
import {defineComponent} from 'vue';
export default defineComponent({

    name:"setup2",
    props:['msg'], // 需要先使用props 接收父组件传递过来的参数
    setup(f, ctx) {

        //打印父组件的数据
        console.log(f.msg);

        function  emitXXX(){
            //调用父组件的函数
            ctx.emit('xxx','++');
        }
        return {
            emitXXX 
        }
        }
    })
</script>
<style scoped>
</style>




