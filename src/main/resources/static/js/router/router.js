import Vue from 'vue'
import VueRouter from 'vue-router'
import GamesList from "components/games/GamesList.vue"
import GameBoard from "pages/GameBoard.vue";

Vue.use(VueRouter)

const routes = [
    {path: '/', component: GamesList},
    {path: '/lobby/:id', component: GameBoard },
    {path: '*', component: GamesList}
]

export default new VueRouter({
    mode: 'history',
    routes
});