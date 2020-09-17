import { Doughnut } from "vue-chartjs";

export default {
  extends: Doughnut,

  props: {
    chartData: {
      type: Object,
    },
    options: {
      type: Array,
    },
  },
  mounted() {
    this.renderChart(this.chartData, this.options);
  },
};
