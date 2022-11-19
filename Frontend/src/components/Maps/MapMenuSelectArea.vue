<template>
  <b-container>
    <b-row>
      <b-col>
        <b-breadcrumb>
          <b-breadcrumb-item @click="breadSidoClick">{{bread.sido}}</b-breadcrumb-item>
          <b-breadcrumb-item @click="breadGugunClick" v-if="tag == 'gugun' || tag == 'dong'" href="#library">{{bread.gugun}}</b-breadcrumb-item>
          <b-breadcrumb-item v-if="tag == 'dong'" href="#data" active>{{bread.dong}}</b-breadcrumb-item>
        </b-breadcrumb>
      </b-col>
    </b-row>
    <b-row class="overflow-auto">
      <b-col>
        <b-button class="mb-2 mr-2" @click="btnClick(area.code, area.name)" v-for="area in area_list" :key="area.code">{{
          area.name
        }}</b-button>
      </b-col>
    </b-row>
    <b-row>
        <!-- 지도이동 -->
    </b-row>
  </b-container>
</template>

<script>
import {areaList} from "@/api/areaApi";

export default {
  name: "MapMenuSelectAarea",
  data() {
    return {
      area_list: [],
      tag : "",
      code : "",
      bread : {}
    };
  },
  watch : {
    tag : function(val) {
        let regcode_pattern;
        console.log(val);

        if(val == "sido") {
            regcode_pattern = '*00000000';
        } else if (val == "gugun") {
            regcode_pattern = `${this.code.slice(0, 2)}*000000`;
        } else {
            regcode_pattern = `${this.code.slice(0, 4)}*`;
        }

        const params = {
            regcode_pattern, is_ignore_zero : true
        }

        if(val == "sido") {
            areaList(params,(res) => {
                this.area_list = res.data.regcodes.map(v => {return {code : v.code, name : v.name}});
                // this.bread.sido = name;
            })
        } else if (val == 'gugun') {
            areaList(params,(res) => {
                this.area_list = res.data.regcodes.map(v => {return {code : v.code, name : v.name.split(' ')[1]}});
                // this.bread.gugun = name;
            })
        } else {
            areaList(params,(res) => {
                this.area_list = res.data.regcodes.map(v => {return {code : v.code, name : v.name.split(' ')[2]}});
            })
        }
        
    }
  },
  created() {
    this.tag = "sido";
    this.bread = {
        sido: "시/도",
        gugun: "구/군",
        dong : "읍/면/동"
    }
  },
  methods : {
    btnClick(code, name) {
        if(this.tag == "sido") {
            this.tag = "gugun";
            this.bread.sido = name;
        } else if(this.tag == 'gugun') {
            this.tag = "dong";
            this.bread.gugun = name;
        } else if(this.tag == 'dong') {
            console.log('dongclick')
        }
        this.code = code;
    },
    breadSidoClick() {
        this.bread.sido = "시/도";
        this.tag = "sido";
    },
    breadGugunClick() {
        this.bread.gugun = "구/군";
        this.tag = "gugun";
    },
  }
};
</script>

<style >
.breadcrumb-item + .breadcrumb-item::before {
    content: ">";
}
</style>
