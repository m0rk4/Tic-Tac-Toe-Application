import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
import Lobby from 'pages/Lobby.vue'
import store from "store/store";
import vuetify from 'plugins/vuetify'
import 'vuetify/dist/vuetify.min.css'

Vue.use(VueResource)

new Vue({
    el: '#app', vuetify,
    render: a => a(App)
});

new Vue({
   el: '#game', vuetify, store,
   render: a => a(Lobby)
});