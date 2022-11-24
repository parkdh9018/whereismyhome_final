<template>
  <b-card no-body id="detail_card">
    <b-row no-gutters>
      <b-col md="12">
        <b-button
          :pressed.sync="favoriteToggle"
          v-show="checkUserInfo"
          variant="success"
          style="z-index: 1; right: 0"
          class="position-absolute m-2"
          >{{
            favoriteToggle == true ? "즐겨찾기 해제" : "즐겨찾기 등록"
          }}</b-button
        >
        <div id="roadview" style="width: 100%; height: 300px"></div>
      </b-col>
      <b-col md="12">
        <b-card-header>
          <span
            ><b-badge pill variant="info"> 이름 </b-badge> {{ bldg_name }}</span
          >
          <span class="float-right"
            ><b-badge pill variant="info"> 건축년도 </b-badge>
            {{ buildYear }}</span
          >
        </b-card-header>
        <b-card-body>
          <b-card-text class="font-weight-bold">
            <span>면적 : {{ bldg_area }} m² | {{ tot_area }} m²</span>
            <h2 class="float-right mr-2">
              <b-badge variant="info">{{ house_type }}</b-badge>
            </h2>
          </b-card-text>
          <b-card-text class="border p-3">
            <h2>시세</h2>
            <stats-card class="bg-gradient-primary mb-2">
              <h5 class="card-title text-uppercase text-white mb-1">
                <b-badge class="bg-green text-white"> 매매 </b-badge>
              </h5>
              <div class="text-white">최근실거래가</div>
              <span class="h2 font-weight-bold mb-0 text-white">
                {{ current_sale.price }} ({{ current_sale.day | dateFilter }} /
                {{ current_sale.floor }}층)
              </span>
              <template slot="footer">
                <div>
                  <span class="text-white mr-2">최고가</span>
                  <span class="text-nowrap text-white">{{
                    structDetail.max_amt
                  }}</span>
                </div>
                <div>
                  <span class="text-white mr-2">최저가</span>
                  <span class="text-nowrap text-white">{{
                    structDetail.min_amt
                  }}</span>
                  <span class="float-right">
                    <span class="text-white mr-2">평균가</span>
                    <span class="text-nowrap text-white">{{
                      structDetail.avg_amt
                    }}</span>
                  </span>
                </div>
              </template>
            </stats-card>
            <stats-card class="bg-gradient-primary mb-2">
              <h5 class="card-title text-uppercase text-white mb-1">
                <b-badge class="bg-orange text-white"> 전세 </b-badge>
              </h5>
              <div class="text-white">최근실거래가</div>
              <span class="h2 font-weight-bold mb-0 text-white">
                {{ current_rent.price }} ({{ current_rent.day | dateFilter }} /
                {{ current_rent.floor }}층)
              </span>
              <template slot="footer">
                <div>
                  <span class="text-white mr-2">최고가</span>
                  <span class="text-nowrap text-white"></span>
                  <span class="float-right">
                    <span class="text-white mr-2">평균가</span>
                    <span class="text-nowrap text-white"></span>
                  </span>
                </div>
              </template>
            </stats-card>
            <stats-card class="bg-gradient-info mb-2">
              <h5 class="card-title text-uppercase text-white mb-0">
                <b-badge class="bg-gray text-white"> 월세 </b-badge>
              </h5>
              <span class="h2 font-weight-bold mb-0 text-white"
                >{{ current_month.gtn }} / {{ current_month.fee }}</span
              >
            </stats-card>
          </b-card-text>
          <b-card-text class="border p-3">
            <h2>시세추이</h2>
            <card type="default" header-classes="bg-transparent">
              <b-row align-v="center" slot="header">
                <b-col>
                  <h6 class="text-light text-uppercase ls-1 mb-1">Overview</h6>
                  <h5 class="h3 text-white mb-0">Sales value</h5>
                </b-col>
                <b-col>
                  <b-nav class="nav-pills justify-content-end">
                    <b-nav-item
                      class="mr-2 mr-md-0"
                      :active="bigLineChart.activeIndex === 0"
                      link-classes="py-2 px-3"
                      @click.prevent="initBigChart(0)"
                    >
                      <span class="d-none d-md-block">Month</span>
                      <span class="d-md-none">M</span>
                    </b-nav-item>
                    <b-nav-item
                      link-classes="py-2 px-3"
                      :active="bigLineChart.activeIndex === 1"
                      @click.prevent="initBigChart(1)"
                    >
                      <span class="d-none d-md-block">Week</span>
                      <span class="d-md-none">W</span>
                    </b-nav-item>
                  </b-nav>
                </b-col>
              </b-row>
              <line-chart
                :height="350"
                ref="bigChart"
                :chart-data="bigLineChart.chartData"
                :extra-options="bigLineChart.extraOptions"
              >
              </line-chart>
            </card>
          </b-card-text>
          <b-card-text class="border p-3">
            <h2>실거래가</h2>
            <b-table class="text-center" striped hover :items="tableData">
            </b-table>
          </b-card-text>
        </b-card-body>
      </b-col>
    </b-row>
  </b-card>
