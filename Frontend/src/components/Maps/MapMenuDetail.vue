<template>
  <b-card no-body class="overflow-hidden" style="max-width: 540px">
    <b-row no-gutters>
      <b-col md="12">
        <div id="roadview" style="width: 100%; height: 300px"></div>
      </b-col>
      <b-col md="12">
        <b-card-body title="Horizontal Card">
          <b-card-text>
            This is a wider card with supporting text as a natural lead-in to
            additional content. This content is a little bit longer.
          </b-card-text>
        </b-card-body>
      </b-col>
    </b-row>
  </b-card>
</template>

<script>
import { mapMutations, mapGetters } from "vuex";

export default {
  updated() {
    console.log("udpate");
  },
  mounted() {},
  watch: {
    structDetailPos: function (val) {
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
      var position = new kakao.maps.LatLng(this.structDetailPos);
      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(val, 20, function (panoId) {
        roadview.setPanoId(panoId, val); //panoId와 중심좌표를 통해 로드뷰 실행
      });
    },
  },
  computed: {
    ...mapGetters("map", ["structDetailPos"]),
  },
  methods: {
    ...mapMutations("map", ["setDetailToggle"]),
  },
};
</script>

<style scoped></style>
