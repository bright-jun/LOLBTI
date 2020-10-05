<template>
  <div>
    <v-row>
      <v-col cols="6">
        <v-card class="text-center" style="padding: 10px">
          <p class="my-6" style="fontsize: 30px">내 챔피언</p>
          <v-img
            :src="`http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/${myChamp.id}.png`"
            style="margin: auto; width: 100px"
          ></v-img>
          <p style="margin-top: 10px; fontsize: 15px">
            {{ myChamp.name }}{{ myChamp.key }}
          </p>
          <v-autocomplete
            class="mt-5"
            v-model="myChampName"
            outlined
            hide_details
            :items="champList"
            label="내 챔피언"
          ></v-autocomplete>
          <v-virtual-scroll
            :items="items"
            :bench="100"
            height="300"
            item-height="70"
          >
            <v-row class="mx-1">
              <v-col
                v-for="champAvatar in champAvatarList"
                :key="champAvatar"
                class="d-flex child-flex"
                cols="2"
                style="padding: 5px"
              >
                <v-img
                  :src="`http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/${champAvatar}.png`"
                  aspect-ratio="1"
                  class="grey lighten-2"
                  @click="selectAvatar(champAvatar)"
                ></v-img>
              </v-col>
            </v-row>
          </v-virtual-scroll>
        </v-card>
      </v-col>

      <v-col cols="6">
        <v-card class="text-center" style="padding: 10px">
          <p class="my-6" style="fontsize: 30px">상대 챔피언</p>
          <v-img
            :src="`http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/${vsChamp.id}.png`"
            style="margin: auto; width: 100px"
          ></v-img>
          <p style="margin-top: 10px; fontsize: 15px">
            {{ vsChamp.name }}{{ vsChamp.key }}
          </p>
          <v-autocomplete
            class="mt-5"
            v-model="vsChampName"
            outlined
            hide_details
            :items="champList"
            label="상대 챔피언"
          ></v-autocomplete>
          <v-virtual-scroll
            :items="items"
            :bench="100"
            height="300"
            item-height="70"
          >
            <v-row class="mx-1">
              <v-col
                v-for="champAvatar in champAvatarList"
                :key="champAvatar"
                class="d-flex child-flex"
                cols="2"
                style="padding: 5px"
              >
                <v-img
                  :src="`http://ddragon.leagueoflegends.com/cdn/10.19.1/img/champion/${champAvatar}.png`"
                  aspect-ratio="1"
                  class="grey lighten-2"
                  @click="selectVsAvatar(champAvatar)"
                ></v-img>
              </v-col>
            </v-row>
          </v-virtual-scroll>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12">
        <v-card class="text-center" height="200px" style="padding: 10px">
          <p class="my-6" style="fontsize: 30px">추천 아이템</p>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      benched: 0,
      champList: [],
      champAvatarList: [],
      myChampName: "",
      vsChampName: "",
      myChamp: { id: "", key: "", name: "" },
      vsChamp: { id: "", key: "", name: "" },
    };
  },
  created() {
    this.getChampList();
  },
  methods: {
    getChampList() {
      for (var i = 0; i < this.$store.state.champ.length; i++) {
        this.champList.push(this.$store.state.champ[i].name);
        this.champAvatarList.push(this.$store.state.champ[i].id);
      }
    },
    selectAvatar(champAvatar) {
      this.myChamp.id = champAvatar;
      this.myChamp.name = this.$store.getters.getChampNameById(champAvatar);
      this.myChamp.key = this.$store.getters.getChampKeyById(champAvatar);
      this.myChampName = this.myChamp.name;
    },
    selectVsAvatar(champAvatar) {
      this.vsChamp.id = champAvatar;
      this.vsChamp.name = this.$store.getters.getChampNameById(champAvatar);
      this.vsChamp.key = this.$store.getters.getChampKeyById(champAvatar);
      this.vsChampName = this.vsChamp.name;
    },
  },
  watch: {
    myChampName: function () {
      this.myChamp.name = this.myChampName;
      this.myChamp.id = this.$store.getters.getChampIdByName(this.myChampName);
      this.myChamp.key = this.$store.getters.getChampKeyByName(
        this.myChampName
      );
    },
    vsChampName: function () {
      this.vsChamp.name = this.vsChampName;
      this.vsChamp.id = this.$store.getters.getChampIdByName(this.vsChampName);
      this.vsChamp.key = this.$store.getters.getChampKeyByName(
        this.vsChampName
      );
    },
  },
  computed: {
    items() {
      return Array.from({ length: this.length }, (k, v) => v + 1);
    },
    length() {
      return 1;
    },
  },
};
</script>

<style>
</style>