import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.store({
    state: {
        games: games,
        tags: allTags;
    },
    mutations: {
        increment (state) {
            state.count++;
        }
    }
})