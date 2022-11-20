<template>
  <b-container style="height: 400px" class="overflow-auto text-left">
    <b-row>
      <b-col cols="12"
        v-for="place in placeList"
        :key="place.id">
        <stats-card :title="place.category"
                      type="gradient-red"
                      :sub-title="place.name"
                      icon="ni ni-active-40"
                      class="mb-1">
            <template slot="footer">
              <span class="text-success mr-2">{{place.address}}</span>
            </template>
          </stats-card>
        </b-col>
      <b-col v-if="placeList.length == 0"> 정보가 없습니다. </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  props: {
    placeList : Array,
    // id, name, address, category, pos(lng, ltg), place_url
  },
};
</script>

<style>
</style>