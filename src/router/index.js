
import * as VueRouter from 'vue-router'
// 导入视图模板
import Login from "../views/Login.vue"
import Index from "../views/Index.vue"
import Register from "../views/Register.vue"

export const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes: [
        { path: '/', name: "Login", component: Login },
        { path: '/index', component: Index },
        { path: '/register', name: "Register", component: Register }
    ]
});


export default router