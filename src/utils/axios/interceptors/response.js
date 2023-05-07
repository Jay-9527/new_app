import axios from "axios"
// 响应拦截器
axios.interceptors.response.use(response => {
    // 如果返回的状态码为200，说明接口请求成功，可以正常拿到数据
    // 否则的话抛出错误
    if (response.status === 200) {
        if (response.data.code === 511) {
            // 未授权调取授权接口
        } else if (response.data.code === 510) {
            // 未登录跳转登录页
        } else {
            return Promise.resolve(response)
        }
    } else {
        return Promise.reject(response)
    }
}, error => {
    // 我们可以在这里对异常状态作统一处理
    if (error.response.status) {
        // 处理请求失败的情况
        // 对不同返回码对相应处理
        return Promise.reject(error.response)
    }
})