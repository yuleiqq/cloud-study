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
            <van-grid-item v-for="(video,index) in hotVideos" :key="index">
              <van-image :src="video.img" />
            </van-grid-item>
        </van-grid>
        
    </div>
</template>
<script lang="ts">
import {defineComponent, getCurrentInstance,ref} from 'vue';
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

            return {
                images,
                hotVideos
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