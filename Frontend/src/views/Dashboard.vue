<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
    </base-header>

    <!--Charts-->
    <b-container fluid class="mt--7">
      <b-row>
        <b-col xl="12" class="mb-5 mb-xl-0">
          <card header-classes="bg-transparent">
            <b-row align-v="center" slot="header">
              <b-col>
                <h6 class="text-uppercase text-muted ls-1 mb-1"></h6>
                <h5 class="h3 mb-0">시도별 건물수</h5>
              </b-col>
            </b-row>

            <bar-chart :height="350" ref="barChart" :chart-data="chartData">
            </bar-chart>
          </card>
        </b-col>
      </b-row>
      <!-- End charts-->

      <!--Tables-->
      <b-row class="mt-5">
        <b-col xl="12">공지사항</b-col>
        <b-col xl="12">
          <b-table :items="boards"></b-table>
        </b-col>
      </b-row>
      <!--End tables-->
    </b-container>
  </div>
</template>
<script>
import { countsido } from "@/api/areaApi";
import { mapGetters } from "vuex";

// Charts
import * as chartConfigs from "@/components/Charts/config";
import LineChart from "@/components/Charts/LineChart";
import BarChart from "@/components/Charts/BarChart";

// Components
import BaseProgress from "@/components/BaseProgress";
import StatsCard from "@/components/Cards/StatsCard";

// Tables
import SocialTrafficTable from "./Dashboard/SocialTrafficTable";
import PageVisitsTable from "./Dashboard/PageVisitsTable";

export default {
  components: {
    LineChart,
    BarChart,
    BaseProgress,
    StatsCard,
    PageVisitsTable,
    SocialTrafficTable,
  },
  data() {
    return {
      labels: [],
      datas: [],
      redBarChart: {
        extraOptions: chartConfigs.blueChartOptions,
      },
    };
  },
  computed: {
    chartData() {

      return {
        labels: this.labels,
        datasets: [
          {
            label: "Sales",
            data: this.datas,
          },
        ],
      };
    },
    ...mapGetters("board",["boards"])
  },
  methods: {},
  mounted() {

    countsido(({ data }) => {
      data.forEach((v) => {
        this.labels.push(v.sidoName);
        this.datas.push(v.sidoCount);
      });
    });
  },
};
</script>
<style>
.el-table .cell {
  padding-left: 0px;
  padding-right: 0px;
}
</style>
