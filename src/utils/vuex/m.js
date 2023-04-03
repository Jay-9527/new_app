import { createStore } from "vuex";

// 实例化一个store对象

const store = createStore({
    state() {
        return {
            count: 0
        }
    },
    mutations: {
        increment(state) {
            state.count++
        }
    }
})


export default store