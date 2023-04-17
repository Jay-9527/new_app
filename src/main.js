import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'
import './scss/styles.scss'
import "@popperjs/core"
import "bootstrap"
import './scss/global.scss'
import store from './utils/vuex/store'
import 'xe-utils'
import VXETable from 'vxe-table'
import 'vxe-table/lib/style.css'

const app = createApp(App)

app.use(VXETable)
app.use(store)
app.use(router)
app.mount('#app')
