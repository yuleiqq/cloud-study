<template>
    <div>
        <van-tabbar v-model="active">
            <van-tabbar-item icon="home-o" to="HomeTop">主页</van-tabbar-item>
            <van-tabbar-item icon="cart-o" :badge="goodsNum"  to="ShopCart">购物车</van-tabbar-item>
            <van-tabbar-item icon="setting-o">退出</van-tabbar-item>
          </van-tabbar>
    </div>
</template>
<script lang="ts">
import {defineComponent,ref,computed} from 'vue';
import {useStore} from 'vuex'

export default defineComponent({    

    name: 'HomeBottom',
    setup(){
        const store = useStore();
        const active  = ref(0);

        const goodsNum = ref(computed(()=>{
           return  store.getters.getGoodsNum;
        }));

        return {
            active,
            goodsNum
        }
    }
})
</script>
<style scoped>
</style>