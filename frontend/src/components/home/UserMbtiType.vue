<template>
  <v-card>
    <v-container>
      <v-row class="mb-3">
        <div class="ml-3">MBTI 유형</div>
      </v-row>
      <v-row>
        <v-card-text>
          <p class="text-center text-h2">
            {{ mtype }}
          </p>
        </v-card-text>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
import UserApi from "../../api/UserApi.js";

export default {
  components: {},
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

<style lang=""></style>
