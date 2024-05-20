import axios from 'axios'

// 创建可一个新的axios对象
const request = axios.create({
    baseURL: 'http://localhost:9090',   // 后端的接口地址  ip:port
    timeout: 30000
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    // let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
    // config.headers['token'] = 'token'  // 设置请求头

    return config
}, error => {
    console.error('request error: ' + error) // for debug
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;  // 获取接口返回的数据

        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {  // 如果返回的数据是字符串,则转换为json对象,因为前端只能处理json对象
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.error('response error: ' + error) // for debug
        return Promise.reject(error)
    }
)


export default request