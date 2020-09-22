<template>
  <nav>
    <v-toolbar flat app class="maincolor">

      <v-toolbar-title class="grey--text">
        <!-- <span class="font-weight-light">lol</span>
        <span>BTI</span>-->

        <v-img
          class="mt-2 mb-2"
          src="../assets/images/lolbti_logo_green.png"
          height="55"
          width="100"
        />
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-form class="mr-4 mt-5">
        <v-text-field
          rounded
          background-color="btncolor"
          color="btncolor"
          outline
          label="닉네임 검색"
          append-icon="search"
        ></v-text-field>
      </v-form>

      <v-btn flat class="btncolor mr-4">
        <span>로그인</span>
        <v-icon right>login</v-icon>
      </v-btn>
      <!-- <v-btn flat color="grey">
        <span>로그아웃</span>
        <v-icon right>exit_to_app</v-icon>
      </v-btn>-->
      <v-btn flat class="btncolor">
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

    };
  },
};
</script>

<style lang=""></style>
