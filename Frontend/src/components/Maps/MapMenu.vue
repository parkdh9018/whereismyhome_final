<template>
  <b-card id="map_menu" class="mb-2">
    <b-card-text class="position-relative">
      <i class="ni ni-bold-left mr-2" @click="closeEvent"></i>
      {{address}}
    </b-card-text>
    <b-card-text>
      <b-tabs content-class="mt-3" fill>
        <b-tab title="관심지역" active><p>관심지역</p></b-tab>
        <b-tab title="주소로 찾기">
          <MapMenuSeletArea/>
        </b-tab>
      </b-tabs>
    </b-card-text>
  </b-card>
</template>

<script>
import { mapGetters } from "vuex";
import MapMenuSeletArea from "@/components/Maps/MapMenuSelectArea"

export default {
  components: {
    MapMenuSeletArea
  },
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
  top: 0px;
  left: 0px;
  z-index: 1;
  width: 350px;
  height: 100%;
}
</style>
