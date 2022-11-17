<template>
  <div>
    <base-header class="pb-2 pt-6 bg-gradient-success">
<!--     
      <b-row>
        <b-col xl="3" md="6">
        </b-col>
      </b-row>  -->
    </base-header>

    <b-container fluid="xl" class="mt--10">
      <b-row>
        <b-col>
          <b-card no-body class="border-1"><KaKaoMap></KaKaoMap></b-card>
        </b-col>
      </b-row>

      <!-- <b-row>
        <b-col>
          <b-card no-body class="mt-3 border-0">
            <b-table :items="getAptdata" class="container-sm"> </b-table>
          </b-card>
        </b-col>
      </b-row> -->
    </b-container>

  </div>
</template>
<script>
import KaKaoMap from "@/components/Maps/KaKaoMap.vue";
import { mapGetters } from "vuex";

export default {
  name: "MapView",
  computed : {
    ...mapGetters("map",["aptlist"]),
    getAptdata() {
      return this.aptlist.map(({apartmentName,dong, roadName, jibun, buildYear}) => {return {apartmentName,dong, roadName, jibun, buildYear}});
    }
  },
  components: {
    KaKaoMap
  },
  
};
</script>
