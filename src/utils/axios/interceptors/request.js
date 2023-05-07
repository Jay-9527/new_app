import axios from "axios"
// 请求拦截器
axios.interceptors.request.use(
    config => {
        // 每次发送请求之前判断是否存在token
        // 如果存在，则统一在http请求的header都加上token，这样后台根据token判断你的登录情况，此处token一般是用户完成登录后储存到localstorage里的
        token && (config.headers.Authorization = token)
        return config
    },
    error => {
        return Promise.error(error)
    }
)