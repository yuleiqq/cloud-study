<template>
    <div>
        {{msg}}
    </div>
</template>
<script lang="ts">
import {defineComponent} from 'vue';

export default defineComponent({

    name:'7-5',

    setup() {
        console.log("setup执行了");
        let msg ="xlsd";
        return {
            msg 
        }
    },

})
</script>
<style scoped>
</style>