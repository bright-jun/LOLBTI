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
            backgroundColor: ["#41B883", "#E46651"],
            data: [n.wins, n.losses],
          },
        ],
      };
      this.options = {
        animation: {
          animateScale: true,
        },
      };
      this.loaded = true;
    });
  },
};
</script>

<style lang=""></style>
