import Home from "./views/Home.vue";
import Welcome from "./views/Welcome.vue";
import Login from "./views/user/Login.vue";
import Join from "./views/user/Join.vue";

export default [
  {
    path: "/home/:summonername",
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
  {
    path: "/join",
    name: "Join",
    component: Join,
  },
];
