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
                                <img :src="image" @click="getCode" style="width: 130px; height: 39px;" data-base64=""
                                    alt="image">
                                <input type="text" id="imginput" style="width: 120px;" class="form-control"
                                    placeholder="输入验证码" v-model="codeImage" />
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
import { mapState, mapGetters, mapActions, mapMutations } from "vuex"
import request from "../utils/axios/request"
import qs from "qs"
// qs.parse({
//     username: this.$data.username,
//     password: this.$data.password,
//     imgData: this.$data.imgcode
// })
import { inject } from 'vue';

export default {
    data() {
        return {
            username: '',
            password: '',
            codeImage: ''
        }
    },
    computed: {
        ...mapState('user', ['image']),
        store() {
            var store = inject("store")
        }
    },
    created() {
        request.get('/captcha').then((response) => {
            // 初始化验证码
            this.initd(response.data.data.base64)
        })
    },
    methods: {
        ...mapMutations('user', ['setimage', 'setname', 'setpath', 'settoken']),
        initd(value) {
            this.setimage(value)
        },
        login() {
            request.post('api/login',
                qs.parse({
                    username: this.$data.username,
                    password: this.$data.password,
                    imgData: this.$data.codeImage
                })
                , {
                    headers: {
                        'Content-Type': ' application/x-www-form-urlencoded'
                    }
                }).then(resp => {
                    // 判断状态
                    console.log(resp)
                    if (resp.data.code === 200) {
                        this.setname(resp.data.token)
                        this.setpath(resp.data.data)
                        this.settoken(resp.data.token)

                        // 动态路由到用户菜单栏中。

                        this.$router.push({ name: 'Home' })
                    }

                }).catch(error => console.log(error))
        },
        getCode() {
            request.get('/captcha').then((response) => {
                this.setimage(response.data.data.base64)
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
    margin: 0 0 0 12px;
}
</style>