<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="mt-2"
          color="btncolor black--text"
          absolute
          right
          dark
          v-bind="attrs"
          v-on="on"
          style="margin-right: 100px"
        >
          내 정보
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">내 정보</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                  label="소환사 이름"
                  v-model="summonerName"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-autocomplete
                  :items="[
                    'ENFJ',
                    'ENFP',
                    'ENTJ',
                    'ENTP',
                    'ESFJ',
                    'ESFP',
                    'ESTJ',
                    'ESTP',
                    'INFJ',
                    'INFP',
                    'INTJ',
                    'INTP',
                    'ISFJ',
                    'ISFP',
                    'ISTJ',
                    'ISTP',
                  ]"
                  v-model="mbti"
                  label="MBTI 유형"
                ></v-autocomplete>
              </v-col>
              <v-col>
                <a
                  href="https://www.16personalities.com/ko/%EB%AC%B4%EB%A3%8C-%EC%84%B1%EA%B2%A9-%EC%9C%A0%ED%98%95-%EA%B2%80%EC%82%AC"
                  target="_blank"
                  >무료 MBTI 검사</a
                >
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">
            닫기
          </v-btn>
          <v-btn color="blue darken-1" text @click="userInfoUpdate()">
            저장
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import UserApi from "../../api/UserApi.js";

export default {
  data: () => ({
    dialog: false,
    summonerName: "",
    mbti: "",
  }),

  created() {
    UserApi.requestSummonerNameAndMbtiTypeById(
      this.$session.get("userinfo")["email"],
      (res) => {
        // console.log(res.data);
        this.summonerName = res.data.summonername;
        this.mbti = res.data.mbti;
      },
      (error) => {
        console.log("에러");
      }
    );
  },
  methods: {
    userInfoUpdate() {
      UserApi.updateUserInfo(
        this.summonerName,
        this.$session.get("userinfo")["email"],
        this.mbti,
        (res) => {
          // console.log(res.data);
          alert("등록성공");
          this.dialog = false;
        },
        (error) => {
          console.log("에러");
          alert("등록실패");
          this.dialog = false;
        }
      );
    },
  },
};
</script>

<style lang="">
</style>