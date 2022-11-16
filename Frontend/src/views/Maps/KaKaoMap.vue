<template>
  <div id="map_wrap">
    <div id="map"></div>
    <b-button id="menuButton">button</b-button>
    <!-- <MapMenu></MapMenu> -->
  </div>
  <!-- <div>{{ center_address }}</div>
    <div>코드 : {{ gugunCode }}</div>
    <div>동 : {{ dongname }}</div>
    <div>레벨 : {{ level }}</div>
    <div>왼쪽 아래 : {{ southwest }}</div>
    <div>오른쪽 위 : {{ northeast }}</div> -->
</template>

<script>
import axios from "axios";
import MapMenu from "./MapMenu.vue";
import { mapGetters } from "vuex";

export default {
  name: "KakaoMap",
  components: {
    MapMenu,
  },
  computed: {
    ...mapGetters(["aptlist"]),
  },
  data() {
    return {
      APP_KEY: "79d74002f45b6b303a05b55e13f3d458",
      center_address: "",
      gugunCode: "",
      dongname: "",
      level: "",
      southwest: "",
      northeast: "",
      markers: [],
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${this.APP_KEY}&libraries=services,clusterer`;
      document.head.appendChild(script);
    }
  },
  // watch: {
  //   gugunCode: function (val) {

  //   },
  // },
  methods: {
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

      this.changeCenter();

      //지도 중심 변경 이벤트 리스너
      kakao.maps.event.addListener(this.map, "idle", this.moveMap);
    },

    moveMap() {
      this.changeCenter();
      this.changeGunguncode(this.center);
    },

    changeGunguncode(latlng) {
      this.searchAddress(latlng).then((data) => {
        this.ADDRESS_INIT = `${data.region_1depth_name} ${data.region_2depth_name}`;
        this.center_address = data.address_name;
        axios
          .get(
            `http://localhost:9999/vue/map/code?sido=${data.region_1depth_name}&gugun=${data.region_2depth_name}`
          )
          .then((res) => {
            this.gugunCode = res.data.gugunCode.slice(0, 5);
            this.dongname = data.region_3depth_name;
          });
      });
    },

    changeCenter() {
      this.center = this.map.getCenter();
      const bounds = this.map.getBounds();

      this.northeast = bounds.getNorthEast();
      this.southwest = bounds.getSouthWest();
      this.level = this.map.getLevel();

      //기존 마커 삭제
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.clusterer.removeMarkers(this.markers);

      if (this.level > 3)  {
        
        return;
      }
      this.$store
        .dispatch("getaptlist_move", [
          bounds.getSouthWest(),
          bounds.getNorthEast(),
        ])
        .then(() => {
          this.aptlist.forEach((apt) => {
            this.makeMarker(new kakao.maps.LatLng(apt.lat, apt.lng));
          });
        });
    },

    // 마커 만들기
    makeMarker(position) {
      const marker = new kakao.maps.Marker({ position });
      marker.setMap(this.map);
      this.markers.push(marker);
      this.clusterer.addMarker(marker);
    },

    // 주소로 위도, 경도 찾는 함수
    searchPosition(address) {
      return new Promise((resolve, reject) => {
        const geocoder = new kakao.maps.services.Geocoder();

        geocoder.addressSearch(address, function (result, status) {
          if (status === kakao.maps.services.Status.OK) {
            const point = new kakao.maps.LatLng(result[0].y, result[0].x);
            resolve(point);
          } else {
            reject(status);
          }
        });
      });
    },

    // 위도, 경도로 주소를 찾는 함수
    searchAddress(position) {
      return new Promise((resolve, reject) => {
        const geocoder = new kakao.maps.services.Geocoder();

        geocoder.coord2Address(
          position.getLng(),
          position.getLat(),
          function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
              for (var i = 0; i < result.length; i++) {
                resolve(result[i].address);
              }
            } else {
              reject(status);
            }
          }
        );
      });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 700px;
}

#map_wrap {
  position: relative;
}

#menuButton {
  position: absolute;
  top: 10px;
  left: 10px;
  z-index: 1;
}
</style>
