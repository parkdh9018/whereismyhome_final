import http from "@/api/http";

const boardStore = {
  namespaced: true,
  state: () =>({
    boards: [],
    board: {},
    comments: [],
  }),
  getters: {
    boards(state) {
      return state.boards;
    },
    board(state) {
      return state.board;
    },
    comments(state) {
      return state.comments;
    },
  },
  mutations: {
    setBoards(state, payload) {
      state.boards = payload;
    },
    setBoard(state, payload) {
      state.board = payload;
    },
    setComments(state, payload) {
      state.comments = payload;
    },
  },
  actions: {
    getBoards({commit}) {
      http
        .get("/board")
        .then(({ data }) => {
          commit("setBoards", data);
        })
        .catch(() => {
          alert("에러발생!");
        });
    },
    getBoard({commit}, payload) {
      http.get(payload).then(({ data }) => {
        commit("setBoard", data);
      });
    },
    getComments({commit}, payload) {
      http.get(payload).then(({ data }) => {
        commit("setComments", data);
      });
    },
  },
};

export default boardStore;