import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'
import './scss/styles.scss'
import "@popperjs/core"
import "bootstrap"
import './scss/global.scss'

const app = createApp(App)

app.use(router)
app.mount('#app')
