
import * as VueRouter from 'vue-router'
// 导入视图模板
import Login from "../views/Login.vue"
import Index from "../views/Index.vue"
import Register from "../views/Register.vue"


// const routerHistory = createWebHistory(import.meta.env.BASE_URL)
// const router = createRouter({
//     mode: routerHistory,
//     routes: [
//         { path: '/', name: "Login", component: Login },
//         { path: '/index', name: "index", component: Index }
//     ]
// });


export const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes: [
        { path: '/', name: "Login", component: Login },
        { path: '/index', component: Index },
        { path: '/register', name: "Register", component: Register }
    ]
});


export default router