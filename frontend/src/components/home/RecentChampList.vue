<template>
  <v-card max-width="450" class="mx-auto">
    <v-list three-line>
      <template v-for="(item, index) in items">
        <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader>

        <v-divider v-else-if="item.divider" :key="index" :inset="item.inset"></v-divider>

        <v-list-item v-else :key="item.role">
          <!-- 위에 빠짐 @click  -->
          <v-list-item-avatar>
            <v-img :src="item.avatar"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>역할: {{item.role}} 라인: {{item.lane}}</v-list-item-title>
            <!-- <v-list-item-subtitle v-html="item.lane"></v-list-item-subtitle> -->
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-list>
  </v-card>
</template>

<script>
import UserApi from "../../api/UserApi.js";
export default {
  data: () => ({
    items: [
      { header: "최근 챔프 기록" },
      // {
      //   avatar: "http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/Lux.png",
      //   title: "Brunch this weekend?",
      //   subtitle:
      //     "<span class='text--primary'>Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?",
      // },
      // { divider: true, inset: true },
    ],
  }),
  created() {
    UserApi.requestUserRecentChamp(
      this.$route.params.summonername,
      (res) => {
        // console.log(res.data);
        for (let index = 0; index < res.data.length; index++) {
          this.items.push({
            avatar:
              "http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/" +
              this.$store.getters.getChampNameByNo(res.data[index].champion) +
              ".png",
            role: res.data[index].role,
            lane: res.data[index].lane,
          });
          if (index != res.data.length - 1) {
            this.items.push({
              divider: true,
              inset: true,
            });
          }
          console.log(this.items.avatar);
          // console.log(info);
          // console.log(divide);
        }
        console.log(this.items);
      },
      (error) => {}
    );
  },
};
</script>
