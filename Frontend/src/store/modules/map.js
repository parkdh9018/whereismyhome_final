import * as dealApi from "@/api/dealApi";
import { keywordSearch } from "@/api/areaApi";

const doFilter = (button) => {
  let result = [];
  button.forEach((v) => {
    if (v.state) result.push(v.caption);
  });
  return result;
};

const mapStore = {
  namespaced: true,
  state: () => ({
    structList: [], // 이름, 주소, 타입, 좌표(lng, lat)
    center: null,
    level: 0,
    address: "",
    detailToggle: false,
    structDetailPos: null,
    structDetail: {
      max_amt: '',
      avg_amt: '',
      max_gtn: '',
      avg_gtn: ''
    },
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
    levelMove(state, payload) {
      state.level = payload;
    },
    structDetailClear(state) {
      state.structDetail = {
        max_amt: '',
        avg_amt: '',
        max_gtn: '',
        avg_gtn: ''
      };
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

      let filter1 = doFilter(state.filter_buttons[0]);
      let filter2 = doFilter(state.filter_buttons[1]);

      let param = {
        sgd_cd: dongcode,
      };

      filter1.forEach((v) => {
        if (v == "매매") {
          param.sell = 1;
        } else if (v == "전세") {
          param.year = 1;
        } else {
          param.month = 1;
        }
      });

      // 아파트
      if (filter2.length == 0 || filter2.includes("아파트")) {
        dealApi.getApartDeal(param, ({ data }) => {
          const data_worked = data.filter(v => v.bobn != "").map((v) => {
            return {
              id: v.sgdbb_cd,
              name:  v.bldg_nm == "" ? `${v.bobn}-${v.bubn}` : v.bldg_nm,
              category: "아파트",
              address: `${address_init} ${v.bobn}-${v.bubn}`,
            };
          });
          commit("addStructList", data_worked);
        });
      }

      // 다가구주택 정보들 불러옴
      if (filter2.length == 0 || filter2.includes("다가구")) {
        dealApi.getMultiplexHouseDeal(param, ({ data }) => {
          const data_worked = data.filter(v => v.bobn != "").map((v) => {
            return {
              id: v.sgdbb_cd,
              name:  v.bldg_nm == "" ? `${v.bobn}-${v.bubn}` : v.bldg_nm,
              category: "연립다세대",
              address: `${address_init} ${v.bobn}-${v.bubn}`,
            };
          });
          commit("addStructList", data_worked);
        });
      }

      // 오피스텔
      if (filter2.length == 0 || filter2.includes("오피스텔")) {
        dealApi.getOfficetelDeal(param, ({ data }) => {
          const data_worked = data.filter(v => v.bobn != "").map((v) => {
            return {
              id: v.sgdbb_cd,
              name: v.bldg_nm == "" ? `${v.bobn}-${v.bubn}` : v.bldg_nm,
              category: "오피스텔",
              address: `${address_init} ${v.bobn}-${v.bubn}`,
            };
          });
          commit("addStructList", data_worked);
        });
      }

    },

    markerDetail({commit}, aptInfo) {
      dealApi.getMarkerAptList(aptInfo.aptCode, ({data}) => {
        
        const obj = {
          avg_amt: 0,
          max_amt: 0,
          housedealDto : [],
          amtDto : [],
        }
        
        let max = 0;
        let sum = 0;

        data.forEach(apt => {

          const price = apt.dealAmount.replace(",", "");

          sum += parseInt(price);
          max = Math.max(price, max);

          const date = `${apt.dealYear}${apt.dealMonth.length == 2 ? apt.dealMonth : `0${apt.dealMonth}`}`;

          obj.housedealDto.push({
            bldg_nm : aptInfo.apartmentName,
            bldg_area : apt.area,
            deal_ymd : date,
            floor : apt.floor,
            house_type: '아파트',
            deal_type : '매매',
            build_year : aptInfo.buildYear,
            obj_amt: price,
          })

          obj.amtDto.push({
            obj_amt : price,
            yearmonth : date
          })

        })


        obj.avg_amt = parseInt(sum / data.length).toString();
        obj.max_amt = max.toString();

        commit("setStructDetail", obj);

      });
    },

    // 상세 정보
    getDetail({ commit }, [code, type]) {

      commit("structDetailClear");

      console.log("===detail");

      let param = {
        sgdbb_cd: code,
      };
      console.log("getDetail");
      console.log(code, type);
      if (type == "아파트") {
        dealApi.getApartDealDetail(param, ({ data }) => {
          console.log(data);
          commit("setStructDetail", data);
        });
      } else if (type == "연립다세대") {
        dealApi.getMultiplexHouseDealDetail(param, ({ data }) => {
          console.log(data);
          commit("setStructDetail", data);
        });
      } else {
        dealApi.getOfficetelDealDetail(param, ({ data }) => {
          console.log(data);
          commit("setStructDetail", data);
        });
      }
    },
  },
};

export default mapStore;
