<template>
  <v-app class="grey lighten-4">
    <NavBar />
    <Banner />
    <v-main>
      <router-view> </router-view>
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import NavBar from "./components/NavBar.vue";
import Banner from "./components/Banner.vue";
import Footer from "./components/Footer.vue";
export default {
  name: "App",

  components: {
    NavBar,
    Banner,
    Footer,
  },

  data: () => ({
    //
  }),
};
</script>
