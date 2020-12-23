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
                v-model="this.tagList"
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

export default {
  props: ['game', 'games', 'enter'],
  computed: {
    tagList: function () {
      var tagList = [];
      this.game.tags.forEach(t => {
        tagList.push(t.name);
      });
      return tagList;
    },
  },
  methods: {
    enterGame() {
      this.game.playing = true;
      this.enter(this.game);
    }
  }
}
</script>

<style>
.tag-item span.v-chip {
  color: darkgreen;
}
</style>