<template>
  <v-card class="mx-auto">
    <v-list three-line>
      <template v-for="(item, index) in items">
        <v-subheader
          v-if="item.header"
          :key="item.header"
          v-text="item.header"
        ></v-subheader>

        <v-divider
          v-else-if="item.divider"
          :key="index"
          :inset="item.inset"
        ></v-divider>

        <v-list-item v-else :key="item.role">
          <!-- 위에 빠짐 @click  -->
          <v-list-item-avatar>
            <v-img :src="item.avatar"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            {{ item.champion }}
          </v-list-item-content>
          <v-sapcer></v-sapcer>
          <v-list-item-content>
            <!-- {{ item.lane }} -->
          </v-list-item-content>
          <v-sapcer></v-sapcer>
          <v-list-item-content>
            {{ item.time }}
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
    // items: [
    // { header: "최근 챔프 기록" },
    // {
    //   avatar: "http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/Lux.png",
    //   title: "Brunch this weekend?",
    //   subtitle:
    //     "<span class='text--primary'>Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?",
    // },
    // { divider: true, inset: true },
    // ],
  }),
  props: ["items"],
  created() {},
};
</script>
