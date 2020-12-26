<template>
  <v-container>
    <v-card rounded outlined color="#f1f8e9">
      <v-form ref="form"
              v-model="valid"
              lazy-validation
              class="ma-4">
        <div
            :class="[`text-h2`]"
            class="mb-4"
        >
          Your Game
        </div>
        <v-text-field solo label="Game Title" placeholder="Type your title" :rules="nameRules" v-model="text"/>
        <v-combobox
            v-model="chosenTags"
            :items="optionsTags"
            :search-input.sync="search"
            chips
            deletable-chips
            hide-selected
            hint="Maximum of 5 tags"
            label="Add some tags"
            class="tag-input"
            multiple
            solo
            persistent-hint
            :rules="tagRules"
        >
          <template v-slot:no-data>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>
                  No results matching "<strong>{{ search }}</strong>". Press <kbd>enter</kbd> to create a new one
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </template>
        </v-combobox>
        <v-btn color="success" @click="addGame" rounded>Create game</v-btn>
      </v-form>
    </v-card>
  </v-container>
</template>

<script>
import {mapActions, mapMutations, mapGetters} from 'vuex'

export default {
  data() {
    return {
      text: '',
      chosenTags: [],
      search: null,
      valid: true,
      nameRules: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 25) || 'Name must be less than 25 characters',
      ],
      tagRules: [
        t => t.length > 0 || 'Choose at least one tag'
      ]
    }
  },
  computed: mapGetters(['optionsTags']),
  watch: {
    tags(val) {
      if (val.length > 5) {
        this.$nextTick(() => this.chosenTags.pop())
      }
    },
  },
  methods: {
    ...mapMutations(['updateOptionsMutation']),
    ...mapActions(['addGameAction']),
    addGame() {
      if (!this.$refs.form.validate()) return
      this.updateOptionsMutation(this.chosenTags);
      var game = {
        title: this.text,
        tags: this.chosenTags.map(s => {return {name: s}}),
        isPlaying: false,
        isCreatorChoice: true,
        gameState: '          '
      };
      this.addGameAction(game)
      this.text = ''
      this.chosenTags = []
      this.$refs.form.reset()
    },
  }
}
</script>

<style>
.tag-input span.v-chip {
  color: darkgreen;
}
</style>