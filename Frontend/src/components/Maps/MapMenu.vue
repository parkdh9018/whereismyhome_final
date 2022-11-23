<template>
  <b-card id="map_menu">
    <b-card-text class="position-relative">
      <b-form @submit.prevent="searchKeyword">
        <b-input-group>
          <i class="ni ni-bold-left mr-2 mt-3" @click="closeEvent"></i>
          <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-search"></i></span>
          </div>

          <b-form-input
            :placeholder="address"
            type="text"
            v-model="keyword"
          ></b-form-input>

          <b-input-group-append v-if="serchResult">
            <span @click="searchResultClose" class="input-group-text"
              ><i class="ni ni-fat-remove"></i
            ></span>
          </b-input-group-append>
        </b-input-group>
      </b-form>
    </b-card-text>
    <b-card-text>
      <b-tabs v-if="!serchResult" content-class="mt-3" fill>
        <b-tab title="관심지역" active>
          <p>관심지역</p>
        </b-tab>
        <b-tab title="주소로 찾기">
          <MapMenuSeletArea />
        </b-tab>
      </b-tabs>
      <MapMenuPlaceList v-else />
    </b-card-text>
  </b-card>
</template>

<script>
import MapMenuSeletArea from "@/components/Maps/MapMenuSelectArea";
import MapMenuPlaceList from "./MapMenuPlaceList.vue";
import { mapGetters } from "vuex";

export default {
  components: {
    MapMenuSeletArea,
    MapMenuPlaceList,
  },
  data() {
    return {
      keyword: "",
      serchResult: false,
    };
  },
  props: {
    address: String,
  },
  computed: {
    ...mapGetters("map", ["center"]),
  },
  methods: {
    closeEvent() {
      this.$emit("closeEvent");
    },
    searchKeyword() {
      this.$store.dispatch("map/getStructByKeyword", this.keyword);
      this.serchResult = true;
    },
    searchResultClose() {
      this.serchResult = false;
    },
  },
};
</script>

<style scoped></style>
