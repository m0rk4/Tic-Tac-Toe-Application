<template>
  <v-container>
    <div v-if="!(this.lobby.isCreatorChoice ^ this.isAdmin)" :class="[`text-h3`]"
         class="d-flex justify-center ma-4">Make Your Choice! - {{ choice }}
    </div>
    <div v-else :class="[`text-h3`]"
         class="d-flex justify-center ma-4">
      Wait, Opponent is thinking...
    </div>
    <v-container class="board" fluid>
      <v-row no-gutters class="row d-flex justify-center" v-for="row in gameState" :key="row.id">
        <v-col
            v-for="tile in row.tiles" :key="tile.pos"
            @click="onCellClicked(row.id, tile.pos)"
            cols="3"
        >
          <v-card
              rounded
              raised
              class="tile py-md-16"
              outlined
              tile
          >
            <v-card-text align="center"><p class="res"> {{ tile.value }}</p></v-card-text>
          </v-card>
        </v-col>

      </v-row>
    </v-container>
    <dialog-form></dialog-form>
  </v-container>
</template>

<script>
import {mapMutations, mapGetters, mapState} from "vuex"
import {sendGame, sendAlert, disconnect, connect} from "util/ws.js";
import {checkWin} from "util/collections.js";
import DialogForm from "games/DialogForm.vue";

export default {
  components: {
    DialogForm
  },
  data() {
    return {
      currSession: frontData.currSession
    }
  },
  created() {
    // take game and it's states
    // then configure the board
    // set handler for click on a tile
    // update database each time person clicks on it
    // allow websocket to do it's work
    // be happy!
    var game = this.games.find(g => +g.id === +this.$route.params.id)
    if (this.currSession === game.creator || this.currSession === game.opponent) {
      this.getGameMutation(game);
      connect(game.id)
    } else {
      this.$router.push({path: '/'})
    }
  }
  ,
  computed: {
    ...
        mapState(['games', 'lobby']),
    ...
        mapGetters(['gameState', 'choice', 'isAdmin'])
  }
  ,
  methods: {
    ...
        mapMutations(['getGameMutation']),
    onCellClicked(rowId, pos) {
      if (this.lobby.isCreatorChoice ^ this.isAdmin) {
        return
      }
      var newGameState = [...this.gameState];
      if (newGameState.find(row => row.id === rowId)
          .tiles.find(tile => tile.pos === pos).value !== ' ') {
        return
      }
      newGameState.find(row => row.id === rowId)
          .tiles.find(tile => tile.pos === pos).value = this.choice
      var serializedNewGameState = " ";
      newGameState.forEach(row => {
        row.tiles.forEach(tl => {
          serializedNewGameState += tl.value;
        });
      });
      var winCheck = checkWin(serializedNewGameState)
      var gameToSave = JSON.parse(JSON.stringify(this.lobby))
      gameToSave.isCreatorChoice = !(this.lobby.isCreatorChoice)
      gameToSave.gameState = serializedNewGameState
      console.log(`What - ${winCheck}`)
      sendGame(gameToSave)
      if (winCheck !== 'n') {
        var text = this.isAdmin ? 'Winner: Creator with \'X\'' :
            'Winner: Guest with \'O\'';
        sendAlert({id: gameToSave.id, title: 'Notification!', text: text});
      } else if (serializedNewGameState.lastIndexOf(' ') === 0) {
        sendAlert({id: gameToSave.id, title: 'Surprise!', text: 'What a great game! No winner!'});
      }
    }
    ,
  }
}
</script>

<style>
.res {
  font-size: 45px;
}

.tile {
  cursor: pointer;
}

</style>