<template>
    <div>
        <van-swipe :autoplay="3000" lazy-render>
            <van-swipe-item v-for="image in images" :key="image">
              <img :src="image" />
            </van-swipe-item>
          </van-swipe>
    </div>

    <div>
        <van-form >
            <van-cell-group inset>
              <van-field
                v-model="user.name"
                name="用户名"
                label="用户名"
                placeholder="用户名"
                :rules="[{ required: true, message: '请填写用户名' }]"
             
              />
              <van-field
                v-model="user.pwd"
                type="password"
                name="密码"
                label="密码"
                placeholder="密码"
                :rules="[{ required: true, message: '请填写密码' }]"
                
              />
            </van-cell-group>
          </van-form>
    </div>
    <div style="margin: 16px;">
        <van-button plain type="primary"    @click="clickButton('1')">提交</van-button>
        <van-button plain type="primary"     @click="clickButton('2')">清空</van-button>
    </div>
</template>
<script lang="ts">
import { useWindowSize } from '@vant/use';
import { S } from 'mockjs';
import {defineComponent, proxyRefs, reactive,getCurrentInstance} from 'vue';
export default defineComponent({

        name:'Login',
        setup(){
            const {proxy}:any  = getCurrentInstance();

            const images = reactive([
                'https://file.xdclass.net/video/2021/1-lbt/VIP/vip1299.png',
                'https://file.xdclass.net/video/2021/62-paas/lbt-paas.png',
                'https://file.xdclass.net/video/2021/59-Postman/59lbt.png'
            ])

        
            
            //发送登录请求
            function toLogin(){   
                console.log(proxy)

                proxy.$http.post('/api/login',{
                    name: user.name,
                    password: user.pwd

                })  
                .then((res:any)=>{
                    console.log(res)
                    if(res.data.code==0){
                       proxy.$toast.success(res.data.data.msg);
                       window.sessionStorage.setItem("token",res.data.data.token);
                        
                       proxy.$router.push({
                           path: '/home',
                       });
                       
                    }else{
                       proxy.$toast.fail(res.data.data.msg);
                    }
                }).
                catch((error:any)=>{
                    console.log(error)
                })
            }

            function clickButton(value:string){
                console.log(value)
                if(value=="1"){
                    toLogin();
                }
            }

            //暴露对象
            return {
                images,
                user,
                clickButton
            }
        }
})
</script>
<style scoped>
    img{
        width: 100%;
    }

</style>