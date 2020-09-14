import "material-design-icons-iconfont/dist/material-design-icons.css";
import Vue from "vue";
import Vuetify from "vuetify/lib";

Vue.use(Vuetify);

export default new Vuetify({
  iconfont: "md",
  theme: {
    themes: {
      light: {
        maincolor: "#FFE082",
        btncolor: "#FF8F00",
      },
    },
  },
});
