<template>
  <div>
    <v-container>
      <v-img
        class="ma-auto"
        src="../assets/images/lolbti_logo_2.png"
        height="180"
        width="310"
      />
      <v-row class="pt-5">
        <v-col md="3"></v-col>
        <v-col xs="11" md="5">
          <v-form>
            <v-text-field outline label="닉네임 검색" append-icon="search"></v-text-field>
          </v-form>
        </v-col>
        <v-col xs="1" md="1" class="pt-5">
          <v-btn class="btncolor">검색</v-btn>
        </v-col>
        <v-col md="3"></v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {};
</script>

<style lang=""></style>
