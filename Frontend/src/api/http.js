import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "http://70.12.50.85:9999/vue",
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
});
