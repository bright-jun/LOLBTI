<template>
  <div>
    <Doughnut v-if="loaded" :chartData="chartData" :options="options" />
  </div>
</template>

<script>
import Doughnut from "./Doughnut.js";

export default {
  components: {
    Doughnut,
  },
  data() {
    return {
      loaded: false,
      chartData: null,
    };
  },
  async mounted() {
    this.chartData = {
      labels: ["탑", "미들", "정글", "바텀", "써폿"],
      datasets: [
        {
          backgroundColor: [
            "#EF6C00",
            "#1E88E5",
            "#B2FF59",
            "#00838F",
            "#D81B60",
          ],
          data: [20, 20, 20, 20, 20],
        },
      ],
    };
    this.loaded = true;
  },
};
</script>

<style lang=""></style>
