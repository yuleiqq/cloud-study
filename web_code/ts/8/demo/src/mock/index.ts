import Mock from 'mockjs'

Mock.setup({
    timeout: '200-2000', //毫秒为单位
})

//设置拦截路径
//mock中有三个方法：分别是  拦截路径的正则表达式，请求的方法，  和响应数据的函数
// Mock.mock(/\/api\/register/,'get',homeApi.getHomeData);

//登陆接口
Mock.mock(/\/api\/test/, 'get', (req: any) => {
    console.log(req);

    //return中的数据
    return {
        code: 0,
        data: {
            msg: 'mock测试成功~~~~'
        }
    }
})

Mock.mock(/\/api\/login/, 'post', (req: any) => {

    //定义一个账号池
    const userPools = [
        { name: 'yulei', password: "123456" },
        { name: 'tom', password: "123456" }
    ]

    //json 格式化
    const { name, password } = JSON.parse(req.body);
    console.log(name);
    console.log(password);

    const userLength = userPools.filter((res) => {
        return res.name == name;
    });

    if (userLength.length > 0) {
        if (userLength[0].password == password) {
            return {
                code: 0,
                data: {
                    msg: '登录成功!',
                    token: 'xdclass'
                }
            }
        } else {
            return {
                code: -1,
                data: {
                    msg: '密码错误!'
                }
            }
        }

    } else {
        return {
            code: -1,
            data: {
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


Mock.mock(/\/api\/homePhoto/, 'get', () => {
    return {
        code: 0,
        data: [
            {
                title: "轮播视频",
                bannerList: [
                    {
                        img: "https://file.xdclass.net/video/2021/1-lbt/VIP/vip1299.png",
                        details: '超级会员',
                        id: 1
                    },
                    {
                        img: "https://file.xdclass.net/video/2021/62-paas/lbt-paas.png",
                        details: '工业级微服务项目',
                        id: 2
                    },
                    {
                        img: "https://file.xdclass.net/video/2021/aliyun/03lbt.png",
                        details: '阿里云服务器',
                        id: 3
                    }
                ]
            },
            {
                title: '热门视频',
                videoList: [
                    {
                        img: "https://file.xdclass.net/video/2021/61-RabbitMq/zt.jpg",
                        details: '容器化分布式集群实战',
                        id: 4
                    },
                    {
                        img: "https://file.xdclass.net/video/2020-12%20%E5%AE%98%E7%BD%91%E8%B6%85100k%E4%B8%BB%E5%9B%BE%E6%9B%B4%E6%96%B0/%E4%B8%BB%E5%9B%BE/57-alibabacloud.jpg",
                        details: 'AlibabaCloud',
                        id: 5
                    },
                    {
                        img: "https://file.xdclass.net/video/2020/SSM/zt-ssm.png",
                        details: 'ssm新版',
                        id: 6
                    }, {
                        img: "https://file.xdclass.net/video/2020-12%20%E5%AE%98%E7%BD%91%E8%B6%85100k%E4%B8%BB%E5%9B%BE%E6%9B%B4%E6%96%B0/%E4%B8%BB%E5%9B%BE/37-yhq.jpeg",
                        details: '微服务Dubbo',
                        id: 7
                    }, {
                        img: "https://file.xdclass.net/video/2021/59-Postman/Postman.png",
                        details: 'Postman',
                        id: 8
                    }, {
                        img: "https://file.xdclass.net/video/2021/60-MLS/cover.jpeg",
                        details: 'MybatisPlus',
                        id: 9
                    }, {
                        img: "https://file.xdclass.net/video/2020/SSM/zt-ssm.png",
                        details: 'ssm新版',
                        id: 10
                    }, {
                        img: "https://file.xdclass.net/video/2021/61-RabbitMq/zt.jpg",
                        details: 'RabbitMQ',
                        id: 11
                    }
                ]
            }
        ]
    }
})




export default Mock

