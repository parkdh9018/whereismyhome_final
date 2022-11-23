import { getMultiplexes } from "@/api/dealApi";
import { keywordSearch } from "@/api/areaApi";

const doFilter = (button) => {
  let result = [];
  button.forEach((v) => {
    if (v.state) result.push(v.caption);
  });
  console.log(result);
  return result;
};

const getBuildings = (commit, type, address_init, dongcode, filter) => {
  // 이름 바꿔야함
  getMultiplexes(
    {
      //데이터 형식
      type,
    },
    ({ data }) => {
      const data_worked = data.map((v) => {
        return {
          id: v.sgdbb_cd,
          name: v.bldg_nm,
          category: v.house_type,
          address: `${address_init} ${v.bobn}-${v.bubn}`,
        };
      });
      commit("addStructList", data_worked);
    }
  );
};

const mapStore = {
  namespaced: true,
  state: () => ({
    structList: [], // 이름, 주소, 타입, 좌표(lng, lat)
    center: null,
    level: 2,
    address: "",
    detailToggle: false,
    structDetailPos: null,
    structDetail: null,
    filter_buttons: [
      [
        { caption: "전세", state: true },
        { caption: "월세", state: true },
        { caption: "매매", state: true },
      ],
      [
        { caption: "아파트", state: true },
        { caption: "오피스텔", state: true },
        { caption: "다가구", state: true },
      ],
    ],
  }),

  getters: {
    level(state) {
      return state.level;
    },
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
    setLevel(state, payload) {
      state.level = payload;
    },
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
    // 키워드 검색 액션
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

    // 동 안에 있는 건축물들
    getStructInDong({ commit, state }, [address_init, dongcode]) {
      commit("structClear");

      const filter1 = doFilter(state.filter_buttons[0]);
      const filter2 = doFilter(state.filter_buttons[1]);

      // 다가구주택 정보들 불러옴
      if (filter2.length == 0 || filter2.includes("다가구")) {
        getBuildings(commit, "다가구", address_init, dongcode, filter1);
      }
      // 아파트
      if (filter2.length == 0 || filter2.includes("아파트")) {
        getBuildings(commit, "아파트", address_init, dongcode, filter1);
      }
      // 오피스텔
      if (filter2.length == 0 || filter2.includes("오피스텔")) {
        getBuildings(commit, "오피스텔", address_init, dongcode, filter1);
      }

      //// 예전 아파트 정보 불러옴
      // if (filter2.length == 0 || filter2.includes("아파트")) {
      //   console.log("아파트");
      //   getApts(
      //     { code: dongcode },
      //     ({ data }) => {
      //       const data_worked = data.map((v) => {
      //         return {
      //           id: v.aptCode,
      //           name: v.apartmentName,
      //           category: "아파트",
      //           address: `${address_init} ${v.jibun}`,
      //           lat: v.lat,
      //           lng: v.lng,
      //         };
      //       });
      //       commit("addStructList", data_worked);
      //     },
      //     (err) => console.log(err)
      //   );
      // }
    },

    // 상세 정보
    getDetail({ commit }, code) {
      getDeals(
        {
          code: code,
        },
        ({ data }) => {
          commit("setStructDetail", data);
        }
      );
    },
  },
};

export default mapStore;
