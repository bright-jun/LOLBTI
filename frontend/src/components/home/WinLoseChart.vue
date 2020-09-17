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
      labels: ["승", "패"],
      datasets: [
        {
          backgroundColor: ["#41B883", "#E46651"],
          data: [40, 60],
        },
      ],
    };
    this.loaded = true;
  },
};
</script>

<style lang=""></style>
