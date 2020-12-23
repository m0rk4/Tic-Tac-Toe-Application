<template>
  <v-container>
    <v-row>
      <v-col cols="12" sm="3" md="4" v-for="(game, index) in filteredGames" :key="index">
        <v-container>
          <v-card
              v-if="!game.playing"
              class="mx-auto"
              max-width="350"
              outlined
              color="#66BB6A"
          >
            <game-item :game="game"
                       :key="game.id"
                       :games="games"
                       :enter="enter"/>
          </v-card>
          <v-card
              v-else
              class="mx-auto"
              max-width="350"
              outlined
              color="#FFEBEE"
          >
            <game-item :game="game"
                       :key="game.id"
                       :games="games"
                       :enter="enter"/>
          </v-card>
        </v-container>
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