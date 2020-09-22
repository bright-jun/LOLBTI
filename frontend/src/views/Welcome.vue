<template>
  <v-main>
    <nav-bar :viewType="navbarType" />
    <v-container>
      <v-img class="ma-auto" src="../assets/images/lolbti_logo_2.png" height="180" width="310" />
      <v-row class="pt-5">
        <v-col md="3"></v-col>
        <v-col xs="11" md="5">
          <v-form>
            <v-text-field outline label="닉네임 검색" append-icon="search" v-model="summonerName"></v-text-field>
          </v-form>
        </v-col>
        <v-col xs="1" md="1" class="pt-5">
          <v-btn class="btncolor" @click="submit">검색</v-btn>
        </v-col>
        <v-col md="3"></v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import NavBar from "../components/NavBar.vue";
import { EventBus } from "../main.js";

export default {
  data() {
    return {
      summonerName: "",
      searchType: 1,
      navbarType: false,
    };
  },
  components: {
    NavBar,
  },
  methods: {
    submit() {
      this.$store.state.summoner = {
        name: this.summonerName,
        searchType: this.searchType,
      };
      this.$router.push("/home");
    },
  },
};
</script>

<style lang=""></style>
