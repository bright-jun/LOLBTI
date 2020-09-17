<template>
  <v-app class="grey lighten-4">
    <NavBar />
    <v-main class="mx-4 my-4">
      <router-view> </router-view>
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import NavBar from "./components/NavBar.vue";
import Footer from "./components/Footer.vue";
export default {
  name: "App",

  components: {
    NavBar,
    Footer,
  },

  data: () => ({
    //
  }),
};
</script>
