<template>
  <v-container>
    <v-card rounded outlined color="#f1f8e9">
      <v-form class="ma-4">
        <div class="mb-4" :class="[`text-h1`]">
          Filter Existing Games
        </div>
        <v-combobox
            v-model="selectedTags"
            :items="options"
            :search-input.sync="search"
            hide-selected
            class="tag-filter"
            label="Filter by tags"
            multiple
            deletable-chips
            persistent-hint
            solo
            chips
            @input="onTagsChanged"
        >
          <template v-slot:no-data>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>
                  No results matching "<strong>{{ search }}</strong>". Press <kbd>Enter</kbd> to create a new one
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </template>
        </v-combobox>
      </v-form>
    </v-card>
  </v-container>
</template>

<script>
import {mapState, mapMutations} from 'vuex'

export default {
  data() {
    return {
      selectedTags: [],
      search: null,
    }
  },
  computed: mapState(['filterTags', 'options']),
  methods: {
    ...mapMutations(['addFilterTagsMutation', 'removeFilterTagsMutation', 'addGameMutation']),
    onTagsChanged(updatedTags) {
      if (this.filterTags.length < updatedTags.length) {
        this.addFilterTagsMutation(updatedTags[updatedTags.length - 1])
      } else {
        this.removeFilterTagsMutation(updatedTags)
      }
    }
  }
}
</script>

<style>
.tag-filter span.v-chip {
  color: darkgreen;
}
</style>