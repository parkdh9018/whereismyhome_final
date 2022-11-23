import api from "@/api/http";

function markerSido(params) {
    return api.get(`/map/marker/sido/`, {params : params});
}

function markerGugun(params) {
    return api.get(`/map/marker/gugun/`, {params : params});
}

function markerDong(params) {
    return api.get(`/map/marker/dong/`, {params : params});
}

export {markerSido, markerGugun, markerDong}