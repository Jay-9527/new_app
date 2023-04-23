import axios from "axios";

const service = axios.create({
    // baseURL: import.meta.env.VITE_APP_URL,
    baseURL: 'http://localhost:8080/',
    timeout: 6000,
    headers: { 'X-Custom-Header': 'foobar' }
})

export default service
