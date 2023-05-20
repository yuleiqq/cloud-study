
import axios from 'axios';

//创建一个axios实例对象
const http = axios.create(
    {
        //配置响应超时时常
        // baseURL:'http://localhost:8080/api'
    }
);

//设置请求拦截器
http.interceptors.request.use((req) => {
    // const token=window.sessionStorage.getItem('token');
    return req;
}, (error) => {
    return error;
})


//设置响应拦截器
http.interceptors.response.use((res) => {
    return res;
}, (error) => {
    return error;
})

//把http实例对象暴露出去
export default http;
