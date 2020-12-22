<template>
  <v-app>
    <v-app-bar app>
      Mastering Spring 2
    </v-app-bar>
    <v-main>
      <v-container fluid>
        <v-row>
          <game-form :games="games" :filter-tags="filterTags"/>
        </v-row>
        <games-list :games="games" :filtered-games="filteredGames"/>
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
  data() {
    return {
      games: games,
      filteredGames: [],
      filterTags: [],
    }
  },
  created() {
    this.filteredGames = this.filterGames();
  },
  watch: {
    games() {
      this.filteredGames = this.filterGames();
    },
    filterTags() {
      this.filteredGames = this.filterGames();
    }
  },
  methods: {
    filterGames() {
      let res = [];
      this.games.forEach(g => {
        var toAdd = true;
        for (var i = 0; i < this.filterTags.length; i++) {
          var fName = this.filterTags[i];
          var containsCurrTag = false;
          g.tags.forEach(gt => {
            if (gt.name === fName) {
              containsCurrTag = true;
            }
          })
          if (!containsCurrTag) {
            toAdd = false;
            break;
          }
        }
        console.log();
        if (toAdd) res.push(g);
      })
      return res;
    }
  }
}
</script>

<style>

</style>