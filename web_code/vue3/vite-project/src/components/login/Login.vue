<template>

    <div class='front'>
        <el-input v-model="user.username" placeholder="用户名" />
        <el-input v-model="user.password" placeholder="密码" />

        <div class="button">
            <el-button type="primary" @click="login">登录</el-button>
        </div>
    </div>
</template>
<script lang="ts">
import {defineComponent, getCurrentInstance,reactive} from 'vue';
import { ElMessage } from 'element-plus'

export default defineComponent({

    name: 'Login',

    setup(){
        
        //登录使用的账号信息
        const user = reactive({
                username: '',
                password:''
            });

       const {proxy}:any = getCurrentInstance();
       const http = proxy.$http;

        function  login (){

            //请求消息体
            const param =  {
                userName: user.username,
                passWord: user.password
            };

            //登录请求
            http.post('/api/doLogin',param).then((res:any)=>{
                    console.log(res.data)  

                    if(res.data.code==0){
                            //消息提示
                    ElMessage({
                        showClose: true,
                        message: res.data.msg,
                        type: 'success',
                    })

                    //成功之后，跳转
                    proxy.$router.push({
                           path: '/home'
                    });

                    }else{

                        ElMessage.error(res.data.msg)
                    }
                    
            }).catch((error:any)=>{
                console.log('报错:'+error)  
                ElMessage.error('报错:'+error)  
            });
        }
       
        //暴露对象, 供使用
       return {
        user,
        login
       }

    }

})
</script>
<style scoped>
  .button{
    margin-top: 20px;
  }



</style>