<template>
  <div>
    <Doughnut v-if="loaded" :chartData="chartData" :options="options" />
    <p class="mt-16 font-weight-black text-h3 text-center" v-if="!loaded">
      데이터가 없습니다.
    </p>
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
      options: null,
      loaded: false,
      chartData: null,
    };
  },

  async mounted() {
    this.$store.watch(this.$store.getters.getSummonerWinLose, (n) => {
      // console.log(n);
      this.chartData = {
        labels: ["승", "패"],
        datasets: [
          {
            backgroundColor: ["#0DA970", "#E46651"],
            data: [n.wins, n.losses],
          },
        ],
      };
      this.options = {
        animation: {
          animateScale: true,
        },
        maintainAspectRatio: false,
      };
      this.loaded = true;
    });
  },
};
</script>

