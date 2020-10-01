<template>
  <div>
    <v-tabs
      v-model="tab"
      background-color="maincolor"
      class="elevation-2"
      dark
      color="btncolor accent-4"
      :centered="centered"
      :grow="grow"
      :vertical="vertical"
      :right="right"
      :prev-icon="prevIcon ? 'mdi-arrow-left-bold-box-outline' : undefined"
      :next-icon="nextIcon ? 'mdi-arrow-right-bold-box-outline' : undefined"
      :icons-and-text="icons"
    >
      <v-tabs-slider></v-tabs-slider>

      <v-tab v-for="i in tabs" :key="i" :href="`#tab-${i}`">
        {{ i }}
        <v-icon v-if="icons">mdi-phone</v-icon>
      </v-tab>
      <v-btn
        class="btncolor my-2 black--text"
        absolute
        right
        @click="updateGameInfo()"
        >갱신하기</v-btn
      >
      <v-tab-item v-for="i in tabs" :key="i" :value="'tab-' + i">
        <v-card flat tile>
          <v-card-text>
            <ChampRecom v-if="i === '챔프추천'" />
            <MbtiRecom v-if="i === 'MBTI추천'" />
            <DuoRecom v-if="i === '아이템추천'" />
          </v-card-text>
        </v-card>
      </v-tab-item>
    </v-tabs>
  </div>
</template>

<script>
import DuoRecom from "../../components/home/DuoRecom.vue";
import ChampRecom from "../../components/home/ChampRecom.vue";
import MbtiRecom from "../../components/home/MbtiRecom";
import UserApi from "../../api/UserApi.js";

export default {
  components: {
    DuoRecom,
    ChampRecom,
    MbtiRecom,
  },
  data() {
    return {
      tab: null,
      text:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
      icons: false,
      centered: false,
      grow: false,
      vertical: false,
      prevIcon: false,
      nextIcon: false,
      right: false,
      tabs: ["챔프추천", "MBTI추천", "아이템추천"],
    };
  },

  created() {},
  methods: {
    updateGameInfo() {
      console.log("업데이트 버튼 클릭됨");
      UserApi.updateUserGameInfo(
        this.$route.params.summonername,
        (res) => {
          console.log(res.data);
          alert("갱신 성공");
        },
        (error) => {
          alert("갱신 실패");
        }
      );
    },
  },
};
</script>
