<template>
  <b-card id="map_menu" class="mb-2">
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
      <MapMenuPlaceList v-else :place-list="searchAptList" />
    </b-card-text>
  </b-card>
</template>

<script>
import MapMenuSeletArea from "@/components/Maps/MapMenuSelectArea";
import MapMenuPlaceList from "./MapMenuPlaceList.vue";
import { keywordSearch } from "@/api/areaApi";

export default {
  components: {
    MapMenuSeletArea,
    MapMenuPlaceList,
  },
  data() {
    return {
      keyword: "",
      searchAptList: [],
      serchResult: false,
    };
  },
  props: {
    address: String,
  },
  methods: {
    closeEvent() {
      this.$emit("closeEvent");
    },
    searchKeyword() {
      keywordSearch(this.keyword).then(([data, pagenation]) => {
        console.log(this.searchAptList);
        this.searchAptList = data.map((place) => {
          return {
            id: place.id,
            name: place.place_name,
            address: place.address_name,
            category: place.category_group_name,
            lng: place.x,
            lat: place.y,
            place_url: place.place_url,
          };
        });
        this.serchResult = true;
      });
    },
    searchResultClose() {
      this.serchResult = false;
    },
  },
};
</script>

<style scoped>
#map_menu {
  width: 350px;
  height: 100%;
}
</style>
