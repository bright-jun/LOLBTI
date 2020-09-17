<template>
  <div>
    <v-row>
      <v-col md="4">
        <v-card>
          <v-card-text>
            <win-lose-chart />
          </v-card-text>
        </v-card>
      </v-col>
      <v-col md="4">
        <fre-champ-list />
      </v-col>
      <v-col md="4">
        <v-card>
          <v-card-text>hi</v-card-text>
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

export default {
  components: {
    RecommendChampList,
    FreChampList,
    WinLoseChart,
  },
};
</script>

<style></style>
