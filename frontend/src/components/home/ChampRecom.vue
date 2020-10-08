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
          <v-row>
            <v-col md="1">
              <template v-for="(ava, index) in worstimgSrcArr">
                <v-img
                  :src="ava.worstAvatar"
                  :key="index"
                  class="mt-6 ml-2"
                ></v-img>
                <!-- <br :key="index" /> -->
              </template>
            </v-col>
            <v-col md="10" v-if="isEmpty">
              <p class="mt-3 text-center font-weight-black text-h3">
                추천 받기 버튼을 누르세요.
              </p>
            </v-col>
            <v-col md="5">
              <recommend-champ-list ref="list1" />
            </v-col>
            <v-col md="5">
              <recommend-champ-list2 ref="list2" />
            </v-col>
            <v-col md="1">
              <template v-for="(ava, index) in bestimgSrcArr">
                <v-img
                  :src="ava.bestAvatar"
                  :key="index"
                  class="mt-6 mr-2"
                ></v-img>
                <!-- <br :key="index" /> -->
              </template>
            </v-col>
          </v-row>
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
      isEmpty: false,
      bestimgSrcArr: [],
      worstimgSrcArr: [],
      // items: [{ header: "숙련도 기반 챔프 추천" }],
      items: {
        bestChampion: [],
        bestPoint: [],
        worstChampion: [],
        worstPoint: [],
      },
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
          var worstPoint = (res.data.worstPointList[index] * 100).toFixed(2);
          var bestAvatar =
            "http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/" +
            self.$store.getters.getChampIdByNo(
              String(res.data.bestChampList[index])
            ) +
            ".png";
          var worstAvatar =
            "http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/" +
            self.$store.getters.getChampIdByNo(
              String(res.data.worstChampList[index])
            ) +
            ".png";
          this.bestimgSrcArr.push({ bestAvatar });
          this.worstimgSrcArr.push({ worstAvatar });
          this.items.bestChampion.push(
            self.$store.getters.getChampNameByNo(
              String(res.data.bestChampList[index])
            )
          );
          this.items.bestPoint.push(Math.round(bestPoint));
          this.items.worstChampion.push(
            self.$store.getters.getChampNameByNo(
              String(res.data.worstChampList[index])
            )
          );
          this.items.worstPoint.push(Math.round(worstPoint * 10 - 100));
          //Math.round(worstPoint * 10 - 100)
          // this.items.push({
          //   bestChampion: self.$store.getters.getChampNameByNo(
          //     String(res.data.bestChampList[index])
          //   ),
          //   bestAvatar: bestAvatar,
          //   bestPoint: bestPoint,
          //   worstChampion: self.$store.getters.getChampNameByNo(
          //     String(res.data.worstChampList[index])
          //   ),
          //   worstAvatar: worstAvatar,
          //   worstPoint: worstPoint,
          // });
          // if (index != res.length - 1) {
          //   this.items.push({
          //     divider: true,
          //     inset: true,
          //   });
          // }
        }

        // console.log(this.worstimgSrcArr);
        // console.log(this.bestimgSrcArr);
        // console.log(this.items);
        this.gogo(this.items);
      },
      (error) => {
        this.isEmpty = true;
      }
    );
  },
};
</script>

<style></style>
