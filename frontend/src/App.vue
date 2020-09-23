<template>
  <v-app class="grey lighten-4">
    <router-view></router-view>
    <Footer></Footer>
  </v-app>
</template>

<script>
import Footer from "./components/Footer.vue";
export default {
  name: "App",

  components: {
    Footer,
  },

  data: () => ({
    //
  }),
};
</script>
