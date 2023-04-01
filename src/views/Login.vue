<template>
    <div id="main" class="container">
        <div class="row">
            <div class="col">
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
                            <div class="image">
                                <img :src="this.codeImage" @click="getCode" style="width: 130px; height: 39px;">
                                <input type="text" id="imginput" style="width: 120px;" class="form-control"
                                    placeholder="输入验证码" />
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
import { createDOMCompilerError } from '@vue/compiler-dom'
import { createApp } from 'vue'
import request from "../utils/request"
import qs from "qs"

export default {
    data() {
        return {
            username: '',
            password: '',
            codeImage: ''
        }
    },
    created() {
        var img = this.$data.codeImage;
        request.get('/captcha', { responseType: 'blob' }).then((response) => {
            console.log(response.data)
            this.codeImage = window.URL.createObjectURL(response.data)
            console.log(this.codeImage)
        })
    },
    methods: {
        login() {
            request.post('/login',
                qs.stringify({
                    name: this.$data.username,
                    password: this.$data.password
                }), {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(resp => {
                console.log(resp)
                if (resp.status == 200) {
                    this.$router.push({
                        path: '/index'
                    })
                }
            }).catch(error => console.log(error))
        },
        getCode() {
            request.get('/captcha', { responseType: 'blob' }).then((response) => {
                console.log(response.data)
                this.codeImage = window.URL.createObjectURL(response.data)
                console.log(this.codeImage)
            })
        },
        toRegister() {
            // 跳转到注册页面
            this.$router.push('/register')
        }
    }
}
</script>

<style scoped>
@media (min-width: 768px) {
    .container-fluid {
        width: 750px;
    }
}

@media (min-width: 992px) {
    .container-fluid {
        width: 970px;
    }
}

@media (min-width: 1200px) {
    .container-fluid {
        width: 100vw;
    }
}

#card1 {
    margin: 50% 0;
    border-radius: 3%;
    background-color: rgba(255, 255, 255, 0.8);
    box-shadow: none;
    border: none;
}

.btn:focus,
.btn:active {
    background-color: blue;
}

.btn {

    margin: 10px;
    width: 100px !important;
}

#reg {
    margin-left: 24%;
}

/* 验证码样式 */

.image {
    margin: 2px 0;
    display: flex;
}

#imginput {
    margin: 0 12px;
}
</style>