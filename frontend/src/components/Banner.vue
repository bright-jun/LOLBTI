<template>
  <v-carousel cycle height="250" hide-delimiter-background show-arrows-on-hover>
    <v-carousel-item
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
      reverse-transition="fade-transition"
      transition="fade-transition"
    ></v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          src:
            "https://c.wallhere.com/photos/32/6e/League_of_Legends_Ashe_League_of_Legends_mountains_sky-1894075.jpg!d",
        },
        {
          src:
            "https://c.wallhere.com/photos/bc/ac/League_of_Legends_Irelia_Karma_League_of_Legends_Akali_League_of_Legends_Yasuo_League_of_Legends_fighting_video_game_girls_dark_hair-1545799.jpg!d",
        },
        {
          src:
            "https://a-static.besthdwallpaper.com/rigeu-obeu-rejeondeu-lol-kha-zix-byeogji-3840x1440-41377_92.jpg",
        },
        {
          src: "https://images4.alphacoders.com/600/thumb-1920-600528.png",
        },
        {
          src:
            "https://upload2.inven.co.kr/upload/2017/11/04/bbs/i13962722566.jpg",
        },
      ],
    };
  },
};
</script>