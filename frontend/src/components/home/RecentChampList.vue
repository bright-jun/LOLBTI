<template>
  <v-card max-width="450" class="mx-auto">
    <v-list three-line>
      <template v-for="(item, index) in items">
        <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader>

        <v-divider v-else-if="item.divider" :key="index" :inset="item.inset"></v-divider>

        <v-list-item v-else :key="item.title">
          <!-- 위에 빠짐 @click  -->
          <v-list-item-avatar>
            <v-img :src="item.avatar"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title v-html="item.title"></v-list-item-title>
            <v-list-item-subtitle v-html="item.subtitle"></v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-list>
  </v-card>
</template>

<script>
import axios from "axios";
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
    //sohwan_chal="https://kr.api.riotgames.com/lol/league/v4/challengerleagues/by-queue/" +'RANKED_SOLO_5x5' +'?api_key=' + api_key
    // axios
    //   .get(`http://localhost:8080/summoner/recentchamp`)
    //   .then((res) => {
    //     this.items = {
    //       avartar: res.data.imageurl,
    //       role: res.data.role,
    //       lane: res.data.lane,
    //       time: res.data.timestamp,
    //     };
    //     console.log(this.items);
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });
  },
};
</script>
