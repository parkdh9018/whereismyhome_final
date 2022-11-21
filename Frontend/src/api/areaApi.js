import api from "@/api/http";
import axios from "axios";

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}
function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`map/apt/dong/`, { params: params }).then(success).catch(fail);
}

function aptListInDong(params, success, fail) {
  api.get("map/apt/dong", { params: params }).then(success).then(fail);
}

// 지역 코드 받아오는 함수
function areaList(params, success, fail) {
  axios
    .get("https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes", {
      params: params,
    })
    .then(success)
    .then(fail);
}

// 주소로 위도, 경도 찾는 함수
function searchPosition(address) {
  return new Promise((resolve, reject) => {
    const geocoder = new kakao.maps.services.Geocoder();

    geocoder.addressSearch(address, function (result, status) {
      if (status === kakao.maps.services.Status.OK) {
        const point = new kakao.maps.LatLng(result[0].y, result[0].x);
        resolve(point);
      } else {
        reject(status);
      }
    });
  });
}

// 위도, 경도로 주소를 찾는 함수
function searchAddress(position) {
  return new Promise((resolve, reject) => {
    const geocoder = new kakao.maps.services.Geocoder();

    geocoder.coord2Address(
      position.getLng(),
      position.getLat(),
      function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
          for (var i = 0; i < result.length; i++) {
            resolve(result[i].address);
          }
        } else {
          reject(status);
        }
      }
    );
  });
}

//키워드로 장소 검색 하는 함수
function keywordSearch(keyword) {
  return new Promise((resolve, reject) => {
    if (!keyword.replace(/^\s+|\s+$/g, "")) {
      alert("키워드를 입력해주세요!");
      reject(false);
    }

    const place = new kakao.maps.services.Places();

    place.keywordSearch(keyword, function (data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        // displayPlaces(data);
        console.log(pagination);
        resolve([data, pagination]);
        // 페이지 번호를 표출합니다
        // displayPagination(pagination);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert("검색 결과가 존재하지 않습니다.");
        reject("fail");
      } else if (status === kakao.maps.services.Status.ERROR) {
        alert("검색 결과 중 오류가 발생했습니다.");
        reject("fail");
      }
    });
  });
}

export {
  sidoList,
  gugunList,
  houseList,
  dongList,
  areaList,
  searchPosition,
  searchAddress,
  aptListInDong,
  keywordSearch,
};
