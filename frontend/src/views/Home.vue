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
              <UserGameInfo />
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

export default {
  data() {
    return {
      navbarType: true,
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
  created() {},
};
</script>

<style></style>
