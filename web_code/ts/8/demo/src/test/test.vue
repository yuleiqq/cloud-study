<template>
    <div>
    </div>
</template>
<script lang="ts">
import { defineComponent, getCurrentInstance} from 'vue';
export default defineComponent({
    name: 'test',
    setup() {
        const { proxy } = getCurrentInstance() as any;
        console.log("sdsgfdsgb")
        // 发送ajax 请求
        function ajaxTest() {
            proxy.$http.get('/api/test')
                .then((res: any) => {
                    console.log(res)
                }).catch((error: any) => {
                    console.log(error)
                });
        }
    //调用发送请求
    ajaxTest();
    }
    
})
</script>
<style scoped>

</style>