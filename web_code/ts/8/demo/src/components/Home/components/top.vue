<template>
    <!-- 轮播图 -->
    <div>
        <van-swipe :autoplay="3000">
            <van-swipe-item v-for="image in images" :key="image">
              <img :src="image.img" />
            </van-swipe-item>
          </van-swipe>
    </div>

    热门视频
    <!-- 热门视频 -->
    <div class="hotVideo">
        <van-grid :border="false" :column-num="2">
            <van-grid-item v-for="(video,index) in hotVideos" :key="index" @click="tocart(video)">
              <van-image :src="video.img" />
            </van-grid-item>
        </van-grid>
        
    </div>
</template>
<script lang="ts">
import {defineComponent, getCurrentInstance,ref} from 'vue';
import {useStore} from 'vuex'
export default defineComponent({
        name: 'HomeTop',
        setup(){
            const {proxy}:any = getCurrentInstance();

            const images = ref<any>(null);
            const hotVideos = ref<any>(null);

            //获取首页轮播和热门视频的资源
            function getSource(){

                proxy.$http.get("/api/homePhoto")
                .then((res:any)=>{
                    console.log(res.data)
                    if(res.data.code == 0 ){ 
                        images.value = res.data.data[0].bannerList;
                        hotVideos.value = res.data.data[1].videoList;
                        
                    }
                })
                .catch((error:any)=>{
                    console.log(error.data)
                });
            }
            getSource();


            const store= useStore();
            //定义添加商品到购物车方法
            function tocart(item:any){
              console.log(item)
              proxy.$toast.success('添加购物车success!');
              //调用vuex 中mutation定义的tocart 方法
              store.commit('tocart',item);
              //查询
              store.commit('getcart');

            }

            return {
                images,
                hotVideos,
                tocart
            }

        }


})
</script>
<style scoped>

  img{
    width: 100%;
  }
  .hotVideo{
    margin-bottom: 3rem;
  }
</style>