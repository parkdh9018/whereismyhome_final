<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- Card stats -->
      <!-- <b-row>
        <b-col xl="3" md="6">
        </b-col>
      </b-row> -->
    </base-header>

    <b-container class="mt--7">
      <b-row>
        <b-col>
          <b-card no-body class="border-0"><KaKaoMap></KaKaoMap></b-card>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-card no-body class="mt-3 border-0">
            <b-table :items="getAptdata" class="container-sm"> </b-table>
          </b-card>
        </b-col>
      </b-row>
    </b-container>

  </div>
</template>
<script>
import KaKaoMap from "./Maps/KaKaoMap.vue";
import { mapGetters } from "vuex";

export default {
  computed : {
    ...mapGetters("map",["aptlist"]),
    getAptdata() {
      return this.aptlist.map(({apartmentName,dong, roadName, jibun, buildYear}) => {return {apartmentName,dong, roadName, jibun, buildYear}});
    }
  },
  components: {
    KaKaoMap,
  },
  
};
</script>
