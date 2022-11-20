<template>
  <b-card id="map_menu" class="mb-2">
    <b-card-text class="position-relative">
      <b-form @submit.prevent="searchKeyword">
        <b-input-group>
          <i class="ni ni-bold-left mr-2 mt-3" @click="closeEvent"></i>         
          <div class="input-group-prepend">
            <span class="input-group-text"><i class="fas fa-search"></i></span>
          </div>

          <b-form-input :placeholder="address" type="text" v-model="keyword"></b-form-input>

          <b-input-group-append v-if="serchResult">
            <span @click="searchResultClose" class="input-group-text"><i class="ni ni-fat-remove"></i></span>
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
      <b-container v-else>
        <b-row>
          <b-col class="border rounded text-center"  cols="12" v-for="apt in searchAptList" :key="apt.id">{{apt.place_name}}</b-col>
          <b-col v-if="searchAptList.length == 0">
            정보가 없습니다.
          </b-col>
        </b-row>
      </b-container>
    </b-card-text>
  </b-card>
</template>

<script>
import MapMenuSeletArea from "@/components/Maps/MapMenuSelectArea";
import { keywordSearch } from "@/api/areaApi";

export default {
  components: {
  MapMenuSeletArea,
},
  data() {
    return {
      keyword : "",
      searchAptList: [],
      serchResult : false,
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
        this.searchAptList = data;
        console.log(this.searchAptList)
        this.serchResult = true;
      });
    },
    searchResultClose() {
      this.serchResult = false;
    }
  },
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
