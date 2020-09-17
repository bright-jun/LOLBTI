<template>
  <v-card>
    <v-card-text>
      <v-row>
        <v-col md="6">
          <v-img
            class="ma-auto"
            src="../../assets/images/ranked-emblems/emblem_bronze.png"
            height="100"
            width="100"
          ></v-img>
        </v-col>
        <v-col md="6">
          <p class="mb-0">솔로랭크</p>
          <p class="mb-0 text-md-h5">{{gameinfo.tier}}</p>
          <p class="mb-0">{{gameinfo.score}}LP</p>
          <p class="mb-0">{{gameinfo.win}}승 {{gameinfo.lose}}패 {{gameinfo.rate}}%</p>
          <p class="mb-0">{{gameinfo.summonerName}}</p>
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>
<script>
export default {
  data() {
    return {};
  },
  props: {
    gameinfo: {
      type: Object,
    },
  },
};
</script>

<style>
</style>