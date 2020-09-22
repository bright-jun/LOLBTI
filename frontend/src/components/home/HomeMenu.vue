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

      <v-tab-item v-for="i in tabs" :key="i" :value="'tab-' + i">
        <v-card flat tile>
          <v-card-text>
            <ChampRecom v-if="i === '챔프추천'" />
            <MbtiRecom v-if="i === 'MBTI추천'" />
            <DuoRecom v-if="i === '듀오추천'" />
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
import axios from "axios";
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
      tabs: ["챔프추천", "MBTI추천", "듀오추천"],
    };
  },
  created() {
    axios
      .get(`http://localhost:8080/recommend/champion`, {
        params: {
          summonerName: this.$store.state.summoner.name,
          type: this.$store.state.summoner.searchType,
        },
      })
      .then((res) => {})
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>
