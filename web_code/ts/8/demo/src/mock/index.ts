import Mock from 'mockjs'

Mock.setup({
    timeout: '200-2000', //毫秒为单位
})

//设置拦截路径
//mock中有三个方法：分别是  拦截路径的正则表达式，请求的方法，  和响应数据的函数
// Mock.mock(/\/api\/register/,'get',homeApi.getHomeData);

//登陆接口
Mock.mock(/\/api\/test/,'get',(req: any) => {
    console.log(req);

    //return中的数据
    return {
        code: 0,
        data: {
            msg: 'mock测试成功~~~~'
        }
    }
})

Mock.mock(/\/api\/login/,'post',(req: any) => {

    //定义一个账号池
    const userPools = [
        {name:'yulei', password:"123456"},
        {name:'tom', password:"123456"}
    ]

    //json 格式化
    const {name,password} = JSON.parse(req.body);
    console.log(name);
    console.log(password );

    const userLength = userPools.filter((res)=>{
      return   res.name==name;
    });

    if(userLength.length>0){
        if(userLength[0].password==password){
            return {
                code: 0,
                data:{
                    msg: '登录成功!'
                }
            }
        }else{ 
            return {
                code: -1,
                data:{
                    msg: '密码错误!'
                }
            }
        }

    }else {
        return {
            code: -1,
            data:{
                msg: '账号不存在'
            }
        }
    }

    console.log(req.body);

    //return中的数据
    return {
        code: 0,
        data: {
            msg: '测试登录成功!~~~~'
        }
    }

})





export default Mock

