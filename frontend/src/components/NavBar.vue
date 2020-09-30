<template>
  <nav>
    <v-toolbar flat app class="maincolor">

      <v-toolbar-title class="grey--text">
        <!-- <span class="font-weight-light">lol</span>
        <span>BTI</span>-->

        <v-img class="mt-2 mb-2" src="../assets/images/lolbti_logo_2.png" height="55" width="100" />
      </v-toolbar-title>

      <v-spacer></v-spacer>
      <v-form class="mr-4 mt-5" onsubmit="return false;">
        <v-text-field
          v-if="viewType"
          rounded
          background-color="btncolor"
          color="btncolor"
          outline
          label="닉네임 검색"
          append-icon="search"
          v-model="summonerName"
          @keydown.enter="searchSummoner"
        ></v-text-field>
      </v-form>

      <v-btn flat class="btncolor mr-4" @click="$router.push('/login').catch(() => {})">
        <span>로그인</span>
        <v-icon right>login</v-icon>
      </v-btn>
      <!-- <v-btn flat color="grey">
        <span>로그아웃</span>
        <v-icon right>exit_to_app</v-icon>
      </v-btn>-->
      <v-btn flat class="btncolor" @click="$router.push('/join').catch(() => {})">
        <span>회원가입</span>
        <!-- <v-icon right></v-icon> -->
      </v-btn>
    </v-toolbar>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      summonerName: "",
      searchType: 1,
      mtype: "",
    };
  },
  props: ["viewType"],
  methods: {
    searchSummoner() {
      this.$store.state.summoner = {
        name: this.summonerName,
        searchType: this.searchType,
        mtype: this.mtype,
      };

      this.$router.push("/home/" + this.$store.state.summoner.name);
    },
  },
};
</script>

<style lang=""></style>
