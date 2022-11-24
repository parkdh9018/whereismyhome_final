import api from "@/api/http";

// 아파트
function getApartDeal(param, success, fail) {
  api.get("housedeal/apart", { params: param }).then(success).catch(fail);
}

function getApartDealDetail(param, success, fail) {
  api.get("housedeal/apartDetail", { params: param }).then(success).catch(fail);
}

// 다세대주택
function getMultiplexHouseDeal(param, success, fail) {
  api.get("housedeal/multiplex", { params: param }).then(success).catch(fail);
}

function getMultiplexHouseDealDetail(param, success, fail) {
  api
    .get("housedeal/multiplexDetail", { params: param })
    .then(success)
    .catch(fail);
}

// 오피스텔
function getOfficetelDeal(param, success, fail) {
  api.get("housedeal/officetel", { params: param }).then(success).catch(fail);
}

function getOfficetelDealDetail(param, success, fail) {
  api
    .get("housedeal/officetelDetail", { params: param })
    .then(success)
    .catch(fail);
}

function getMarkerAptList(aptCode,success,fail) {
  api.get(`housedeal/markerApt/${aptCode}`).then(success).catch(fail);
}

export {
  getApartDeal,
  getApartDealDetail,
  getMultiplexHouseDeal,
  getMultiplexHouseDealDetail,
  getOfficetelDeal,
  getOfficetelDealDetail,
  getMarkerAptList
};
