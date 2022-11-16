<template>
  <b-card id="map_menu" title="Card Title" class="mb-2">
    <b-card-text>
      <b-table :items="this.aptlist"></b-table>
    </b-card-text>

    <b-button href="#" variant="primary">Go somewhere</b-button>
  </b-card>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["aptlist"]),
  },
};
</script>

<style>
#map_menu {
  top: 10px;
  left: 10px;
  z-index: 1;
}
</style>
