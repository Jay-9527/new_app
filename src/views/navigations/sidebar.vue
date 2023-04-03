<template>
    <div class="navigation">
        <div class="list-group list-group-flush menu" role="tablist" id="list-tab">
            <!-- <a href="index/list-home" class="list-group-item list-group-item-action" id="list-home-list" data-toggle="list"
                role="tab" aria-controls="home">dashboard</a> -->

            <router-link class="nav-item" to="/index/list-home" tager="span">DashBoard</router-link>
            <router-link class="nav-item" to="/index/list-people" tager="span">人员管理</router-link>
            <router-link class="nav-item" to="/index/list-asset" tager="span">资产管理</router-link>
            <router-link class="nav-item" to="/index/list-vendor" tager="span">厂商管理</router-link>
            <router-link class="nav-item" to="/index/list-claims" tager="span">报销管理</router-link>
            <router-link class="nav-item" to="/index/list-repair" tager="span">维修管理</router-link>
            <router-link class="nav-item" to="/index/list-permission" tager="span">权限管理</router-link>
            <router-link class="nav-item" to="/index/list-export" tager="span">列表导出</router-link>

            <!-- <a href="list-people" class="list-group-item list-group-item-action" id="list-people-list" data-toggle="list"
                role="tab" aria-controls="people">人员管理</a>

            <a href="list-asset" class="list-group-item list-group-item-action" id="list-asset-list" data-toggle="list"
                role="tab" aria-controls="asset">资产管理</a>

            <a href="list-vendor" class="list-group-item list-group-item-action" id="list-vendor-list" data-toggle="list"
                role="tab" aria-controls="vendor">厂商管理</a>

            <a href="list-claims" class="list-group-item list-group-item-action" id="list-claims-list" data-toggle="list"
                role="tab" aria-controls="claims">报销管理</a>

            <a href="list-repair" class="list-group-item list-group-item-action" id="list-repair-list" data-toggle="list"
                role="tab" aria-controls="repair">维修管理</a>

            <a href="list-permission" class="list-group-item list-group-item-action" id="list-permission-list"
                data-toggle="list" role="tab" aria-controls="permission">权限管理</a>

            <a href="list-export" class="list-group-item list-group-item-action" id="list-export-list" data-toggle="list"
                role="tab" aria-controls="export">列表导出</a> -->
        </div>
    </div>
</template>

<style>
/* 侧栏样式 */
.item-1,
.menu {
    background-color: #23a6d5;
    text-align: center;

}

.menu a {
    color: #fff;
    background-color: #23a6d5;
    text-decoration: none;
}
</style>