<template>
  <div>
    <v-row>
      <v-col md="4">
        <v-card>
          <v-card-text>mbti</v-card-text>
        </v-card>
      </v-col>
      <v-col md="4">
        <v-card>
          <v-card-text>mbti</v-card-text>
        </v-card>
      </v-col>
      <v-col md="4">
        <v-card>
          <v-card-text>mbti</v-card-text>
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

export default {
  components: {
    RecommendChampList,
  },
};
</script>

<style>
</style>