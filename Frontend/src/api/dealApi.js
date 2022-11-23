import api from "@/api/http";

function getApartDetail(param, success, fail) {
  api.get(`housedeal/apart/${param}`).then(success).catch(fail);
}

function getMultiplexes(param, success, fail) {
  api.get(`housedeal/multiplex/${param}`).then(success).catch(fail);
}

function getMultiplexDetail(param, success, fail) {
  api.get(`housedeal/multiplexDetail/${param}`).then(success).catch(fail);
}

function getApts(params, success, fail) {
  api.get("map/apt/dong", { params: params }).then(success).catch(fail);
}

function getOfficetels(param, success, fail) {
  api.get(`housedeal/officetel/${param}`).then(success).catch(fail);
}

function getOffiecetelDetail(param, success, fail) {
  api.get(`housedeal/officetelDetail/${param}`).then(success).then(fail);
}

export {
  getApartDetail,
  getMultiplexes,
  getMultiplexDetail,
  getApts,
  getOfficetels,
  getOffiecetelDetail,
};
