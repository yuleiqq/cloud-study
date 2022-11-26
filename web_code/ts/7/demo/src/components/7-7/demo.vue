<template>
    <div>
        name:{{obj.name}} <br>

        <ul>
            <li v-for="(item, index) in obj.courses" :key="index">{{item}}</li>
        </ul>
        
        <button @click="changeName">改变名字</button>
        <button @click="changeCourse">改变课程</button>


    </div>
</template>
<script lang="ts">
import {defineComponent,reactive} from 'vue';
export default defineComponent({
    name: 'reactive',
    setup(){
        
        // const obj ={
        //     name:'小弟',
        //     courses:['java','spring','python']
        // }

        //使用reactive 将其变成响应数据,父组件可展示.
        const obj = reactive({
            name:'小弟',
            courses:['java','spring','python']
        });

        function changeName(){
            obj.name +='+';
            console.log(obj.name)
        }

        function changeCourse(){
            obj.courses[0] +='+';
            console.log(obj.courses[0])

         }
       //访问：
       console.log(obj);

       return {
            obj,
            changeCourse,
            changeName
       }
    }
})
</script>
<style scoped>
</style>