<template>
  <b-container class="overflow-auto text-left">
    <b-row>
      <b-col
        cols="12"
        @click="placeClickEvent(place.address, place.house_type, place.code)"
        v-for="(place, index) in favoriteList"
        :key="index"
      >
        <stats-card
          :title="place.house_type"
          :sub-title="place.name"
          :type="iconColor(place.house_type)"
          icon="ni ni-building"
          class="mb-1 bg-gradient"
        >
          <template slot="footer">
            <span class="text-success mr-2">{{ place.address }}</span>
          </template>
        </stats-card>
      </b-col>
      <b-col v-if="favoriteList.length == 0"> 정보가 없습니다. </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapMutations, mapGetters } from "vuex";
import { searchPosition } from "@/api/areaApi";

export default {
  computed: {
    ...mapGetters("member", ["favoriteList"]),
  },
  methods: {
    ...mapMutations("map", [
      "levelMove",
      "setCenter",
      "setDetailToggle",
      "setStructDetailPos",
    ]),
    placeClickEvent(address, category, code) {
      searchPosition(address).then((pos) => {
        this.setCenter(pos);
        this.levelMove(2);
        if (["아파트", "오피스텔", "연립다세대"].includes(category)) {
          // 디테일 액션
          this.$store.dispatch("map/getDetail", [code, category]);
          this.setStructDetailPos(pos);
          this.setDetailToggle(true);
        }
      });
    },
    iconColor(category) {
      if(category == "아파트") {
        return 'gradient-red'
      } else if (category == "오피스텔") {
        return 'gradient-blue'
      } else if (category == "연립다세대") {
        return 'gradient-gray'
      } else {
        return 'gradient-red'
      }
    }
  },
};
</script>

<style></style>