</template>

<script>
import { mapMutations, mapGetters } from "vuex";
import * as chartConfigs from "@/components/Charts/config";
import LineChart from "@/components/Charts/LineChart";
import { lab } from "d3";

export default {
  data() {
    return {
      favoriteToggle: null,
      house_type: "",
      bldg_name: "",
      buildYear: "",
      dealAmount: "",
      bldg_area: "",
      tot_area: "",
      housedealList: [],
      current_sale: {
        price: "-",
        day: "-",
        floor: "-",
      },
      current_rent: {
        price: "-",
        day: "-",
        floor: "-",
      },
      current_month: {
        gtn: "-",
        fee: "-",
      },
      bigLineChart: {
        allData: [
          [0, 20, 10, 30, 15, 40, 20, 60, 60],
          [0, 20, 5, 25, 10, 30, 15, 40, 40],
        ],
        activeIndex: 0,
        chartData: {
          datasets: [
            {
              label: "Performance",
              data: [0, 20, 10, 30, 15, 40, 20, 60, 60],
            },
          ],
          labels: ["May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
        },
        extraOptions: chartConfigs.blueChartOptions,
      },
      tableData: [],
    };
  },
  components: {
    LineChart,
  },
  filters: {
    dateFilter: function (value) {
      if (!value || value == "-") return "-";
      return `${value.slice(0, 4)}.${value.slice(4, 6)}.${value.slice(6, 8)}`;
    },
    moneyFilter: function (value) {
      if (!value || value == "-") return "-";
      return;
    },
  },
  mounted() {
    this.favoriteToggle = this.favoriteList
      .map((v) => v.code)
      .includes(this.sgdbb_cd);
  },
  watch: {
    structDetail: function () {
      this.tableData = this.structDetail.housedealDto.map((v) => {
        return {
          계약일: v.deal_ymd,
          거래: v.deal_type,
          가격: (v.obj_amt = "" ? `${v.rent_gtn} / ${v.rent_fee}` : v.obj_amt),
          층: v.floor,
        };
      });

      this.housedealList = this.structDetail.housedealDto;

      this.house_type = this.housedealList[0].house_type;
      this.bldg_name = this.housedealList[0].bldg_nm;
      this.buildYear = this.housedealList[0].build_year;
      this.bldg_area = this.housedealList[0].bldg_area;
      this.tot_area = this.housedealList[0].tot_area;

      this.housedealList.forEach((v) => {
        if (v.deal_type == "매매" && this.current_sale.price == "-") {
          this.current_sale.price = v.obj_amt;
          this.current_sale.day = v.deal_ymd;
          this.current_sale.floor = v.floor;
        } else if (v.deal_type == "전세" && this.current_rend.price == "-") {
          this.current_sale.price = v.rend_gtn;
          this.current_sale.day = v.deal_ymd;
          this.current_sale.floor = v.floor;
        } else if (v.deal_type == "월세" && this.current_month.gtn == "-") {
          this.current_month.gtn = v.rent_gtn;
          this.current_month.fee = v.rent_fee;
        }
      });

      console.log("===== char data");
      console.log(this.structDetail.amtDto);

      let label = [];
      let data = [];

      this.structDetail.amtDto.forEach((v) => {
        label.push(`${v.yearmonth.slice(0, 4)}.${v.yearmonth.slice(4, 6)}`);
        data.push(v.obj_amt);
      });
    },
  },
  updated() {
    console.log("=====update");
    var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
    var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
    var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
    // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
    const val = this.structDetailPos;
    roadviewClient.getNearestPanoId(val, 20, function (panoId) {
      roadview.setPanoId(panoId, val); //panoId와 중심좌표를 통해 로드뷰 실행
    });
  },
  computed: {
    ...mapGetters("map", ["structDetailPos", "address", "structDetail"]),
    ...mapGetters("member", ["checkUserInfo", "favoriteList"]),
  },
  methods: {
    ...mapMutations("map", ["setDetailToggle"]),
    initBigChart(index) {
      let chartData = {
        datasets: [
          {
            label: "Performance",
            data: this.bigLineChart.allData[index],
          },
        ],
        labels: ["May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
      };
      this.bigLineChart.chartData = chartData;
      this.bigLineChart.activeIndex = index;
    },
  },
};
</script>

<style scoped>
#detail_card {
  max-height: 75vh;
  overflow: auto;
}
</style>
