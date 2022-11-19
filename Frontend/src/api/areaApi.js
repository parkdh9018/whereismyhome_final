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
  
  export { sidoList, gugunList, houseList, dongList, areaList };
  