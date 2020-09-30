<template>
  <div>
    <RadarChart v-if="loaded" :chartData="chartData" :options="options" />
  </div>
</template>

<script>
import RadarChart from "../home/RadarChart.js";

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
    this.chartData = {
      labels: ["탑", "정글", "미드", "바텀", "서폿"],

      datasets: [
        {
          label: this.$route.params.summonername,
          backgroundColor: this.color(),
          data: [1, 2, 3, 4, 5],
        },
      ],
    };
    this.loaded = true;
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
