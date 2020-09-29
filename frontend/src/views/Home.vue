<template>
  <div>
    <nav-bar :viewType="navbarType" />
    <v-main class="mx-4 my-4">
      <v-container>
        <v-row>
          <v-card class="mx-auto">
            <Banner />
          </v-card>
        </v-row>
        <v-row>
          <v-col xs="12" sm="12" md="4">
            <v-col>
              <!-- <v-card class="pa-2" outlined tile>mbti 유형</v-card> -->
              <UserMbtiType />
            </v-col>
            <v-col>
              <UserGameInfo :gameInfo="gameInfo" :imgURL="imgURL" />
            </v-col>
            <v-col>
              <RecentChampList :items="items" />
            </v-col>
          </v-col>
          <v-col xs="12" sm="12" md="8" class="pt-6">
            <!-- <v-card class="pa-2" outlined tile>
            </v-card>-->
            <HomeMenu />
            <!-- <v-card class="pa-2" outlined tile>승률 원형 그래프, 챔프 기록, 라인 기록</v-card>
            <v-card class="pa-2" outlined tile>챔프 추천 목록</v-card>-->
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </div>
</template>

<script>
import HomeMenu from "../components/home/HomeMenu.vue";
import UserMbtiType from "../components/home/UserMbtiType.vue";
import UserGameInfo from "../components/home/UserGameInfo.vue";
import RecentChampList from "../components/home/RecentChampList.vue";
import Banner from "../components/Banner.vue";
import NavBar from "../components/NavBar.vue";
import axios from "axios";
import UserApi from "../api/UserApi.js";

export default {
  data() {
    return {
      navbarType: true,
      gameInfo: {},
      // recentGames: [],
      imgURL: "",
      items: [
        { header: "최근 챔프 기록" },
        // {
        //   avatar: "http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/Lux.png",
        //   title: "Brunch this weekend?",
        //   subtitle:
        //     "<span class='text--primary'>Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?",
        // },
        // { divider: true, inset: true },
      ],
    };
  },
  components: {
    HomeMenu,
    UserMbtiType,
    UserGameInfo,
    RecentChampList,
    Banner,
    NavBar,
  },
  created() {
    UserApi.requestUserGameInfo(
      this.$route.params.summonername,
      (res) => {
        // console.log(res.data);
        for (var index = 0; index < res.data.rankInfo.length; index++) {
          if (res.data.rankInfo[index].queueType == "RANKED_SOLO_5x5") {
            this.gameInfo = res.data.rankInfo[index];
            // this.recentGames = res.data.recentMatches.matches;
            this.imgURL =
              "emblem_" + res.data.rankInfo[index].tier.toLowerCase() + ".png";
          }
        }
        console.log(this.gameInfo);
        // console.log(this.recentGames);
        this.getRecentMatch(res.data.recentMatches.matches);
      },
      (error) => {}
    );
  },
  methods: {
    getRecentMatch(res) {
      var self = this;
      // console.log(res);
      for (var index = 0; index < res.length; index++) {
        // console.log(res[index].champion);
        this.items.push({
          champion: self.$store.getters.getChampNameByNo(
            String(res[index].champion)
          ),
          avatar:
            "http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/" +
            self.$store.getters.getChampIdByNo(String(res[index].champion)) +
            ".png",
          role: res[index].role,
          lane: res[index].lane,
        });
        if (index != res.length - 1) {
          this.items.push({
            divider: true,
            inset: true,
          });
        }
      }
      // console.log(this.items);
    },
  },
};
</script>

<style></style>
