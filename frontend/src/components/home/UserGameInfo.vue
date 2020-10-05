<template>
  <v-card>
    <v-card-text>
      <v-row>
        <v-col md="5">
          <v-img
            v-if="imgURL != ''"
            class="ma-auto"
            :src="require(`@/assets/img/${imgURL}`)"
            height="150"
            width="120"
          ></v-img>
        </v-col>
        <v-col md="7">
          <h2 class="mb-0">솔로랭크</h2>
          <br />
          <p class="mb-0 text-md-h6">{{ gameInfo.tier }}</p>
          <p class="mb-0">{{ gameInfo.leaguePoints }}LP</p>
          <p class="mb-0">{{ gameInfo.wins }}승 {{ gameInfo.losses }}패</p>
          <br />
          <h3 class="mb-0">{{ gameInfo.summonerName }}</h3>
          <!-- <v-btn @click="check"></v-btn> -->
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>
<script>
import UserApi from "../../api/UserApi.js";

export default {
  data() {
    return {};
  },
  props: ["gameInfo", "imgURL"],

  created() {},
};
</script>

<style>
</style>