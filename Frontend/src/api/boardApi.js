import api from "./http";

function listArticle(param, success, fail) {
  api.get(`/board`, { params: param }).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(articleno, success, fail) {
  api.get(`/board/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  api.delete(`/board/${articleno}`).then(success).catch(fail);
}

function getComments(articleno, success, fail) {
  api.get(`/comment/${articleno}`).then(success).catch(fail);
}

function registComment(comment, success, fail) {
  api.post(`/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

function updateComments(comment, success, fail) {
  api.put(`/comment`, JSON.stringify(comment)).then(success).catch(fail);
}
export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle, getComments, registComment, updateComments };


