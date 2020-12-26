<template>
  <v-row justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        max-width="300"
    >
      <v-card>
        <v-card-title class="headline">
          {{ title }}
        </v-card-title>
        <v-card-text>{{ text }}</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="success"
              text
              rounded
              @click="dialogClicked"
          >
            OK WITH IT
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import {alertHandlers, disconnect} from "util/ws.js";
import {mapMutations} from 'vuex'

export default {
  data () {
    return {
      dialog: false,
      title: '',
      text: ''
    }
  },methods: {
    ...mapMutations(['removeGameMutation']),
    dialogClicked() {
      this.dialog = false;
      this.$router.push({ path: '/' })
    }
  },
  created() {
    alertHandlers(data => {
      this.title = data.title;
      this.text = data.text;
      this.dialog = true;
      this.removeGameMutation(data.id)
      disconnect()
    })
  }
}
</script>

<style scoped>

</style>