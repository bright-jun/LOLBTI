<template>
  <div>
    <v-row>
      <v-col md="4">
        <v-card height="450">
          <v-card-text> 승패 기록 </v-card-text>
          <win-lose-chart />
        </v-card>
      </v-col>
      <v-col md="4">
        <fre-champ-list />
      </v-col>
      <v-col md="4">
        <v-card height="450">
          <v-card-text> 선호 라인 </v-card-text>
          <fre-line-chart />
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col md="12">
        <v-card style="display: flex">
          <div style="width: 10%">
            <!-- qq qqqqqqqqqqq -->
            <!-- <img :src="items[1].worstAvatar" width="80px"> -->
            <!-- <v-img :src="items[1].worstAvatar"></v-img>
            <v-img :src="items[3].worstAvatar"></v-img>
            <v-img :src="items[5].worstAvatar"></v-img>
            <v-img :src="items[7].worstAvatar"></v-img>
            <v-img :src="items[9].worstAvatar"></v-img> -->
          </div>
          <div style="width: 80%; display: flex">
            <recommend-champ-list ref="list1" />
            <recommend-champ-list2 ref="list2" />
          </div>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import RecommendChampList from "./RecommendChampList.vue";
import RecommendChampList2 from "./RecommendChampList2.vue";
import FreChampList from "./FreChampList.vue";
import WinLoseChart from "./WinLoseChart.vue";
import FreLineChart from "./FreLineChart.vue";
import UserApi from "../../api/UserApi.js";

export default {
  components: {
    RecommendChampList,
    RecommendChampList2,
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
  methods: {
    gogo(items) {
      this.$refs.list1.gogo(items);
      this.$refs.list2.gogo(items);
    },
  },

  created() {
    UserApi.requestRecommendChampList(
      this.$route.params.summonername,
      this.type,
      (res) => {
        var self = this;
        for (var index = 0; index < res.data.bestChampList.length; index++) {
          var bestPoint = (res.data.bestPointList[index] * 100).toFixed(2);
          var worstPoint = (res.data.worstPointList[index] * 1000).toFixed(2);
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
        this.gogo(this.items);
      },
      (error) => {}
    );
  },
};
</script>

<style></style>
