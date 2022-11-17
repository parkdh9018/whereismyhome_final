<template>
  <b-card id="map_menu" class="mb-2">
    <b-card-text class="position-relative">
      <i class="ni ni-bold-right" @click="closeEvent"></i>
      {{address}}
    </b-card-text>
    <b-card-text>
      <b-tabs content-class="mt-3" fill>
        <b-tab title="관심지역" active><p>관심지역</p></b-tab>
        <b-tab title="지역정보"><p>지역</p></b-tab>
      </b-tabs>
    </b-card-text>
  </b-card>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  data() {
    return {};
  },
  props : {
    address : String
  },
  computed: {
    ...mapGetters(["aptlist"]),
  },
  methods: {
    closeEvent() {
      this.$emit('closeEvent');
    }
  }
};
</script>

<style scoped>
#map_menu {
  position: absolute;
  top: 10px;
  left: 10px;
  z-index: 1;
  width: 340px;
  height: 80%;
}
</style>
