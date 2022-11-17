import Vue from "vue";
import Vuex from "vuex";
import board from "./modules/board";
import map from "./modules/map";
import member from "./modules/member";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    board,
    map,
    member,
  },
});
