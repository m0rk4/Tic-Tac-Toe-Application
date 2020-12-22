import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
import vuetify from 'plugins/vuetify' // path to vuetify export

Vue.use(VueResource)

new Vue({
    el: '#app',
    vuetify: vuetify,
    render: a => a(App)
})