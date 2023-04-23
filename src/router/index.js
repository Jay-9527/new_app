
import * as VueRouter from 'vue-router'


// 导入视图模板
// 定义路由路径对象：
const RoutsList = [
    // 登录界面
    {
        path: '/login',
        component: () => import('../views/Login.vue'), 
        hidden: true
    },
    // 错误界面
    {
        path: '/404',
        component: () => import('../views/404.vue'),
        hidden: false
    },
    // 管理界面
    {
        path: '/index',
        name: 'index',
        component: () => import('../views/Index.vue'),
        children: [
            {
                path: 'list-home',
                component: () => import('../views/adminManager/page/page1.vue'),
                hidden: false
            },
            {
                path: 'list-worker',
                component: () => import('../views/adminManager/page/page2.vue'),
                hidden: false
            },
            {
                path: 'list-people',
                component: () => import('../views/adminManager/page/page3.vue'),
                hidden: false
            },
            {
                path: 'list-assettype',
                component: () => import('../views/adminManager/page/page4.vue'),
                hidden: false
            },
            {
                path: 'list-brand',
                component: () => import('../views/adminManager/page/page5.vue'),
                hidden: false
            }, {
                path: 'list-getMethod',
                component: () => import('../views/adminManager/page/page6.vue'),
                hidden: false
            },
            {
                path: 'list-vendor',
                component: () => import('../views/adminManager/page/page7.vue'),
                hidden: false
            },
            {
                path: 'list-address',
                component: () => import('../views/adminManager/page/page8.vue'),
                hidden: false
            },
            {
                path: 'list-claims',
                component: () => import('../views/adminManager/page/page9.vue'),
                hidden: false
            },
            {
                path: 'list-repair',
                component: () => import('../views/adminManager/page/page10.vue'),
                hidden: false
            },
            {
                path: 'list-permission',
                component: () => import('../views/adminManager/page/page7.vue'),
                hidden: false
            },
            {
                path: 'list-export',
                component: () => import('../views/adminManager/page/page8.vue'),
                hidden: false
            }, {
                path: 'list-info',
                component: () => import('../views/adminManager/page/page11.vue'),
                hidden: false
            }
        ]
    }
]

export const router = VueRouter.createRouter({
    history: VueRouter.createWebHistory(),
    routes: RoutsList
    
});


export default router