<template>
    <h1>第2个组件</h1>
    <button @click="deleteClicks">删除  </button>
</template>

<script>
    export default {

        methods:{
            deleteClicks(){
                this.deleteClick();   
            }

        },
        props: ["deleteClick"]

        
    }
</script>

<style lang="scss" scoped>

</style>