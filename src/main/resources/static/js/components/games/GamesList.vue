<template>
  <v-container>
    <v-row>
      <v-col cols="12" sm="3" md="4" v-for="(game, index) in filteredGames" :key="index">
        <game-item :game="game"
                   :key="game.id"
                   :games="games"
                   :enter="enter"/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import GameItem from "components/games/GameItem.vue";
import {getIndex} from "util.js";

export default {
  props: ['games', 'filteredGames'],
  components: {
    GameItem
  },
  methods: {
    enter(game) {
      this.$resource('/game{/id}').update({id: game.id}, game).then(res => {
        res.json().then(data => {
          var index = getIndex(this.games, game.id);
          this.games.splice(index, 1, data);
        });
      });
    }
  },

}
</script>

<style>

</style>