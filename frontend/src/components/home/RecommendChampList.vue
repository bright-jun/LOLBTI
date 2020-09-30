<template>
  <v-card class="mx-auto ml-2 mr-2">
    <v-list three-line>
      <template v-for="(item, index) in items">
        <v-subheader
          v-if="item.header"
          :key="item.header"
          v-text="item.header"
        ></v-subheader>

        <v-divider
          v-else-if="item.divider"
          :key="index"
          :inset="item.inset"
        ></v-divider>

        <v-list-item v-else :key="item.title">
          <!-- 위에 빠짐 @click  -->
          <v-list-item-avatar>
            <v-img :src="item.avatar"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>
              {{ item.champion }} {{ item.point }}
            </v-list-item-title>
            <!-- <v-list-item-subtitle v-html="item.lane"></v-list-item-subtitle> -->
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-list>
  </v-card>
</template>

<script>
import { HorizontalBar } from 'vue-chartjs'

export default {
  extends: HorizontalBar,
  data() {
    return {
      datacollection: {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        datasets: [
          {
            label: 'Data One',
            barThickness: 6,
            backgroundColor: '#f87979',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            pointBorderColor: '#249EBF',
            data: [40, 20, 30, 50, 90, 10, 20, 40, 50, 70, 90, 100]
          }
        ]
      },
      options: {

        // scales: {
        //   yAxes: [{
        //     ticks: {
        //       beginAtZero: true
        //     },
        //     gridLines: {
        //       display: true
        //     }
        //   }],
        //   xAxes: [ {
        //     gridLines: {
        //       display: false
        //     }
        //   }]
        // },
        // legend: {
        //     display: true
        //   },
        // responsive: true,
        // maintainAspectRatio: false
      }
    }
  },
  mounted() {
    this.renderChart(this.datacollection, this.options)
  }
}
</script>