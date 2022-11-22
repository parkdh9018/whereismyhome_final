<template>
  <b-container style="height: 400px" class="overflow-auto text-left">
    <b-row>
      <b-col
        cols="12"
        @click="placeClickEvent(place.address)"
        v-for="place in structList"
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
      <b-col v-if="structList.length == 0"> 정보가 없습니다. </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapMutations, mapGetters } from "vuex";
import { searchPosition } from "@/api/areaApi";

export default {
  computed: {
    ...mapGetters("map", ["structList"]),
  },
  methods: {
    ...mapMutations("map", [
      "setCenter",
      "setDetailToggle",
      "setStructDetailPos",
    ]),
    placeClickEvent(address) {
      this.setDetailToggle(true);
      searchPosition(address).then((pos) => {
        this.setStructDetailPos(pos);
        this.setCenter(pos);
      });
    },
  },
};
</script>

<style></style>
