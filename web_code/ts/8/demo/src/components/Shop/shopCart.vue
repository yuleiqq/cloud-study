<template>
    <!-- 空状态的组件 -->
    <div v-if="goodsNum==0">
        <!-- 搜索提示 -->
        <van-empty image="search" description="当前购物车为空" />
    </div>
    <div v-else>
        <div class="goodstop">
            <h2>购物车</h2>
        </div>
        
        <!-- 购物车商品展示区域 -->
        <div class="goodsCenter">
            <div class="goodsItem">
                <div class="left">
                    <img src="https://file.xdclass.net/video/2021/59-Postman/Postman.png"/>
                </div>
                <div class="center">阿里巴巴</div>
                <div class="right">
                    <van-stepper v-model="value" theme="round" button-size="22" disable-input />
                </div>
            </div>
        </div>
        
        <div class="goodsBottom">
             <van-button plain hairline type="success"  color="grey"  >下单</van-button>
             <van-button plain hairline type="success"  color="grey"  >清空</van-button>
        </div>





    </div>


</template>
<script lang="ts">
import {defineComponent,ref,computed} from 'vue';

import  {useStore} from 'vuex'
export default defineComponent({
    name: 'ShopCart',
    setup(){
        const store = useStore();
        //购物车中商品的总数
        const goodsNum = ref(
            computed(()=>{
             return  store.getters.getGoodsNum;
            }
        ));

        const value =0;

        return {
            goodsNum,
            value
        }

    }

})
</script>
<style scoped>
.goodstop{
    margin-top: 3rem;
    margin-bottom: 2rem;
}

.goodsCenter{
    position: relative;
    width: 100%;
    min-height: 5rem;
}
.goodsItem{
    position: relative;
    width: 100%;
    height: 5rem;
    margin-top: 1rem;
}
img{
    height: 100%;
}

.left{
     height: 5rem;
     position: absolute;
     left:1.5rem
}
.center{
    position: absolute;
    top: 0.5rem;
    right: 1.5rem;
}

.right{
    position: absolute;
    bottom: 0.5rem;
    right: 1.5rem;
}

.goodsBottom{
    margin-top:2rem;
    margin-bottom: 5rem;
}

</style>