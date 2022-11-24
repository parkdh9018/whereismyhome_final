<template>
  <b-container style="height: 400px" class="overflow-auto text-left">
    <b-row>
      <b-col
        cols="12"
        @click="placeClickEvent(place.address, place.category, place.id)"
        v-for="(place, index) in getPlaceList"
        :key="index"
      >
        <stats-card
          :title="place.category"
          type="gradient-red"
          :sub-title="place.name"
          icon="ni ni-active-40"
          class="mb-1"
        >
          <template slot="footer">
            <span class="text-success mr-2">{{ place.address }}</span>
          </template>
        </stats-card>
      </b-col>
      <b-col v-if="structList.length == 0"> 정보가 없습니다. </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapMutations, mapGetters } from "vuex";
import { searchPosition } from "@/api/areaApi";

export default {
  data() {
    return {
      placeList : [],
    }
  },
  computed: {
    ...mapGetters("map", ["structList"]),
    getPlaceList() {
      return this.structList;
    }
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
  },
};
</script>

<style></style>
