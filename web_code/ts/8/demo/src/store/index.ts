import { createStore } from 'vuex'

export default createStore({
  state: {
    cartarray:[]
  },
  getters: {

    getGoodsNum(state:any){
      let num = 0;
      state.cartarray.forEach((val:any) => {
            num += val.cartCount;
      });

      return num;
    }



  },

  mutations: {
      //添加商品到购物车
      tocart(state:any,tag:any){
          console.log("我在vuex中: "+tag);

          const goods:any = state.cartarray.find((val:any)=>{
            return  val.id = tag. id 
          });

          if(goods){
             goods.cartCount +=1; 
          }else{
            const item = {
              id: tag.id,
              title: tag.details,
              cartCount:1,
              img: tag.img
            }
            state.cartarray.push(item)
          }
      },

      //查询购物车信息
      getcart(state:any){
        console.log("购物车信息:"+state.cartarray)
      }


  },
  actions: {

    
  },
  modules: {
  }
})
