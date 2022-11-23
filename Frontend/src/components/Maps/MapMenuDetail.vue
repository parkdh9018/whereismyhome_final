<template>
  <b-card no-body id="detail_card">
    <b-row no-gutters>
      <b-col md="12">
        <div id="roadview" style="width: 100%; height: 300px"></div>
      </b-col>
      <b-col md="12">
        <b-card-header>
          <span
            ><b-badge pill variant="info"> 이름 </b-badge>
            {{ bldg_name }}</span
          >
          <span class="float-right"
            ><b-badge pill variant="info"> 건축년도 </b-badge>
            {{ buildYear }}</span
          >
        </b-card-header>
        <b-card-body>
          <b-card-text class=" font-weight-bold">
            <span>면적 : {{ bldg_area }} m² | {{ tot_area }} m²</span>
            <h2 class="float-right mr-2"><b-badge variant="info">{{type}}</b-badge></h2>
          </b-card-text>
          <b-card-text class="border p-3">
            <h2> 시세</h2>
            <stats-card class="bg-gradient-primary mb-2">
              <h5 class="card-title text-uppercase text-white mb-1">
                <b-badge class="bg-green text-white"> 매매 </b-badge>
              </h5>
              <div class="text-white">
                최근실거래가
              </div>
              <span class="h2 font-weight-bold mb-0 text-white">
                18억 8,000만 (22.07 / 4층)
              </span>
              <template slot="footer">
                <div>
                  <span class="text-white mr-2">최고가</span>
                  <span class="text-nowrap text-white">10억 9,000</span>
                </div>
                <div>
                  <span class="text-white mr-2">최저가</span>
                  <span class="text-nowrap text-white">10억 9,000</span>
                  <span class="float-right">
                    <span class="text-white mr-2">평균가</span>
                    <span class="text-nowrap text-white">10억 9,000</span>
                  </span>
                </div>
              </template>
            </stats-card>
            <stats-card class="bg-gradient-primary mb-2">
              <h5 class="card-title text-uppercase text-white mb-1">
                <b-badge class="bg-orange text-white"> 전세 </b-badge>
              </h5>
              <div class="text-white">
                최근실거래가
              </div>
              <span class="h2 font-weight-bold mb-0 text-white">
                7억 8,000만 (22.07 / 4층)
              </span>
              <template slot="footer">
                <div>
                  <span class="text-white mr-2">최고가</span>
                  <span class="text-nowrap text-white">10억 9,000</span>
                </div>
                <div>
                  <span class="text-white mr-2">최저가</span>
                  <span class="text-nowrap text-white">10억 9,000</span>
                  <span class="float-right">
                    <span class="text-white mr-2">평균가</span>
                    <span class="text-nowrap text-white">10억 9,000</span>
                  </span>
                </div>
              </template>
            </stats-card>
            <stats-card class="bg-gradient-info mb-2">
              <h5 class="card-title text-uppercase text-white mb-0">
                <b-badge class="bg-gray text-white"> 월세 </b-badge>
              </h5>
              <span class="h2 font-weight-bold mb-0 text-white"
                >5000만 / 255만</span
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

export default {
  data() {
    return {
      type: "아파트",
      sell_type: "매매",
      bldg_name: "동부센트레빌",
      buildYear: "2021.3",
      dealAmount: "20,000",
      bldg_area: "105",
      tot_area: "82",
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
      tableData : [
          { 계약일: '2022.11.17', 거래 : '월세', 가격 : '2억 3000', 층 : '12층' },
          { 계약일: '2022.11.17', 거래 : '월세', 가격 : '2억 3000', 층 : '12층' },
          { 계약일: '2022.11.17', 거래 : '월세', 가격 : '2억 3000', 층 : '12층' },
          { 계약일: '2022.11.17', 거래 : '월세', 가격 : '2억 3000', 층 : '12층' },
          { 계약일: '2022.11.17', 거래 : '월세', 가격 : '2억 3000', 층 : '12층' },
        ]
    };
  },
  components: {
    LineChart,
  },
  updated : {
    
  },
  watch: {
    structDetailPos: function (val) {
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
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
