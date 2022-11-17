import http from "@/api/http";

const mapStore = {
  namespaced: true,
  state: ()=>({
    aptlist: [],
  }),

  getters: {
    aptlist(state) {
      return state.aptlist;
    },
  },

  mutations: {
    setAptlist(state, payload) {
      state.aptlist = payload;
    },
  },

  actions: {
    getaptlist_move({commit}, positions) {
      http
        .get(
          `/map/apt/nearby?lat1=${positions[0].getLat()}&lng1=${positions[0].getLng()}&lat2=${positions[1].getLat()}&lng2=${positions[1].getLng()}`
        )
        .then(({ data }) => {
          commit("setAptlist", data);
        });
    },
  },
};

export default mapStore;