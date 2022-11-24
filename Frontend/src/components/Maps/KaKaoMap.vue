<template>
  <div id="map_wrap">
    <div id="map"></div>
    <div>
      <b-button-group v-if="!detailToggle && !menuToggle" id="button_group">
        <b-button pill v-b-toggle.collapse-1 @click="menuButtonClick">
          <i class="ni ni-bold-right mr-2"></i>{{ address }}
        </b-button>
        <b-button
          pill
          v-b-toggle.collapse-1
          @click="filterClick(0)"
          class="ml-3"
          >{{ filterStr0 == "" ? "전체" : filterStr0 }}</b-button
        >
        <b-button
          pill
          v-b-toggle.collapse-1
          @click="filterClick(1)"
          class="ml-3"
          >{{ filterStr1 == "" ? "전체" : filterStr1 }}</b-button
        >
      </b-button-group>

      <b-collapse id="collapse-1" class="mt-2">
        <b-card>
          <b-button
            v-for="(btn, idx) in filter_buttons[filter_num]"
            :key="idx"
            :pressed.sync="btn.state"
            variant="primary"
          >
            {{ btn.caption }}
          </b-button>
        </b-card>
      </b-collapse>

      <div id="menu_container">
        <b-row>
          <b-col style="width: 400px" v-if="menuToggle">
            <MapMenu @closeEvent="menuButtonClick" :address="address" />
          </b-col>
          <b-col style="width: 550px" v-if="detailToggle">
            <MapMenuDetail />
          </b-col>
        </b-row>
      </div>
    </div>
  </div>
</template>

<script>
import MapMenu from "./MapMenu.vue";
import MapMenuDetail from "@/components/Maps/MapMenuDetail";
import { getaptlist_move, searchAddress } from "@/api/areaApi";
import {
  markerSido,
  markerGugun,
  markerDong,
  searchDongAvg,
} from "@/api/markerApi";
import { mapState, mapGetters, mapMutations } from "vuex";

export default {
  name: "KakaoMap",
  components: {
    MapMenu,
    MapMenuDetail,
  },
  computed: {
    ...mapState("map", ["filter_buttons"]),
    ...mapGetters("map", ["center", "address", "detailToggle", "level"]),
    filterStr0() {
      return this.filter_buttons[0]
        .filter((v) => v.state)
        .map((v) => v.caption)
        .join(",");
    },
    filterStr1() {
      return this.filter_buttons[1]
        .filter((v) => v.state)
        .map((v) => v.caption)
        .join(",");
    },
  },
  data() {
    return {
      map: null,
      menuToggle: false,
      gugunCode: "",
      dongname: "",
      markers: [],
      filter_num: 0,
    };
  },
  mounted() {
    console.log("---mout");
    this.setDetailToggle(false);
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    }
  },
  watch: {
    center: function (pos) {
      console.log("center change");

      this.map.setCenter(pos);

      const bounds = this.map.getBounds();
      const level = this.map.getLevel();

      //기존 마커 삭제
      // this.clusterer.removeMarkers(this.markers);
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];

      const params = {
        lat1: bounds.getSouthWest().Ma,
        lng1: bounds.getSouthWest().La,
        lat2: bounds.getNorthEast().Ma,
        lng2: bounds.getNorthEast().La,
      };

      // 마커 찍기
      if (level <= 4) {
        // 일반 마커
        getaptlist_move(params, ({ data }) => {
          data.forEach((apt) => {
            this.makeMarker(new kakao.maps.LatLng(apt.lat, apt.lng));
          });
        });
      } else if (level >= 5 && level <= 6) {
        // 동 마커
        searchDongAvg(params, ({ data }) => {
          data.forEach((v) => {
            this.makeClusterMarker(v, "dongName");
          });
        });
      } else if (level >= 7 && level <= 8) {
        // 구/군 마커
        markerGugun(params).then(({ data }) => {
          data.forEach((v) => {
            this.makeClusterMarker(v, "gugunName");
          });
        });
      } else {
        // 시/도 마커
        markerSido(params).then(({ data }) => {
          data.forEach((v) => {
            this.makeClusterMarker(v, "sidoName");
          });
        });
      }

      // 중심 위치 주소 변경
      searchAddress(pos).then((data) => {
        this.setAddress(
          `${data.region_1depth_name} ${data.region_2depth_name} ${data.region_3depth_name}`
        );
      });
    },
    level: function (val) {
      console.log("level change" + val);
      this.map.setLevel(val);
      this.levelMove(0);
    },
  },
  methods: {
    ...mapMutations("map", [
      "levelMove",
      "setCenter",
      "setAddress",
      "setDetailToggle",
      "setStructDetailPos",
    ]),
    initMap() {
      const container = document.getElementById("map");

      const options = {
        center: new kakao.maps.LatLng(37.5012767241426, 127.039600248343),
        level: 2,
      };

      const imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      this.markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        new kakao.maps.Size(24, 35)
      );

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      //클러스터
      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 3, // 클러스터 할 최소 지도 레벨
        gridSize: 60, //클러스터의 격자 크기. 화면 픽셀 단위이며 해당 격자 영역 안에 마커가 포함되면 클러스터에 포함시킨다
      });

      this.moveMap();

      //지도 중심 변경 이벤트 리스너
      kakao.maps.event.addListener(this.map, "idle", this.moveMap);
      kakao.maps.event.addListener(this.map, "click", this.detailClose);
    },

    moveMap() {
      this.setCenter(this.map.getCenter());
    },

    // 마커 만들기
    makeMarker: function (position) {
      const marker = new kakao.maps.Marker({ position, clickable: true });
      marker.setMap(this.map);
      this.markers.push(marker);
      // this.clusterer.addMarker(marker);

      //마커 클릭 이벤트
      const setDetailToggle = this.setDetailToggle;
      const setCenter = this.setCenter;
      const setStructDetailPos = this.setStructDetailPos;
      const levelMove = this.levelMove;
      kakao.maps.event.addListener(marker, "click", function () {
        setDetailToggle(true);
        setCenter(marker.getPosition());
        levelMove(2);
        setStructDetailPos(marker.getPosition());
      });
    },

    // 클러스터 마커 만들기
    makeClusterMarker(data, type) {
      const content = `<span class="p-2 font-weight-bold badge badge-pill bg-white" style="font-size: 14px;">
        ${data[type]}
        <span class="badge font-weight-bold bg-red text-white" style="font-size: 14px;">${data.amountAvg}억</span>
      </span>`;

      // 커스텀 오버레이를 생성합니다
      const customOverlay = new kakao.maps.CustomOverlay({
        position: new kakao.maps.LatLng(data.lat, data.lng),
        content: content,
        xAnchor: 0.3,
        yAnchor: 0.91,
      });
      this.markers.push(customOverlay);

      // 커스텀 오버레이를 지도에 표시합니다
      customOverlay.setMap(this.map);
    },

    menuButtonClick() {
      this.menuToggle = !this.menuToggle;
      this.setDetailToggle(false);
    },

    filterClick(num) {
      this.filter_num = num;
    },

    detailClose() {
      console.log("detail close");
      this.setDetailToggle(false);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 75vh;
  z-index: inherit;
}

#map_wrap {
  position: relative;
}

#button_group {
  position: absolute;
  top: 10px;
  left: 10px;
  z-index: 1;
}

#menu_container {
  position: absolute;
  top: 0px;
  left: 0px;
  z-index: 1;
}

#collapse-1 {
  position: absolute;
  top: 60px;
  left: 10px;
  z-index: 1;
}
</style>
