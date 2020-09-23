<template>
  <div>
    <nav-bar :viewType="navbarType" />
    <v-col align="center" md="4" offset-md="4" class="my-10">
      <v-img class="white--text align-end" width="400" height="300" src="../../assets/logo.png"></v-img>

      <v-card class="mx-auto my-5" max-width="500">
        <v-card-text class="display-1 text--primary">Sign Up</v-card-text>
        <div class="px-10">
          <v-text-field class="mt-10" v-model="email" label="이메일" outlined hide-details></v-text-field>
          <div align="left" class="error-text" v-if="error.email">
            <b>{{error.email}}</b>
          </div>

          <v-text-field
            class="mt-5"
            v-model="password"
            type="password"
            label="비밀번호"
            outlined
            hide-details
          ></v-text-field>
          <div align="left" class="error-text" v-if="error.password">
            <b>{{error.password}}</b>
          </div>

          <v-text-field
            class="mt-5"
            v-model="passwordRe"
            type="password"
            label="비밀번호 확인"
            outlined
            hide-details
          ></v-text-field>
          <div align="left" class="error-text" v-if="error.passwordRe">
            <b>{{error.passwordRe}}</b>
          </div>

          <v-text-field class="mt-10" v-model="userId" label="소환사명" outlined hide-details></v-text-field>

          <v-text-field class="mt-5" v-model="userMbti" label="소환사 MBTI" outlined hide-details></v-text-field>

          <v-btn class="my-10" block color="orange">Join</v-btn>
          <v-divider></v-divider>
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
    passwordRe: function (v) {
      this.checkForm();
    },
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

      if (this.passwordRe != this.password)
        this.error.passwordRe = "비밀번호가 일치하지 않습니다.";
      else this.error.passwordRe = false;

      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    onJoin() {
      if (this.isSubmit) {
        let { email, password, userId, userMbti } = this;
        let data = {
          email,
          password,
          userId,
          userMbti,
        };
        this.isSubmit = false;
        UserApi.requestJoin(
          data,
          (res) => {},
          (error) => {}
        );
      }
    },
  },
  data: () => {
    return {
      email: "",
      password: "",
      passwordRe: "",
      userId: "",
      userMbti: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        password: false,
        passwordRe: false,
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
