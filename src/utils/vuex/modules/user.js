const state = () => ({
    username: '',
    token: '',
    image: '',
    path: ''
})

const getter = {
    getName: (state) => {
        return username = state.username
    },
    getToken: (state) => {
        return token = state.token
    },
    getPath: (state) => {
        return path = state.path
    },
    getImage: (state) => {
        return image = state.image
    }
}

const actions = {

}

const mutations = {
    setimage (state, value) {
        state.image = value
    },
    setpath (state, value) {
        state.path = value
    },
    settoken (state, value) {
        state.token = value
    },
    setname (state, value) {
        if (value != null) {
            state.username = JSON.parse(atob(value.split('.')[1])).userVo.name
        }
    }
}

export default {
    namespaced: true,
    state,
    getter,
    mutations,
    actions
}