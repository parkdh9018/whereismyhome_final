import {
  getApartDetail,
  getMultiplexes,
  getMultiplexDetail,
  getApts,
  getOfficetels,
  getOffiecetelDetail,
} from "@/api/dealApi";
import { keywordSearch } from "@/api/areaApi";

const mapStore = {
  namespaced: true,
  state: () => ({
    structList: [], // 이름, 주소, 타입, 좌표(lng, lat)
    center: null,
    address: "",
    detailToggle: false,
    structDetailPos: null,
    structDetail: null,
    filter_buttons: [
      [
        { caption: "전세", state: true },
        { caption: "월세", state: true },
      ],
      [
        { caption: "아파트", state: true },
        { caption: "오피스텔", state: true },
        { caption: "다세대", state: true },
      ],
    ],
  }),

  getters: {
    structDetailPos(state) {
      return state.structDetailPos;
    },
    structList(state) {
      return state.structList;
    },
    filterSale(state) {
      return state.filterSale;
    },
    filterType(state) {
      return state.filterType;
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
    structDetail(state) {
      return state.structDetail;
    },
  },

  mutations: {
    structClear(state) {
      state.structList = [];
    },
    addStructList(state, payload) {
      state.structList = [...state.structList, ...payload];
    },
    setStructList(state, payload) {
      state.structList = payload;
    },
    setFilterSale(state, payload) {
      state.filterSale = payload;
    },
    setFilterType(state, payload) {
      state.filterType = payload;
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
    setStructDetail(state, payload) {
      state.structDetail = payload;
    },
    setStructDetailPos(state, payload) {
      state.structDetailPos = payload;
    },
  },

  actions: {
    getStructByKeyword({ commit, state }, keyword) {
      commit("structClear");

      keywordSearch(keyword, state.center).then(([data]) => {
        const data_worked = data.map((place) => {
          return {
            id: place.id,
            name: place.place_name,
            address: place.address_name,
            category: place.category_group_name,
            lng: place.x,
            lat: place.y,
            place_url: place.place_url,
          };
        });

        commit("addStructList", data_worked);
      });
    },
    getStructInDong({ commit }, [address_init, dongcode]) {
      console.log(dongcode);

      commit("structClear");

      getApts(
        { code: dongcode },
        ({ data }) => {
          const data_worked = data.map((v) => {
            return {
              id: v.aptCode,
              name: v.apartmentName,
              category: "아파트",
              address: `${address_init} ${v.jibun}`,
              lat: v.lat,
              lng: v.lng,
            };
          });
          commit("addStructList", data_worked);
        },
        (err) => console.log(err)
      );
      getMultiplexes(dongcode.slice(0, 8), ({ data }) => {
        const data_worked = data.map((v) => {
          return {
            id: v.code,
            name: v.bldg_nm,
            category: v.house_type,
            address: `${address_init} ${v.bobn}-${v.bubn}`,
          };
        });
        commit("addStructList", data_worked);
      });
    },

    getDetail({ commit }, [type, code]) {
      if (type == "아파트") {
      } else if (type == "연립다세대") {
      } else if (type == "오피스텔") {
      }
    },
  },
};

export default mapStore;
