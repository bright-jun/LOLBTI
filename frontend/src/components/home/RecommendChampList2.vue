<script>
import { HorizontalBar } from "vue-chartjs";
export default {
  extends: HorizontalBar,
  methods: {
    gogo(items) {
      var datacollection = {
        labels: [
          // items[1]["bestChampion"],
          // items[3]["bestChampion"],
          // items[5]["bestChampion"],
          // items[7]["bestChampion"],
          // items[9]["bestChampion"],
        ],
        datasets: [
          {
            label: "추천 챔피언",
            barThickness: 30,
            backgroundColor: "rgba(54, 162, 235, 1)",
            borderColor: "rgba(54, 162, 235, 1)",
            borderWidth: 1,
            data: [
              // Math.round(items[1]["bestPoint"] * 10),
              // Math.round(items[3]["bestPoint"] * 10),
              // Math.round(items[5]["bestPoint"] * 10),
              // Math.round(items[7]["bestPoint"] * 10),
              // Math.round(items[9]["bestPoint"] * 10),
            ],
          },
        ],
      };
      datacollection.labels = items.bestChampion;
      datacollection.datasets[0].data = items.bestPoint;
      // console.log("찍히나");
      // console.log(datacollection.labels);
      // console.log(datacollection.datasets[0].data);
      
      var options = {
        scales: {
          yAxes: [
            {
              position: "right",
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
                min: Math.floor((items.bestPoint[items.bestPoint.length-1]-1) / 10) * 10,
                max: Math.ceil((items.bestPoint[0]+1) / 10) * 10,
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
