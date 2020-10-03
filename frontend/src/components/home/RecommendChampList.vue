<script>
import { HorizontalBar } from "vue-chartjs";
export default {
  extends: HorizontalBar,
  methods: {
    gogo(items) {
      var datacollection = {
        labels: [
          items[1]["worstChampion"],
          items[3]["worstChampion"],
          items[5]["worstChampion"],
          items[7]["worstChampion"],
          items[9]["worstChampion"],
        ],
        datasets: [
          {
            label: "비추천 챔피언",
            barThickness: 30,
            backgroundColor: "rgba(255, 99, 132, 1)",
            borderColor: "rgba(255, 99, 132, 1)",
            borderWidth: 1,
            data: [
              Math.round(items[1]["worstPoint"] * 10) - 100,
              Math.round(items[3]["worstPoint"] * 10) - 100,
              Math.round(items[5]["worstPoint"] * 10) - 100,
              Math.round(items[7]["worstPoint"] * 10) - 100,
              Math.round(items[9]["worstPoint"] * 10) - 100,
            ],
          },
        ],
      };
      var options = {
        scales: {
          yAxes: [
            {
              gridLines: {
                display: true,
              },
              ticks: {
                fontStyle: "bold",
                fontSize: 18,
              },
            },
          ],
          xAxes: [
            {
              display: false,
              gridLines: {
                display: false,
                offsetGridLines: false,
              },
              ticks: {
                min: Math.ceil(10 - items[1]["worstPoint"]) * -10,
                max: Math.floor(10 - items[9]["worstPoint"]) * -10,
              },
            },
          ],
          legend: {
            display: true,
          },
          responsive: true,
          maintainAspectRatio: false,
        },
      };
      this.renderChart(datacollection, options);
    },
  },
};
</script>
