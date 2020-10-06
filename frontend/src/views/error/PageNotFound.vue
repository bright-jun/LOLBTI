<template>
    <v-app id="inspire">
        <div class="text-center">
            <v-img class="mx-auto" style="margin-bottom: 30px;" height="500" width="600" src="../../assets/img/amumu.png"></v-img>
            <h1 align="center">페이지를 찾을 수 없습니다.</h1>
            <h3 align="center">주소가 잘못 입력되었거나, 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.</h3>
            <div>
                <v-btn class="ma-2" color="orange darken-2" dark @click="moveBack">
                    <v-icon dark left>mdi-arrow-left</v-icon>이전
                </v-btn>
                <v-btn class="ma-2" color="primary" dark @click="moveWelcome">
                    홈
                </v-btn>
            </div>
        </div>
  </v-app>
</template>
<script>
    export default {
        name : 'PageNotFound',
        methods: {
            moveWelcome(){
                this.$router.push("/");
            },
            moveBack(){
                this.$router.go(-1);
            }
        }
    }
</script>