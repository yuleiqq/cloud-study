<template>
    <h1 @click="handleClick">第1个组件</h1>
    <li v-for="(item, index) in list" :key="index">{{item}}</li>
</template>

<script>
    export default {

        data(){
            return {
                text: "第一个组件的值"
            }
        },
        props: ["list"],
        methods: {
             
            handleClick(){
                 this.$emit('xd',this.text)
            }
        }
    }
</script>

<style lang="scss" scoped>

</style>