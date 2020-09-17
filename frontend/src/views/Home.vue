<template>
  <div>
    <v-container>
      <v-row>
        <v-card class="mx-auto">
          <!-- <v-img
            class="white--text align-end"
            height="200px"
            src="https://c.wallhere.com/photos/32/6e/League_of_Legends_Ashe_League_of_Legends_mountains_sky-1894075.jpg!d"
          >
            <v-card-title>lolBTI 어서와</v-card-title>
          </v-img>-->
          <Banner />
        </v-card>
      </v-row>
      <v-row>
        <v-col xs="12" sm="12" md="4">
          <v-col>
            <!-- <v-card class="pa-2" outlined tile>mbti 유형</v-card> -->
            <UserMbtiType :mtype="mbtitype" />
          </v-col>
          <v-col>
            <UserGameInfo :gameinfo="userGameInfo" />
          </v-col>
          <v-col>
            <RecentChampList />
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
  </div>
</template>

<script>
import HomeMenu from "../components/home/HomeMenu.vue";
import UserMbtiType from "../components/home/UserMbtiType.vue";
import UserGameInfo from "../components/home/UserGameInfo.vue";
import RecentChampList from "../components/home/RecentChampList.vue";
import Banner from "../components/Banner.vue";

export default {
  data() {
    return {
      mbtitype: "ENTJ",
      userGameInfo: {
        tier: "bronze",
        score: "600",
        rate: "50",
        win: "100",
        lose: "100",
        summonerName: "임시아이디",
      },
    };
  },
  components: {
    HomeMenu,
    UserMbtiType,
    UserGameInfo,
    RecentChampList,
    Banner,
  },
};
</script>

<style></style>
