<template>
  <div id="map_wrap">
    <div id="map"></div>
    <div>
      <b-button-group v-if="!menuToggle" id="button_group" class="p-1">
        <b-button v-b-toggle.collapse-1 @click="menuButtonClick">
          <i class="ni ni-bold-right mr-2"></i>{{ address }}
        </b-button>
        <b-button v-b-toggle.collapse-1 class="ml-3">Button 2</b-button>
        <b-button class="ml-3">Button 3</b-button>
      </b-button-group>

      <b-collapse id="collapse-1" class="mt-2">
        <b-card>
          <p class="card-text">Collapse contents Here</p>
          <b-button v-b-toggle.collapse-1-inner size="sm"
            >Toggle Inner Collapse</b-button
          >
          <b-collapse id="collapse-1-inner" class="mt-2">
            <b-card>Hello!</b-card>
          </b-collapse>
        </b-card>
      </b-collapse>

      <b-container>
        <b-row id="menu_container">
          <b-col v-if="menuToggle">
            <MapMenu @closeEvent="menuButtonClick" :address="address" />
          </b-col>
          <b-col v-if="detailToggle">
            <MapMenuDetail />
          </b-col>
        </b-row>
      </b-container>
    </div>
  </div>
  <!-- <div>{{ center_address }}</div>
    <div>코드 : {{ gugunCode }}</div>
    <div>동 : {{ dongname }}</div>
    <div>레벨 : {{ level }}</div>
    <div>왼쪽 아래 : {{ southwest }}</div>
    <div>오른쪽 위 : {{ northeast }}</div> -->
</template>

<script>
import MapMenu from "./MapMenu.vue";
import MapMenuDetail from "@/components/Maps/MapMenuDetail";
import { searchAddress } from "@/api/areaApi";
import { mapGetters, mapMutations } from "vuex";

export default {
  name: "KakaoMap",
  components: {
    MapMenu,
    MapMenuDetail,
  },
  computed: {
    ...mapGetters("map", ["aptlist", "center", "address", "detailToggle"]),
  },
  data() {
    return {
      menuToggle: false,
      gugunCode: "",
      dongname: "",
      level: "",
      markers: [],
    };
  },
  mounted() {
    this.setDetailToggle(false);
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=79d74002f45b6b303a05b55e13f3d458&libraries=services,clusterer`;
      document.head.appendChild(script);
    }
  },
  watch: {
    center: function (pos) {
      console.log("center change");

      this.map.setCenter(pos);

      const bounds = this.map.getBounds();

      this.northeast = bounds.getNorthEast();
      this.southwest = bounds.getSouthWest();
      this.level = this.map.getLevel();

      //기존 마커 삭제
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.clusterer.removeMarkers(this.markers);

      if (this.level > 3) {
        return;
      }
      this.$store
        .dispatch("map/getaptlist_move", [
          bounds.getSouthWest(),
          bounds.getNorthEast(),
        ])
        .then(() => {
          this.aptlist.forEach((apt) => {
            this.makeMarker(new kakao.maps.LatLng(apt.lat, apt.lng));
          });
        });

      // 중심 위치 주소 변경
      searchAddress(pos).then((data) => {
        this.setAddress(
          `${data.region_1depth_name} ${data.region_2depth_name} ${data.region_3depth_name}`
        );
      });
    },
  },
  methods: {
    ...mapMutations("map", [
      "setCenter",
      "setAddress",
      "setDetailToggle",
      "setDetailToggle",
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
        gridSize: 200, //클러스터의 격자 크기. 화면 픽셀 단위이며 해당 격자 영역 안에 마커가 포함되면 클러스터에 포함시킨다
      });

      this.moveMap();

      //지도 중심 변경 이벤트 리스너
      kakao.maps.event.addListener(this.map, "idle", this.moveMap);
    },

    moveMap() {
      this.setCenter(this.map.getCenter());
    },

    // 마커 만들기
    makeMarker(position) {
      const marker = new kakao.maps.Marker({ position, clickable: true });
      marker.setMap(this.map);
      this.markers.push(marker);
      this.clusterer.addMarker(marker);

      //마커 클릭 이벤트
      kakao.maps.event.addListener(marker, "click", this.markerClickEvent);
    },

    markerClickEvent() {
      console.log("marker click");
      this.setDetailToggle(true);
    },

    menuButtonClick() {
      this.menuToggle = !this.menuToggle;
      this.setDetailToggle(false);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 730px;
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
  left: 217px;
  z-index: 1;
}
</style>
