<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title>Mastering Spring</v-toolbar-title>
    </v-app-bar>
    <v-main>
      <v-container fluid>
        <v-row>
          <game-form/>
        </v-row>
        <v-divider></v-divider>
        <v-container>
          <games-list/>
        </v-container>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import GamesList from "components/games/GamesList.vue";
import GameForm from "components/games/GameForm.vue";

export default {
  components: {
    GamesList,
    GameForm
  },
}
</script>

<style>

</style>