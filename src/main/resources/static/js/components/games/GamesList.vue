<template>
  <v-container>
    <v-row>
      <v-col cols="12" sm="3" md="4" v-for="(game, index) in filteredSortedGames" :key="index">
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
                       />
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
                       :games="games"/>
          </v-card>
        </v-container>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import GameItem from "components/games/GameItem.vue";
import {mapGetters, mapState} from 'vuex';

export default {
  computed: {
    ...mapState(['games']),
    ...mapGetters(['filteredSortedGames'])
  },
  components: {
    GameItem
  }
}
</script>

<style>

</style>