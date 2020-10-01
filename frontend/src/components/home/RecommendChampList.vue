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
            <v-img :src="item.bestAvatar"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>
              {{ item.bestChampion }} {{ item.bestPoint }}
            </v-list-item-title>
            <!-- <v-list-item-subtitle v-html="item.lane"></v-list-item-subtitle> -->
          </v-list-item-content>
          <v-list-item-avatar>
            <v-img :src="item.worstAvatar"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>
              {{ item.worstChampion }} {{ item.worstPoint }}
            </v-list-item-title>
            <!-- <v-list-item-subtitle v-html="item.lane"></v-list-item-subtitle> -->
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-list>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    // items: [
    // { header: "추천 챔프리스트" },
    // {
    //   avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
    //   title: "Brunch this weekend?",
    //   subtitle:
    //     "<span class='text--primary'>Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?",
    // },
    // { divider: true, inset: true },
    // ],
  }),
  props: ["items"],
};


// export default {
//   extends: HorizontalBar,
//   data() {
//     return {
//       datacollection: {
//         labels: ['January', 'February', 'March', 'April', 'May'],
//         datasets: [
//           {
//             label: 'Data One',
//             barThickness: 6,
//             backgroundColor: '#f87979',
//             pointBackgroundColor: 'white',
//             borderWidth: 1,
//             // borderSkipped: right,
//             pointBorderColor: '#249EBF',
//             data: [-40, 20, 30, 50, 90]
//           }
//         ]
//       },
//       options: {

//         scales: {
//         //   yAxes: [{
//         //     ticks: {
//         //       beginAtZero: true
//         //     },
//         //     gridLines: {
//         //       display: true
//         //     }
//         //   }],
//           xAxes: [{
//             gridLines: {
//                 offsetGridLines: false
//             }
//           }]
//         // legend: {
//         //     display: true
//         //   },
//         // responsive: true,
//         // maintainAspectRatio: false
//         }
//       }
//     }
//   },
//   mounted() {
//     this.renderChart(this.datacollection, this.options)
//   }
// }
</script>
