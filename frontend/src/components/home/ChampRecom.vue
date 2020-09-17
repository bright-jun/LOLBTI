<template>
  <div>
    <v-row>
      <v-col md="4">
        <v-card>
          <v-card-text>
            승패 기록
          </v-card-text>
          <win-lose-chart />
        </v-card>
      </v-col>
      <v-col md="4">
        <fre-champ-list />
      </v-col>
      <v-col md="4">
        <v-card>
          <v-card-text>
            선호 라인
          </v-card-text>
          <fre-line-chart />
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <recommend-champ-list />
    </v-row>
  </div>
</template>

<script>
import RecommendChampList from "./RecommendChampList.vue";
import FreChampList from "./FreChampList.vue";
import WinLoseChart from "./WinLoseChart.vue";
import FreLineChart from "./FreLineChart.vue";

export default {
  components: {
    RecommendChampList,
    FreChampList,
    WinLoseChart,
    FreLineChart,
  },
};
</script>

<style></style>
