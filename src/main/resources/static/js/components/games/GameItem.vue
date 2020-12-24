<template>
  <v-container >
    <v-card
        class="mx-auto"
        max-width="350"
        outlined
    >
      <v-list-item four-line>
        <v-list-item-content>
          <div class="overline mb-4">
            {{ game.creationDate }}
          </div>
          <v-list-item-title class="headline mb-1">
            {{ game.title }}
          </v-list-item-title>
          <v-list-item-subtitle v-if="!game.playing">Status: Waiting for opponent</v-list-item-subtitle>
          <v-list-item-subtitle v-else>Status: Playing</v-list-item-subtitle>

          <v-list-item-subtitle>
            <v-combobox
                class="tag-item"
                color="green"
                v-model="gameTags"
                chips
                multiple
                readonly
                item-color="green"
            ></v-combobox>
          </v-list-item-subtitle>
        </v-list-item-content>

      </v-list-item>

      <v-card-actions v-if="!game.playing">
        <v-btn @click="enterGame" color="success" outlined rounded text>
          Enter The Game
        </v-btn>
      </v-card-actions>
    </v-card>

  </v-container>
</template>

<script>
import {mapActions} from 'vuex'

export default {
  props: ['game'],
  data() {
    return {
      gameTags: []
    }
  },
  created() {
    this.game.tags.forEach(t => this.gameTags.push(t))
  },
  methods: {
    ...mapActions(['updateGameAction']),
    enterGame() {
      this.game.isPlaying = true;
      console.log(`Game ${this.game}`)
      this.updateGameAction(this.game);
    }
  }
}
</script>

<style>
.tag-item span.v-chip {
  color: darkgreen;
}
</style>