<template>    
    <h2 @click="change">{{ddos}}</h2>
</template>

<script>
export default {
    data() {
        return {
            name: '小弟课堂 ',
            ddos: this.msg
        }
    },

    // props: ["msg"],
    props: ["msg","transmitt"],
    methods: {
        change(){
            this.ddos = "香蕉"
        }

    },

    mounted() {
        console.log(typeof this.msg);
        this.transmitt(this.name)
    },
}
</script>

<style scoped>

</style>
