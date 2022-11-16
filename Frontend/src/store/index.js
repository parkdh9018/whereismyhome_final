import Vue from "vue";
import Vuex from "vuex";
import board from "./modules/board";
import map from "./modules/map";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    board,
    map,
  },
});
