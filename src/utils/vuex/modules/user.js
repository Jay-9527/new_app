export default {
    store: {
        username: '',
        token: '',
        image: '',
        path: ''
    },
    mutations: {

    },
    actions: {

    },
    getName: {
        get(state) {
            return username = store.username
        }
    },
    getToken: {
        get(state) {
            return token = store.token
        }
    },
    getPath: {
        get(state) {
            return path = store.path
        }

    },
    getImage: {
        get(state) {
            return image = store.image
        }
    }
}