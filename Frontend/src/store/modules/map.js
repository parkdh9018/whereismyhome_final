import http from "@/api/http";

export default {
  namespaced: true,
  state: {
    aptlist: [],
  },

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
    getaptlist_move(context, positions) {
      http
        .get(
          `/map/apt/nearby?lat1=${positions[0].getLat()}&lng1=${positions[0].getLng()}&lat2=${positions[1].getLat()}&lng2=${positions[1].getLng()}`
        )
        .then(({ data }) => {
          context.commit("setAptlist", data);
        });
    },
  },
};
