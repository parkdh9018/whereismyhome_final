<template>
  <div class="text-center">
    <b-breadcrumb>
      <b-breadcrumb-item @click="breadSidoClick">{{
        bread.sido
      }}</b-breadcrumb-item>
      <b-breadcrumb-item
        @click="breadGugunClick"
        v-if="tag == 'gugun' || tag == 'dong'"
        >{{ bread.gugun }}</b-breadcrumb-item
      >
      <b-breadcrumb-item v-if="tag == 'dong'" href="#data" active>{{
        bread.dong
      }}</b-breadcrumb-item>
    </b-breadcrumb>
    <b-container class="overflow-auto" style="height : 400px">
      <b-row>
        <b-col class="border rounded text-center" cols="6" @click="btnClick(area.code, area.name)"  v-for="area in area_list" :key="area.code">
            {{ area.name}}
          </b-col>
      </b-row>
    </b-container>
    <b-button v-show="bread.sido != '시/도'" @click="changeCenter" class="mt-4">{{this.btn_address}} 지도로 이동</b-button>
  </div>
</template>

<script>
import { areaList, searchPosition } from "@/api/areaApi";
import { mapMutations, mapGetters } from "vuex";

export default {
  name: "MapMenuSelectAarea",
  data() {
    return {
      area_list: [],
      tag: "",
      code: "",
      bread: {},
      btn_address: "",
    };
  },
  computed : {
    ...mapGetters("map", ["address"]),
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
          // this.bread.sido = name;
        });
      } else if (val == "gugun") {
        areaList(params, (res) => {
          this.area_list = res.data.regcodes.map((v) => {
            return { code: v.code, name: v.name.split(" ").slice(-1)[0] };
          });
          // this.bread.gugun = name;
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
    ...mapMutations("map", ["setCenter"]),
    btnClick(code, name) {
      if (this.tag == "sido") {
        this.tag = "gugun";
        this.bread.sido = name;
      } else if (this.tag == "gugun") {
        this.tag = "dong";
        this.bread.gugun = name;
      } else if (this.tag == "dong") {
        //뭔가 해야해!
      }
      this.code = code;
      this.btn_address = `${this.bread.sido == '시/도' ? '' : this.bread.sido} ${this.bread.gugun == '구/군' ? '' : this.bread.gugun}`
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
      searchPosition(this.btn_address).then((pos) => {
        this.setCenter(pos);
      })
    },



  },
};
</script>

<style >
.breadcrumb-item + .breadcrumb-item::before {
  content: ">";
}
</style>
