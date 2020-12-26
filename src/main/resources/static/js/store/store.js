import Vue from 'vue';
import Vuex from 'vuex';
import gameApi from 'api/gameApi.js'
import {getFilteredGames} from 'util/collections'
import {getNewTags} from 'util/collections'
import router from "router/router.js";
import {connect} from 'util/ws.js'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        games: frontData.games,
        options: frontData.tags,
        filteredGames: frontData.games,
        filterTags: [],
        lobby: {},
        router: router
    },
    getters: {
        filteredSortedGames: state => state.filteredGames.sort((a, b) => -(a.id - b.id)),
        optionsTags: state => state.options.map(o => o.name),
        gameState: state => {
            var strState;
            if (state.lobby.gameState) {
                console.log('hit the bit')
                strState = state.lobby.gameState;
            } else {
                strState = '          '
            }
            console.log(strState)
            var res = []
            res.push({
                id: '1row', tiles: [
                    {value: strState[1], pos: '0,0'},
                    {value: strState[2], pos: '0,1'},
                    {value: strState[3], pos: '0,2'}
                ]
            })
            res.push({
                id: '2row', tiles: [
                    {value: strState[4], pos: '1,0'},
                    {value: strState[5], pos: '1,1'},
                    {value: strState[6], pos: '1,2'}
                ]
            })
            res.push({
                id: '3row', tiles: [
                    {value: strState[7], pos: '2,0'},
                    {value: strState[8], pos: '2,1'},
                    {value: strState[9], pos: '2,2'}
                ]
            })
            console.log(res)
            return res
        },
        choice: state => state.lobby ? state.lobby.creator === frontData.currSession ? 'x' : 'o' : ' ',
        isAdmin: state => state.lobby ? state.lobby.creator === frontData.currSession : false
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
                if (!updatedTags.includes(state.filterTags[i].name)) {
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
            state.router.push({path: `/lobby/${game.id}`});
        },
        updateGameMutation(state, game) {
            const updateIndex = state.games.findIndex(g => g.id === game.id)
            state.games = [
                ...state.games.splice(0, updateIndex),
                game,
                ...state.games.splice(updateIndex + 1)
            ]
            state.filteredGames = [...getFilteredGames(state.games, state.filterTags)]
            state.router.push({path: `/lobby/${game.id}`});
        },
        getGameMutation(state, game) {
            state.lobby = game;
        },
        removeGameMutation(state, id) {
            const delIndex = state.games.findIndex(g => g.id === id)
            state.games = [
                ...state.games.splice(0, delIndex),
                ...state.games.splice(delIndex + 1)
            ]
            state.filteredGames = [...getFilteredGames(state.games, state.filterTags)]
        }
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
                    commit('updateGameMutation', data)
                })
            })
        },
        getGameAction({commit, state}, id) {
            gameApi.get(id).then(result => {
                console.log(result)
                result.json().then(data => {
                    commit('getGameMutation', data)
                })
            })
        }
    }
})