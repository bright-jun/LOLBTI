import Home from "./views/Home.vue";
import Welcome from "./views/Welcome.vue";
import Login from "./views/user/Login.vue";
export default [
  {
    path: "/home",
    name: "Home",
    component: Home,
  },
  {
    path: "/",
    name: "Welcome",
    component: Welcome,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
];
