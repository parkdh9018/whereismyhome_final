<template>
  <div class="text-center">
    <b-breadcrumb>
      <b-breadcrumb-item @click="breadSidoClick">{{
        bread.sido
      }}</b-breadcrumb-item>
      <b-breadcrumb-item
        @click="breadGugunClick"
        v-if="tag == 'gugun' || tag == 'dong' || tag == 'apt'"
        >{{ bread.gugun }}</b-breadcrumb-item
      >
      <b-breadcrumb-item v-if="tag == 'dong' || tag == 'apt'">{{
        bread.dong
      }}</b-breadcrumb-item>
    </b-breadcrumb>
    <b-container style="height: 400px" class="overflow-auto">
      <b-row v-if="tag != 'apt'">
        <b-col
          class="border rounded text-center"
          cols="6"
          @click="btnClick(area.code, area.name)"
          v-for="area in area_list"
          :key="area.code"
        >
          {{ area.name }}
        </b-col>
      </b-row>
      <b-row v-else>
        <MapMenuPlaceList />
      </b-row>
    </b-container>
    <b-button v-show="bread.sido != '시/도'" @click="changeCenter" class="mt-4"
      >{{ this.total_address }} 지도로 이동</b-button
    >
  </div>
</template>

<script>
import { areaList, searchPosition } from "@/api/areaApi";
import { mapMutations, mapGetters } from "vuex";
import MapMenuPlaceList from "./MapMenuPlaceList.vue";

export default {
  name: "MapMenuSelectAarea",
  components: {
    MapMenuPlaceList,
  },
  data() {
    return {
      area_list: [],
      tag: "",
      code: "",
      bread: {},
      dongAptlist: [],
    };
  },
  computed: {
    ...mapGetters("map", ["address"]),
    total_address() {
      return `${this.bread.sido == "시/도" ? "" : this.bread.sido} ${
        this.bread.gugun == "구/군" ? "" : this.bread.gugun
      } ${this.bread.dong == "읍/면/동" ? "" : this.bread.dong}`;
    },
  },
  watch: {
    tag: function (val) {
      let regcode_pattern;
      console.log(val);

      if (val == "sido") {
        regcode_pattern = "*00000000";
      } else if (val == "gugun") {
        regcode_pattern = `${this.code.slice(0, 2)}*000000`;
      } else {
        regcode_pattern = `${this.code.slice(0, 4)}*00`;
      }

      const params = {
        regcode_pattern,
        is_ignore_zero: true,
      };

      if (val == "sido") {
        areaList(params, (res) => {
          this.area_list = res.data.regcodes.map((v) => {
            return { code: v.code, name: v.name };
          });
          this.bread.gugun = "구/군";
          this.bread.dong = "읍/면/동";
        });
      } else if (val == "gugun") {
        areaList(params, (res) => {
          this.area_list = res.data.regcodes.map((v) => {
            return { code: v.code, name: v.name.split(" ").slice(-1)[0] };
          });
          this.bread.dong = "읍/면/동";
        });
      } else {
        areaList(params, (res) => {
          this.area_list = res.data.regcodes.map((v) => {
            return { code: v.code, name: v.name.split(" ").slice(-1)[0] };
          });
        });
      }
    },
  },
  created() {
    this.tag = "sido";
    this.bread = {
      sido: "시/도",
      gugun: "구/군",
      dong: "읍/면/동",
    };
  },
  methods: {
    ...mapMutations("map", ["setCenter", "levelMove"]),
    btnClick(code, name) {
      this.code = code;
      if (this.tag == "sido") {
        this.tag = "gugun";
        this.bread.sido = name;
      } else if (this.tag == "gugun") {
        this.tag = "dong";
        this.bread.gugun = name;
      } else if (this.tag == "dong") {
        this.tag = "apt";
        this.bread.dong = name;

        // 동 리스트들
        this.$store.dispatch("map/getStructInDong", [this.total_address, code]);
      }
    },
    breadSidoClick() {
      this.bread.sido = "시/도";
      this.tag = "sido";
    },
    breadGugunClick() {
      this.bread.gugun = "구/군";
      this.tag = "gugun";
    },
    changeCenter() {
      searchPosition(this.total_address).then((pos) => {
        this.levelMove(3);
        this.setCenter(pos);
      });
    },
  },
};
</script>

<style>
.breadcrumb-item + .breadcrumb-item::before {
  content: ">";
}
</style>
