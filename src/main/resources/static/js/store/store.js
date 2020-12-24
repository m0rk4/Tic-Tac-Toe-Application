import Vue from 'vue';
import Vuex from 'vuex';
import gameApi from 'api/gameApi.js'
import {getFilteredGames} from 'util/collections'
import {getNewTags} from 'util/collections'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        games: frontData.games,
        options: frontData.tags,
        filteredGames: frontData.games,
        filterTags: [],
    },
    getters: {
        filteredSortedGames: state => state.filteredGames.sort((a, b) => -(a.id - b.id))
    },
    mutations: {
        updateOptionsMutation(state, chosenTags) {
            var newTags = getNewTags(state.options, chosenTags);
            state.options = [
                ...state.options,
                ...newTags
            ]
        },
        addFilterTagsMutation(state, newTag) {
            state.filterTags = [
                ...state.filterTags,
                newTag
            ]
            state.filteredGames = [...getFilteredGames(state.games, state.filterTags)]
        },
        removeFilterTagsMutation(state, updatedTags) {
            for (var i = 0; i < state.filterTags.length; i++) {
                if (!updatedTags.includes(state.filterTags[i])) {
                    state.filterTags = [
                        ...state.filterTags.splice(0, i),
                        ...state.filterTags.splice(i + 1)
                    ]
                    break;
                }
            }
            state.filteredGames = [...getFilteredGames(state.games, state.filterTags)]
        },
        addGameMutation(state, game) {
            state.games = [
                ...state.games,
                game
            ]
            state.filteredGames = [...getFilteredGames(state.games, state.filterTags)]
        },
        updateGameMutation(state, game) {
            const updateIndex = state.games.findIndex(g => g.id === game.id)
            state.games.forEach(g => console.log(g));
            console.log(game)
            state.games = [
                ...state.games.splice(0, updateIndex),
                game,
                ...state.games.splice(updateIndex + 1)
            ]
            state.games.forEach(g => console.log(g));
        },
    },
    actions: {
        addGameAction({commit}, game) {
            gameApi.add(game).then(result => {
                result.json().then(data => {
                    commit('addGameMutation', data)
                })
            })
        },
        updateGameAction({commit}, game) {
            gameApi.update(game).then(result => {
               result.json().then(data => {
                   console.log(data)
                   commit('updateGameMutation', data)
               })
            })
        }
    }
})