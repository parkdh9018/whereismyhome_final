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
    addComments(state, payload) {
      state.comments.push(payload);
    },
    setComments(state, payload) {
      state.comments = payload;
    },
  },
  actions: {
    getBoards(context) {
      http
        .get("/board")
        .then(({ data }) => {
          context.commit("setBoards", data);
        })
        .catch(() => {
          alert("에러발생!");
        });
    },
    getBoard(context, payload) {
      http.get(payload).then(({ data }) => {
        context.commit("setBoard", data);
      });
    },
    getComments(context, payload) {
      http.get(payload).then(({ data }) => {
        console.log("dsds");
        context.commit("setComments", data);

      });
    },
  },
};

export default boardStore;