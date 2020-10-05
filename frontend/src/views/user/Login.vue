<template>
  <div>
    <nav-bar :viewType="navbarType" />
    <v-col align="center" md="4" offset-md="4" class="my-10">
      <v-img class="white--text align-end" width="600" height="300" src="../../assets/images/lolbti_logo_2.png"></v-img>

      <v-card class="mx-auto my-5" max-width="500">
        <v-card-text class="text--primary" style="">로그인</v-card-text>
        <div class="px-10">
          <v-text-field class="mt-10" v-model="email" label="이메일" outlined hide-details></v-text-field>
          <div align="left" class="error-text" v-if="error.email">
            <b>{{error.email}}</b>
          </div>

          <v-text-field
            class="mt-10"
            v-model="password"
            type="password"
            @keyup.enter="Login"
            label="비밀번호"
            outlined
            hide-details
          ></v-text-field>
          <div align="left" class="error-text" v-if="error.password">
            <b>{{error.password}}</b>
          </div>

          <v-btn
            class="mt-13"
            block
            color="btncolor"
            @click="onLogin"
            :disabled="!isSubmit"
            :class="{disabled : !isSubmit}"
          >Login</v-btn>
          <v-btn class="mt-5" block color="btncolor">Join</v-btn>
          <br>
          <br>
        </div>
      </v-card>
    </v-col>
  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import UserApi from "../../api/UserApi";
import NavBar from "../../components/NavBar.vue";

export default {
  components: {
    NavBar,
  },
  created() {
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
  watch: {
    password: function (v) {
      this.checkForm();
    },
    email: function (v) {
      this.checkForm();
    },
  },
  methods: {
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    onLogin() {
      if (this.isSubmit) {
        let { email, password } = this;
        let data = {
          email,
          password,
        };
        this.isSubmit = false;
        UserApi.requestLogin(
          data,
          (res) => {
            this.isSubmit = true;
            this.$session.start();
            this.$session.set('userinfo',{
              email: this.$store.state.email,
              summonerName: this.$store.state.summonerName,
            });
          
            this.$router.push({path : "/home/"+this.$store.state.summonerName });
          },
          (error) => {}
        );
      }
    },
  },
  data: () => {
    return {
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false,
      },
      isSubmit: false,
      navbarType: true,
    };
  },
};
</script>
<style scoped>
.error-text {
  color: #e53935;
  font-size: 12px;
}
</style>
