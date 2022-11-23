<template>
  <b-card no-body id="detail_card">
    <b-row no-gutters>
      <b-col md="12">
        <div id="roadview" style="width: 100%; height: 300px"></div>
      </b-col>
      <b-col md="12">
        <b-card-header>
          <span
            ><b-badge pill variant="info"> {{ type }} </b-badge>
            {{ bldg_name }}</span
          >
          <span class="float-right"
            ><b-badge pill variant="info"> 건축년도 </b-badge>
            {{ buildYear }}</span
          >
        </b-card-header>
        <b-card-body>
          <b-card-text class="text-center">
            {{ bldg_area }} | {{ tot_area }} m²
          </b-card-text>
          <b-card-text>
            <stats-card
              title="매매"
              type="gradient-red"
              sub-title="18억 8,000만"
              class="mb-1"
            >
              <template slot="footer">
                <span class="text-success mr-2">최대가 : 18억 8,000만</span>
              </template>
            </stats-card>
            <stats-card
              title="전세"
              type="gradient-red"
              sub-title="18억 8,000만"
              class="mb-1"
            >
              <template slot="footer">
                <span class="text-success mr-2">최대가 : 18억 8,000만</span>
              </template>
            </stats-card>
            <stats-card
              title="월세"
              type="gradient-red"
              sub-title="18억 8,000만"
              class="mb-1"
            >
              <template slot="footer">
                <span class="text-success mr-2">최대가 : 18억 8,000만</span>
              </template>
            </stats-card>
          </b-card-text>
          <b-card-text class="border p-3">
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
    };
  },
  components: {
    LineChart,
  },
  mounted() {},
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
  max-height: 730px;
  overflow: auto;
}
</style>
