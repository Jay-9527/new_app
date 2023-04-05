<script>
export default {
    props: {
        message: String
    }
}
</script>
<template>
    <!-- 首页展示 -->
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="boox">
                    欢迎{{ message }}登录系统
                </div>
            </div>
        </div>
    </div>
</template>
<style>
.boox{
    margin: 0;
    padding: 0;
    max-width: 400px;
    max-height: 600px;
    background-color: brown;
}
</style>