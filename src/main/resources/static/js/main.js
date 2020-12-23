import Vue from 'vue'
import 'api/resource'
import App from 'pages/App.vue'
import store from "store/store"
import vuetify from 'plugins/vuetify'

new Vue({
    el: '#app', vuetify, store,
    render: a => a(App)
})
