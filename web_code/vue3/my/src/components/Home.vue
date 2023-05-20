<template>
    <div>
        <div>{{ state.count }}</div>
        <button @click="increment2">
           第二个 {{ count2 }} <!-- 无需 .value -->
          </button>
    </div>
</template>
<script lang="ts">
import {defineComponent} from 'vue';
import { reactive } from 'vue'
import { ref } from 'vue'

export default defineComponent({
    name: 'Home',
    
    setup(){
        //reactive 处理的是一个对象
        const state = reactive({ count: 0 });
        function increment() {
         state.count++
        }
        const raw = {}
        // reactive() 返回的是一个原始对象的 Proxy，它和原始对象是不相等的：
        const proxy = reactive(raw);
        console.log(proxy === raw); // false


        //ref
        const count2 =ref(0);
        function increment2() {
            count2.value++
         }


        return {
            state,
            increment,
            count2,
            increment2
        }
    }
})
</script>
<style scoped>
</style>