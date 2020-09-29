<template>
  <v-card>
    <v-card-text>
      <div>MBTI 유형</div>
      <p class="text-sm-center text-md-center text-sm-h3 text-md-h2">{{mtype}}</p>
    </v-card-text>
  </v-card>
</template>

<script>
import UserApi from "../../api/UserApi.js";

export default {
  data() {
    return {
      mtype: "",
    };
  },
  // props: {
  //   mtype: {
  //     type: String,
  //   },
  // },
  created() {
    UserApi.requestMbtiInfo(
      this.$route.params.summonername,
      (res) => {
        // console.log(res.data);
        this.mtype = res.data.mbti;
        // console.log(this.mtype);
      },
      (error) => {
        this.mtype = "정보가 없습니다.";
      }
    );
  },
};
</script>

<style lang="">
</style>