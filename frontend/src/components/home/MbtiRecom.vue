<template>
  <div>
    <v-row>
      <v-col md="4">
        <v-card v-if="mtype.length == 4" height="500px">
          <v-card-title class="justify-center">내 MBTI 성향</v-card-title>
          <v-img :src="require(`../../assets/images/${mtype.toLowerCase()}.png`)" class="mx-auto" width="150px" height="150px"></v-img>
          <v-card-text>
            <v-row align="center" class="mx-0">
              <v-chip-group v-for="nick in myMbti.nick" :key="nick">
                <v-chip small>#{{nick}}</v-chip>
              </v-chip-group>
            </v-row>
            <div class="my-4">
              {{myMbti.detail}}
            </div>
          </v-card-text>
        </v-card>
        <v-card v-else>
          {{mtype}}
        </v-card>
      </v-col>
      <v-col md="4">
        <v-card v-if="mtype.length == 4" height="500px">
          <v-card-title class="justify-center">최고의 궁합</v-card-title>
          <v-img :src="require(`../../assets/images/${good.toLowerCase()}.png`)" class="mx-auto" width="150px" height="150px"></v-img>
          <v-card-text>
            <v-row align="center" class="mx-0">
              <v-chip-group v-for="nick in goodMbti.nick" :key="nick">
                <v-chip small>#{{nick}}</v-chip>
              </v-chip-group>
            </v-row>
            <div class="my-4">
              {{goodMbti.detail}}
            </div>
          </v-card-text>
        </v-card>
        <v-card v-else>
          {{mtype}}
        </v-card>
      </v-col>
      <v-col md="4">
        <v-card v-if="mtype.length == 4" height="500px">
          <v-card-title class="justify-center">최악의 궁합</v-card-title>
          <v-img :src="require(`../../assets/images/${bad.toLowerCase()}.png`)" class="mx-auto" width="150px" height="150px"></v-img>
          <v-card-text>
            <v-row align="center" class="mx-0">
              <v-chip-group v-for="nick in badMbti.nick" :key="nick">
                <v-chip small>#{{nick}}</v-chip>
              </v-chip-group>
            </v-row>
            <div class="my-4">
              {{badMbti.detail}}
            </div>
          </v-card-text>
        </v-card>
        <v-card v-else>
          {{mtype}}
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col md="12">
        <v-card style="display: flex">
          <v-row>
            <v-col md="1"> </v-col>
            <v-col md="5">
              <recommend-champ-list ref="list1" />
            </v-col>
            <v-col md="5">
              <recommend-champ-list2 ref="list2" />
            </v-col>
            <v-col md="1"> </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import RecommendChampList from "./RecommendChampList.vue";
import RecommendChampList2 from "./RecommendChampList2.vue";
import UserApi from "../../api/UserApi.js";

export default {
  components: {
    RecommendChampList,
    RecommendChampList2,
  },
  data() {
    return {
      mtype: "",
      myMbti: {"nick":[],"detail":""},
      good: "",
      goodMbti: {"nick":[],"detail":""},
      bad: "",
      badMbti: {"nick":[],"detail":""}
    };
  },
  created() {
    UserApi.requestMbtiInfo(
      this.$route.params.summonername,
      (res) => {
        this.mtype = res.data.mbti;
        this.myMbti.nick = this.$store.state.mbti[this.mtype].nick
        this.myMbti.detail = this.$store.state.mbti[this.mtype].detail
        this.good = this.$store.state.mbti[this.mtype].good
        this.goodMbti.nick = this.$store.state.mbti[this.good].nick
        this.goodMbti.detail = this.$store.state.mbti[this.good].detail
        this.bad = this.$store.state.mbti[this.mtype].bad
        this.badMbti.nick = this.$store.state.mbti[this.bad].nick
        this.badMbti.detail = this.$store.state.mbti[this.bad].detail
      },
      (error) => {
        this.mtype = "정보가 없습니다.";
      }
    );
  },
};
</script>

<style>
</style>