import Home from "./views/Home.vue";
import Welcome from "./views/Welcome.vue";
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
];
