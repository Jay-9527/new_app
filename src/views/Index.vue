<template>
    <div class="IndexPage row">
        <!-- left 区域 -->
        <div class="col-1 item item-1">
            <div class="list-group list-group-flush menu" role="tablist" id="list-tab">
                <a href="list-home" class="list-group-item list-group-item-action" id="list-home-list" data-toggle="list"
                    role="tab" aria-controls="home">dashboard</a>

                <a href="list-people" class="list-group-item list-group-item-action" id="list-people-list"
                    data-toggle="list" role="tab" aria-controls="people">人员管理</a>

                <a href="list-asset" class="list-group-item list-group-item-action" id="list-asset-list" data-toggle="list"
                    role="tab" aria-controls="asset">资产管理</a>

                <a href="list-vendor" class="list-group-item list-group-item-action" id="list-vendor-list"
                    data-toggle="list" role="tab" aria-controls="vendor">厂商管理</a>

                <a href="list-claims" class="list-group-item list-group-item-action" id="list-claims-list"
                    data-toggle="list" role="tab" aria-controls="claims">报销管理</a>

                <a href="list-repair" class="list-group-item list-group-item-action" id="list-repair-list"
                    data-toggle="list" role="tab" aria-controls="repair">维修管理</a>

                <a href="list-permission" class="list-group-item list-group-item-action" id="list-permission-list"
                    data-toggle="list" role="tab" aria-controls="permission">权限管理</a>

                <a href="list-export" class="list-group-item list-group-item-action" id="list-export-list"
                    data-toggle="list" role="tab" aria-controls="export">列表导出</a>
            </div>
        </div>

        <!-- right 区域 -->
        <div class="col-11 item">
            <!-- header 区域 -->
            <header class="header">
                <!-- 存放navgeations -->
                <nav class="navbar bg-body-tertiary">

                    <!-- 导航栏 -->
                    <div class="container-fluid">
                        <a class="navbar-brand">
                            <img src="https://img.icons8.com/cotton/24/null/money--v2.png"
                                class="d-inline-block align-text-top" alt="Logo" />
                            Bootstrap
                        </a>
                        <form class="d-flex align-items-center" role="search">
                            <input class="form-control" id="Navin" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>

                    </div>

                </nav>

            </header>

            <!-- content区域 -->
            <div>
                <div class="tab-content" id="nav-tabContent">
                    <RouterView />
                </div>
            </div>

            <!-- footer区域 -->
        </div>
    </div>
</template>

<script >
export default {
    name: 'App5Index',

    data() {
        return {
            name: "ssss"
        };
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
    background-color: aqua;
    box-sizing: border-box;
}

/* 导航栏的样式 */
.header {
    background-color: beige;
}

/* 侧栏样式 */
.item-1,
.menu {
    background-color: #23a6d5;
    text-align: center;
}

.item {
    padding: 0;
}



#Navin {
    height: 40px;
}
</style>