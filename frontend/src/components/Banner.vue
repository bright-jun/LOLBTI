<template>
  <v-carousel cycle height="250" hide-delimiter-background show-arrows-on-hover>
    <v-carousel-item
      v-for="(item, i) in items"
      :key="i"
      :src="require(`@/assets/img/${item.src}`)"
    ></v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          src: "ashe.jpg",
        },
        {
          src: "lolbackground.jpg",
        },
        {
          src: "khazix.jpg",
        },
        {
          src: "timo.png",
        },
        {
          src: "loldcup.jpg",
        },
      ],
    };
  },
};
</script>