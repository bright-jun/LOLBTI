<template>
  <div>
    <v-row>
      <v-col md="4">
        <v-card>
          <v-card-text> 승패 기록 </v-card-text>
          <win-lose-chart />
        </v-card>
      </v-col>
      <v-col md="4">
        <fre-champ-list />
      </v-col>
      <v-col md="4">
        <v-card>
          <v-card-text> 선호 라인 </v-card-text>
          <fre-line-chart />
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col md="12">
        <recommend-champ-list :items="items" />
      </v-col>
    </v-row>
  </div>
</template>

<script>
import RecommendChampList from "./RecommendChampList.vue";
import FreChampList from "./FreChampList.vue";
import WinLoseChart from "./WinLoseChart.vue";
import FreLineChart from "./FreLineChart.vue";
import UserApi from "../../api/UserApi.js";

export default {
  components: {
    RecommendChampList,
    FreChampList,
    WinLoseChart,
    FreLineChart,
  },
  data() {
    return {
      type: 0,
      items: [{ header: "숙련도 기반 챔프 추천" }],
    };
  },
  created() {
    UserApi.requestRecommendChampList(
      this.$route.params.summonername,
      this.type,
      (res) => {
        console.log(res.data);
        var self = this;
        for (var index = 0; index < res.data.bestChampList.length; index++) {
          console.log(res.data.bestPointList[index]);
          var bestPoint = Math.round(res.data.bestPointList[index] * 100);
          var worstPoint = Math.round(res.data.worstPointList[index] * 100);
          this.items.push({
            bestChampion: self.$store.getters.getChampNameByNo(
              String(res.data.bestChampList[index])
            ),
            bestAvatar:
              "http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/" +
              self.$store.getters.getChampIdByNo(
                String(res.data.bestChampList[index])
              ) +
              ".png",
            bestPoint: bestPoint,
            worstChampion: self.$store.getters.getChampNameByNo(
              String(res.data.worstChampList[index])
            ),
            worstAvatar:
              "http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/" +
              self.$store.getters.getChampIdByNo(
                String(res.data.worstChampList[index])
              ) +
              ".png",
            worstPoint: worstPoint,
          });
          if (index != res.length - 1) {
            this.items.push({
              divider: true,
              inset: true,
            });
          }
        }
        console.log(this.items);
      },
      (error) => {}
    );
  },
};
</script>

<style></style>
