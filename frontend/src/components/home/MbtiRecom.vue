<template>
  <div>
    <v-row>
      <v-col md="6">
        <v-card>
          <v-card-text>mbti</v-card-text>
        </v-card>
      </v-col>
      <v-col md="6">
        <v-card>
          <v-card-text>mbti</v-card-text>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col md="12">
        <v-card style="display: flex">
          <v-row>
            <v-col md="1"> </v-col>
            <v-col md="5">
              <recommend-champ-list ref="list1" />
            </v-col>
            <v-col md="5">
              <recommend-champ-list2 ref="list2" />
            </v-col>
            <v-col md="1"> </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import RecommendChampList from "./RecommendChampList.vue";
import RecommendChampList2 from "./RecommendChampList2.vue";

export default {
  components: {
    RecommendChampList,
    RecommendChampList2,
  },
};
</script>

<style>
</style>