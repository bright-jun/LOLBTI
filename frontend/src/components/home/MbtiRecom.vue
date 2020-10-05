<template>
  <div>
    <v-row>
      <v-col md="6">
        <v-card>
          <v-card-text>mbti</v-card-text>
        </v-card>
      </v-col>
      <v-col md="6">
        <v-card>
          <v-card-text>mbti</v-card-text>
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
import UserApi from "../../api/UserApi.js";

export default {
  components: {
    RecommendChampList,
    RecommendChampList2,
  },
  data() {
    return {
      mtype: "",
      bestimgSrcArr: [],
      worstimgSrcArr: [],
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
    UserApi.requestMbtiInfo(
      this.$route.params.summonername,
      (res) => {
        // console.log(res.data);
        this.mtype = res.data.mbti;
        // console.log(this.mtype);
        UserApi.requestRecommendChampListByMbti(
          res.data.mbti,
          (res) => {
            var self = this;
            for (
              var index = 0;
              index < res.data.bestChampList.length;
              index++
            ) {
              var bestPoint = (res.data.bestPointList[index] * 100).toFixed(2);
              var worstPoint = (res.data.worstPointList[index] * 100).toFixed(
                2
              );
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
            }
            // console.log(this.worstimgSrcArr);
            // console.log(this.bestimgSrcArr);
            // console.log(this.items);
            this.gogo(this.items);
          },
          (error) => {}
        );
      },
      (error) => {
        this.mtype = "";
      }
    );
  },
};
</script>

<style>
</style>