<template>
  <div>
    <RadarChart v-if="loaded" :chartData="chartData" :options="options" />
  </div>
</template>

<script>
import RadarChart from "../home/RadarChart.js";
import UserApi from "../../api/UserApi.js";

export default {
  components: {
    RadarChart,
  },
  props: {},
  data() {
    return {
      loaded: false,
      chartData: null,
      options: null,
    };
  },
  async mounted() {
    UserApi.requestFreqLane(
      this.$route.params.summonername,
      (res) => {
        // console.log(res.data);
        this.chartData = {
          labels: res.data.lane,
          datasets: [],
        };
        this.chartData.datasets.push({
          label: this.$route.params.summonername,
          backgroundColor: "#0DA970",
          data: res.data.laneFreq,
        });
        this.loaded = true;
      },
      (error) => {}
    );
  },
  methods: {
    color() {
      var a = Math.floor(Math.random() * 256);
      var b = Math.floor(Math.random() * 256);
      var c = Math.floor(Math.random() * 256);

      var str = `rgba(${a},${b},${c},0.5)`;
      return str;
    },
  },
};
</script>

<style>
.small {
  max-width: 600px;
  margin: 150px auto;
}
</style>
