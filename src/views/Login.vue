<template>
    <div id="main" class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="card" id="card1">
                    <h2 class="text-center">资产系统后台管理界面</h2>
                    <div class="card-body d-flex justify-content-center">
                        <form>
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Name</label>
                                <input type="text" class="form-control" required v-model="username" id="exampleInputEmail1"
                                    placeholder="请输入用户名">
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputPassword1" class="form-label">Password</label>
                                <input type="password" required v-model="password" class="form-control"
                                    id="exampleInputPassword1" placeholder="请输入密码">
                            </div>
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                                <label class="form-check-label" for="exampleCheck1">记住密码</label>
                            </div>
                            <button type="submit" class="btn" @click.prevent="login"
                                style="background-color: dodgerblue; color: white;">登录</button>
                            <button type="submit" class="btn" @click="toRegister"
                                style="background-color: dodgerblue; color: white;">注册</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            username: '',
            password: ''
        }
    },
    methods: {
        login() {
            // 发送 POST 请求
            fetch('https://localhost:8080/api/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username: this.username, password: this.password })
            })
                .then(response => response.json())
                .then(data => {
                    // 将 Token 存储在本地存储器中
                    localStorage.setItem('token', data.token)
                    // 跳转到个人资料页面
                    this.$router.push('/index')
                })
                .catch(error => console.error(error))
        },
        toRegister() {
            // 跳转到注册页面
            this.$router.push('/register')
        }
    }
}
</script>

<style scoped></style>