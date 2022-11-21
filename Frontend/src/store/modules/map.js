import http from "@/api/http";

const mapStore = {
  namespaced: true,
  state: () => ({
    aptlist: [],
    center: null,
    address: "",
    detailToggle: false,
    detailData: null,
  }),

  getters: {
    aptlist(state) {
      return state.aptlist;
    },
    center(state) {
      return state.center;
    },
    address(state) {
      return state.address;
    },
    detailToggle(state) {
      return state.detailToggle;
    },
    detailData(state) {
      return state.detailData;
    },
  },

  mutations: {
    setAptlist(state, payload) {
      state.aptlist = payload;
    },
    setCenter(state, payload) {
      state.center = payload;
    },
    setAddress(state, payload) {
      state.address = payload;
    },
    setDetailToggle(state, payload) {
      state.detailToggle = payload;
    },
    setDetailData(state, payload) {
      state.detailData = payload;
    },
  },

  actions: {
    getaptlist_move({ commit }, positions) {
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
