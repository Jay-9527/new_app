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
                                <img :src="code" @click="getVerifyCode" style="width: 100px; height: 39px;" data-base64=""
                                    alt="image">
                                <input type="text" id="imginput" style="width: 100px;" class="form-control"
                                    placeholder="输入验证码" v-model="codeimage" />
                            </div>
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                                <label class="form-check-label" for="exampleCheck1">记住密码</label>
                            </div>
                            <button type="submit" class="btn" @click.prevent="sendData"
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

<script setup>
import { onBeforeMount } from 'vue';
import request from "../utils/axios/request"
import { useStore } from 'vuex';
import qs from "qs"
import { useRouter } from 'vue-router';

var username = ''
var password = ''
var codeimage = ''

const sotre = useStore()
const router = useRouter()
var code = sotre.state.user.image

// async () => {
// const result = await request.get('/captcha').then((resp) => {
//     return resp.data.data.base64
// })
// sotre.commit('user/setimage', result)
// }

onBeforeMount(async () => {
    const result = await request.get('/captcha').then((resp) => {
        return resp.data.data.base64
    })
    sotre.commit('user/setimage', result)
});

const getVerifyCode = () => {
    const result = request.get('/captcha').then((resp) => {
        return resp.data.data.base64
    })
    sotre.commit('user/setimage', result)
}

const sendData = () => {
    request.post('api/login',
        qs.parse({
            username: username,
            password: password,
            codeimage: codeimage
        }),
        {
            headers: {
                "Content-Type": 'application/x-www-form-urlencoded'
            }
        }
    ).then(resp => {
        console.log(resp)
        if (resp.data.code === 200) {
            sotre.commit("user/setname", resp.data.token)
            sotre.commit("user/setpath", resp.data.data)
            sotre.commit("user/settoken", resp.data.token)
        }
    }).catch(error => console.log(error))
}

const toRegister = () => {
    router.push('/register')
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
    margin: 0 0 0 2px;
}
</style>