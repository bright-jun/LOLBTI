<template>
  <nav>
    <v-toolbar flat app class="maincolor">
      <v-toolbar-title class="grey--text">
        <v-img
          class="mt-2 mb-2"
          src="../assets/images/lolbti_logo_2.png"
          height="55"
          width="100"
          @click="$router.push('/').catch(() => {})"
          style="cursor: pointer"
        />
      </v-toolbar-title>

      <v-spacer></v-spacer>
      <v-form class="mr-4 mt-7" onsubmit="return false;">
        <v-text-field
          v-if="viewType"
          rounded
          solo
          dense
          label="닉네임 검색"
          append-icon="search"
          v-model="summonerName"
          @keydown.enter="searchSummoner"
        ></v-text-field>
      </v-form>

      <v-btn
        v-if="!isLog"
        flat
        class="btncolor mr-4"
        @click="$router.push('/login').catch(() => {})"
      >
        <span>로그인</span>
        <v-icon right>login</v-icon>
      </v-btn>

      <v-btn
        v-if="!isLog"
        flat
        class="btncolor"
        @click="$router.push('/join').catch(() => {})"
      >
        <span>회원가입</span>
      </v-btn>

      <v-btn v-if="isLog" flat class="btncolor" @click="logout()">
        <span>{{ userName }} 로그아웃</span>
      </v-btn>
    </v-toolbar>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      isLog: false,
      userName: "",
      summonerName: "",
      searchType: 1,
      mtype: "",
    };
  },
  props: ["viewType"],
  created() {
    this.isUserInfo();
  },
  methods: {
    searchSummoner() {
      this.$store.state.summoner = {
        name: this.summonerName,
        searchType: this.searchType,
        mtype: this.mtype,
      };

      this.$router
        .push("/home/" + this.$store.state.summoner.name)
        .catch(() => {});
    },
    logout() {
      this.$session.remove("userinfo");
      this.isUserInfo()
      this.$router.push("/").catch(() => {});
    },
    isUserInfo() {
      if (this.$session.get("userinfo")) {
        this.isLog = true;
        this.userName = this.$session.get("userinfo")["summonerName"];
      } else {
        this.isLog = false;
        this.userName = "";
      }
    }
  },
};
</script>

<style lang=""></style>
