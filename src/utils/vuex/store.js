import { createStore } from 'vuex'
import user from './modules/user'
import settings from './modules/settings'
import createPersistedState from 'vuex-persistedstate'

export default createStore({
    plugins: [createPersistedState({
        key: 'saveinfo',
        paths: ['user']
    })],
    modules: {
        user,
        settings
    }
})