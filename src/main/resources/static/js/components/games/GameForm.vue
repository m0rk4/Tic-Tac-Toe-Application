<template>
  <v-container>
    <v-row>
      <v-col>
        <filter-form></filter-form>
      </v-col>
      <v-col>
        <add-form></add-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import AddForm from "components/games/AddForm.vue"
import FilterForm from "components/games/FilterForm.vue";

export default {
  components: {AddForm, FilterForm},
}
</script>

<style>

</style>