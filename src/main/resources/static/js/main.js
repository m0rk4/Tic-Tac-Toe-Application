import Vue from 'vue'
import 'api/resource'
import router from 'router/router.js'
import App from 'pages/App.vue'
import store from "store/store"
import vuetify from 'plugins/vuetify'


new Vue({
    el: '#app', vuetify, store, router,
    render: a => a(App)
})
