import { Doughnut } from "vue-chartjs";

export default {
  components: {
    Doughnut,
  },
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
