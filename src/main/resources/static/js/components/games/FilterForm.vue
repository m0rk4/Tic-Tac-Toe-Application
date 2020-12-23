<template>
  <v-container>
    <v-card rounded outlined color="#f1f8e9">
      <v-form class="ma-4" >
        <div class="mb-4"
             :class="[`text-h1`]"
        >
          Filter Existing Games
        </div>
        <v-combobox
            v-model="tags"
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
                  No results matching "<strong>{{ search }}</strong>". Press <kbd>enter</kbd> to create a new one
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
import {extractNames} from "util.js";

export default {
  props: ['filterTags'],
  data() {
    return {
      tags: [],
      options: extractNames(allTags),
      search: null,
    }
  },
  methods: {
    onTagsChanged(tags) {
      if (this.filterTags.length < tags.length) {
        this.filterTags.push(tags[tags.length - 1]);
      } else {
        for (var i = 0; i < this.filterTags.length; i++) {
          if (!tags.includes(this.filterTags[i])) {
            this.filterTags.splice(i, 1);
            break;
          }
        }
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