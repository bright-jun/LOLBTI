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
        this.chartData = {
          labels: ["탑", "정글", "미드", "바텀", "서폿"],
          datasets: [],
        };
        this.chartData.datasets.push({
          label: this.$route.params.summonername,
          backgroundColor: this.color(),
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
