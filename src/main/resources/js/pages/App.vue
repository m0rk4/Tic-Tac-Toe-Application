<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title>Mastering Spring</v-toolbar-title>
    </v-app-bar>
    <v-main>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import {addHandler} from "util/ws.js";
import {mapMutations} from 'vuex'

export default {
  created() {
    addHandler(data => {
      this.getGameMutation(data)
    })
  },
  methods: mapMutations(['getGameMutation'])
}
</script>

<style>

</style>