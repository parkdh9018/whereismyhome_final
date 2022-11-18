import api from "./http";

async function login(user, success, fail) {
  await api.post(`/member/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/member/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/member/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/member/logout/${userid}`).then(success).catch(fail);
}

function idCheck(user) {
  return api.post("/member/idCheck", user);
}

function regist(user) {
  return api.post("/member", user);
}

function passwordFind(user) {
  return api.post("/member/passwordFind", user);
}

function modifyMember(user) {
  return api.put("/member", user);
}

export { login, findById, tokenRegeneration, logout, idCheck, regist, passwordFind, modifyMember };
