<script>
import { HorizontalBar } from 'vue-chartjs'

export default {
  extends: HorizontalBar,
  data() {
    return {
      datacollection: {
        labels: ['January', 'February', 'March', 'April', 'May'],
        datasets: [
          {
            label: 'Data One',
            barThickness: 6,
            backgroundColor: '#f87979',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            // borderSkipped: right,
            pointBorderColor: '#249EBF',
            data: [-40, 20, 30, 50, 90]
          }
        ]
      },
      options: {

        scales: {
        //   yAxes: [{
        //     ticks: {
        //       beginAtZero: true
        //     },
        //     gridLines: {
        //       display: true
        //     }
        //   }],
          xAxes: [{
            gridLines: {
                offsetGridLines: false
            }
          }]
        // legend: {
        //     display: true
        //   },
        // responsive: true,
        // maintainAspectRatio: false
        }
      }
    }
  },
  mounted() {
    this.renderChart(this.datacollection, this.options)
  }
}
</script>