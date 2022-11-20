import api from '@/api/http';
import axios from 'axios';

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

  function areaList(params, success, fail) {
    axios.get('https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes', {params : params}).then(success).then(fail);
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
  
  export { sidoList, gugunList, houseList, dongList, areaList, searchPosition, searchAddress };
  