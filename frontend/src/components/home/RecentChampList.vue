<template>
  <v-card class="mx-auto">
    <v-list three-line v-if="!isEmpty">
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
    <v-list v-if="isEmpty">
      <p class="mt-3 text-center font-weight-black text-h5">
        데이터가 없습니다.
      </p>
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
    isEmpty: false,
  }),
  props: ["items"],
  created() {
    // console.log(typeof items);
    if (this.items.length == 1) {
      console.log("들어오니");
      this.isEmpty = true;
    } else {
      console.log("안들어와");
    }
  },
};
</script>
