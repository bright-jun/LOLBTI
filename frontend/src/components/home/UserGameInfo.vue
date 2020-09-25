<template>
  <v-card>
    <v-card-text>
      <v-row>
        <v-col md="6">
          <v-img class="ma-auto" :src="require(`@/assets/img/${imgURL}`)" height="150" width="120"></v-img>
        </v-col>
        <v-col md="6">
          <p class="mb-0">솔로랭크</p>
          <p class="mb-0 text-md-h6">{{tier}}</p>
          <p class="mb-0">{{score}}LP</p>
          <p class="mb-0">{{wins}}승 {{losses}}패</p>
          <p class="mb-0">{{$route.params.summonername}}</p>
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>
<script>
import UserApi from "../../api/UserApi.js";

export default {
  data() {
    return {
      tier: "",
      score: "",
      wins: "",
      losses: "",
      rate: "",
      imgURL: "",
    };
  },

  created() {
    UserApi.requestUserGameInfo(
      this.$route.params.summonername,
      (res) => {
        // console.log(res.data);
        this.tier = res.data.tier;
        this.score = res.data.leaguePoints;
        this.wins = res.data.wins;
        this.losses = res.data.losses;
        this.imgURL = "emblem_" + res.data.tier.toLowerCase() + ".png";
        // console.log(this.imgURL);
      },
      (error) => {}
    );
  },
};
</script>

<style>
</style>