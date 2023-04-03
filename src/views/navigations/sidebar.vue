<template>
    <div class="navigation">
        <div class="list-group list-group-flush menu" role="tablist" id="list-tab">
            <router-link class="nav-item " to="/index/list-home" tager="span">DashBoard</router-link>
            <router-link class="nav-item " to="/index/list-worker" tager="span">部门管理</router-link>
            <router-link class="nav-item" to="/index/list-people" tager="span">人员管理</router-link>
            <router-link class="nav-item" to="/index/list-assettype" tager="span">资产类别</router-link>
            <router-link class="nav-item" to="/index/list-brand" tager="span">品牌</router-link>
            <router-link class="nav-item" to="/index/list-getMethod" tager="span">取得方式</router-link>
            <router-link class="nav-item" to="/index/list-vendor" tager="span">供应商管理</router-link>
            <router-link class="nav-item" to="/index/list-address" tager="span">存放地址</router-link>
            <router-link class="nav-item" to="/index/list-claims" tager="span">设备用途</router-link>
            <router-link class="nav-item" to="/index/list-repair" tager="span">报销管理</router-link>
            <router-link class="nav-item" to="/index/list-info" tager="span">个人信息</router-link>
            <!-- <router-link class="nav-item" to="/index/list-permission" tager="span">权限管理</router-link>
            <router-link class="nav-item" to="/index/list-export" tager="span">列表导出</router-link> -->
        </div>
    </div>
</template>

<style>
/* 侧栏样式 */
.item-1,
.menu {
    background-color: #304156;
    text-align: center;

}

.menu a {
    height: 50px;
    color: #efefefef;
    background-color: #304156;
    text-decoration: none;
    line-height: 50px;
}

.menu a:hover {
    /* background-color: #001523; */
    background-color: #202e3c;
    /* color: rgb(64, 158, 255); */
    color: #efefefef;
}

.menu a:active {
    background-color: #001523;
    color: rgb(64, 158, 255);
}
</style>