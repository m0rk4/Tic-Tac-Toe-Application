import Vue from 'vue'

const games = Vue.resource('/game{/id}')

export default {
    add: game => games.save({}, game),
    update: game => games.update({id: game.id}, game),
    remove: id => games.remove({id: id})
}