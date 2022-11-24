<template>
  <b-container class="overflow-auto text-left">
    <b-row>
      <b-col
        cols="12"
        @click="placeClickEvent(place.address)"
        v-for="place in favoriteList"
        :key="place.id"
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
      "setCenter",
      "setDetailToggle",
      "setStructDetailPos",
      "setLevel",
    ]),
    placeClickEvent(address) {
      searchPosition(address).then((pos) => {
        this.setDetailToggle(true);
        this.setLevel(2);
        this.setCenter(pos);
        this.setStructDetailPos(pos);
      });
    },
  },
};
</script>

<style></style>
