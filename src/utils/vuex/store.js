import { createStore } from 'vuex';

// 实例化一个store对象

const store = createStore({
    state() {
        return {
            isLogin: false,
            successMessage: '',
            showLoginSuccess: false
        }
    },
    mutations: {
        login(state) {
            state.isLogin = true;
        },
        logout(state) {
            state.isLogin = false;
        },
        setSuccessMessage(state, message) {
            state.successMessage = message;
        },
        setShowLoginSuccess(state, value) { // 新增 mutation
            state.showLoginSuccess = value;
        }
    },
    actions: {

    }
})

// 将vuex实例出来的store抛出去
export default store