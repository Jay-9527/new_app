<template>
    <div class="IndexPage row">
        <!-- left 区域 -->
        <div class="col-1 item item-1">
            <sidebar />
        </div>

        <!-- right 区域 -->
        <div class="col-11 item">
            <!-- header 区域 -->
            <header class="header">
                <!-- 存放navgeations -->
                <nav class="navbar bg-body-tertiary">

                    <!-- 导航栏 -->
                    <div class="container-fluid row ">
                        <div class="col-1 item" id="logo">
                            <span>
                                <a class="navbar-brand" href="/index">
                                    <img src="https://img.icons8.com/cotton/24/null/money--v2.png"
                                        class="d-inline-block align-text-top" alt="Logo" />
                                    资产系统
                                </a>
                            </span>
                        </div>
                        <div class="col-3 item">
                            <form class="d-flex align-items-center" role="search">
                                <input class="form-control" id="Navin" type="search" placeholder="Search"
                                    aria-label="Search">
                                <button class="btn btn-outline-success" type="submit">Search</button>
                            </form>
                        </div>
                        <div class="col-1 item" id="userImage">
                            <div class="set">
                                <img src="../image/user.png" alt="userIMage">
                            </div>
                        </div>
                    </div>

                </nav>

            </header>

            <!-- content区域 -->
            <div>
                <div class="content" id="nav-tabContent">
                    <RouterView />
                </div>
            </div>

            <!-- footer区域 -->
            <div class="footer">

            </div>
        </div>
    </div>
</template>

<script >
import sidebar from './navigations/sidebar.vue';
import SuccessMessage from './modal/SuccessMessage.vue';
import { ElNotification } from 'element-plus';
import store from '../utils/vuex/store';

export default {
    name: 'App5Index',
    components: { sidebar, SuccessMessage, ElNotification },

    data() {
        return {
            name: "ssss",
            message: null
        };
    },
    // 初始化
    created() {

    },

    mounted() {

    },

    methods: {

    },
};
</script>

<style scoped>
/* 子组件的背景颜色 */
.IndexPage {
    height: 100vh;
    padding: 0;
    background-color: white;
    box-sizing: border-box;
}

/* 首页左右区域样式 */
.item {
    padding: 0;
}

/* logo */
#logo {
    margin-left: 20px;
}

/* 导航栏的样式 */
.header {
    /* background-color: beige; */
    box-shadow: 0 1px 4px rgba(0, 21, 41, .08);
}

/* 输入容器高度 */
#Navin {
    height: 40px;
}

/* 用户头像的样式 */
#userImage img {
    width: 40px;
    height: 40px;
    object-fit: contain;
    border-radius: 50%;
}

/* content样式 */
.content {
    max-width: 100%;
    max-height: 100vh;
}
</style>