<template>
  <v-container>
    <v-row>
      <game-form/>
    </v-row>
    <v-divider></v-divider>
    <v-container>
      <v-row>
        <v-col cols="12" sm="5" md="4" v-for="game in filteredSortedGames" :key="game.id">
          <v-card
                v-if="!game.isPlaying"
                class="mx-auto"
                max-width="350"
                outlined
                color="#66BB6A"
            >
              <game-item :game="game"
                         :key="game.id"
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
                         :key="game.id"/>
            </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
import GameItem from "components/games/GameItem.vue";
import GameForm from "components/games/GameForm.vue";
import {mapGetters} from 'vuex';

export default {
  computed: {
    ...mapGetters(['filteredSortedGames']),
  },
  components: {
    GameItem,
    GameForm
  }
}
</script>

<style>

</style>