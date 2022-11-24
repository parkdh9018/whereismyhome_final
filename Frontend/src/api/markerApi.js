import api from "@/api/http";

function markerSido(params) {
  return api.get(`/map/marker/sido/`, { params: params });
}

function markerGugun(params) {
  return api.get(`/map/marker/gugun/`, { params: params });
}

function markerDong(params) {
  return api.get(`/map/marker/dong/`, { params: params });
}

function searchDongAvg(params, success, fail) {
  api.get("map/searchGugunAvg", { params }).then(success).catch(fail);
}

export { markerSido, markerGugun, markerDong, searchDongAvg };
