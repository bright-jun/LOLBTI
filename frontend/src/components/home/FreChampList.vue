<template>
  <v-card class="mx-auto">
    <v-card-text>선호챔피언</v-card-text>
    <v-list>
      <v-list-item v-for="item in items" :key="item.title">
        <v-list-item-avatar>
          <v-img :src="item.avatar"></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title
            >{{ item.champion }} {{ item.freq }}</v-list-item-title
          >
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-card>
</template>

<script>
import UserApi from "../../api/UserApi.js";

export default {
  data() {
    return {
      items: [
        // { icon: true },
        //   title: "Jason Oner",
        //   avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
        // },
        // {
        //   title: "Travis Howard",
        //   avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
        // },
        // {
        //   title: "Ali Connors",
        //   avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg",
        // },
        // {
        //   title: "Ali Connors",
        //   avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg",
        // },
      ],
    };
  },
  created() {
    var self = this;
    UserApi.requestFreqChampList(
      this.$route.params.summonername,
      (res) => {
        console.log(res.data);
        for (var index = 0; index < res.data.freqChampAvartar.length; index++) {
          console.log(res.data.freqChampAvartar[index]);
          this.items.push({
            champion: self.$store.getters.getChampNameByNo(
              res.data.freqChampAvartar[index]
            ),
            avatar:
              "http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/" +
              self.$store.getters.getChampIdByNo(
                res.data.freqChampAvartar[index]
              ) +
              ".png",
            freq: (res.data.freqChampScore[index] * 100).toFixed(2),
          });
        }
      },
      (error) => {}
    );
  },
};
</script>
