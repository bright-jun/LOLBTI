<template>
  <v-card>
    <v-card-text>
      <div>MBTI 유형</div>
      <p class="text-sm-center text-md-center text-sm-h3 text-md-h2">{{mtype}}</p>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  data() {
    return {};
  },
  props: {
    mtype: {
      type: String,
    },
  },
};
</script>

<style lang="">
</style>