<template>
  <v-container>
    <v-row>
      <v-col>
        <filter-form :filter-tags="filterTags" ></filter-form>
      </v-col>
      <v-col>
        <add-form :games="games"></add-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import AddForm from "components/games/AddForm.vue"
import FilterForm from "components/games/FilterForm.vue";

export default {
  props: ['games', 'filterTags'],
  components: {AddForm, FilterForm},
}
</script>

<style>

</style>