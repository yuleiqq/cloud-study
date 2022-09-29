<template>
  <div class="bg">
      <div class="nav">
        <a href="">首页</a>
        <a href="">课程</a>
      </div>
      <div class="content">

      </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  data(){
    return {};
  },
  components: {
}, 
}
</script>

<style>
   .bg {
     width: 200px;
     margin: 0 auto;
   }
   .nav {
      background-color: orange;
      display: flex;
      justify-content: space-around;
   }
   .content{
     margin-top: 10px;
     height: 200px;
     width: 200px;
     border: 1px solid black;
   }

</style>