<template>
    <div>
        <div id="main" class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card" id="card1">
                        <h2 class="text-center">资产系统后台管理界面</h2>
                        <div class="card-body d-flex justify-content-center">
                            <form>
                                <div class="mb-3">
                                    <label for="exampleInputEmail1" class="form-label">用户名</label>
                                    <input type="text" class="form-control" required v-model="username"
                                        id="exampleInputEmail1" placeholder="请输入用户名">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputPassword1" class="form-label">密码</label>
                                    <input type="password" required v-model="password" class="form-control"
                                        id="exampleInputPassword1" placeholder="请输入密码">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputPassword2" class="form-label">确认密码</label>
                                    <input type="password" required v-model="confirmPassword" class="form-control"
                                        id="exampleInputPassword2" placeholder="请再次输入密码">
                                </div>
                                <button type="submit" id="reg" class="btn" @click="toRegister"
                                    style="background-color: dodgerblue; color: white;">注册</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, reactive } from 'vue';

export default {
    data() {
        return {
            username: '',
            password: '',
            confirmPassword: '',
            iphone: ''
        };
    },
    methods: {
        register() {
            if (this.password != this.confirmPassword) {
                return alert("密码与确认密码不一致！")
            }
            if (!this.password.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/)) {
                return alert('密码必须至少包含字母和数字，并且至少为 8 位！')
            }
            fetch('http://localhost:8080/api/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username: this.username, password: this.password })
            })
                .then(response => response.json())
                .then(data => {
                    alert('您已成功注册，请登录！')
                    this.$router.push('/login')
                })
                .catch(error => console.log(error))
        },
        toLogin() {
            this.$route.push('/login')
        }
    }
}
</script>

<style scoped></style>