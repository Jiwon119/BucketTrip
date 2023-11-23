import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function userConfirm(param, success, fail) {
  console.log("param", param);
  await local.post(`/member/login`, param).then(success).catch(fail);
  console.log("userConfirm ok");
}

async function findById(userid, success, fail) {
  local.defaults.headers["Authorization"] =
    sessionStorage.getItem("accessToken");
  await local.get(`/member/info/${userid}`).then(success).catch(fail);
}

async function deleteById(userid, success, fail) {
  local.defaults.headers["Authorization"] =
    sessionStorage.getItem("accessToken");
  await local.delete(`/member/delete/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] =
    sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`/member/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`/member/logout/${userid}`).then(success).catch(fail);
}

async function regist(param, success, fail) {
  await local.post(`/member/regist`, param).then(success).catch(fail);
}

async function update(param, success, fail) {
  await local.put(`/member/updateMember`, param).then(success).catch(fail);
}

async function findUser(param, success, fail) {
  await local.post(`/member/info`, param).then(success).catch(fail);
}

async function getFriends(userId, status, success, fail) {
  await local
    .get(`/member/friend/${userId}/${status}`)
    .then(success)
    .catch(fail);
}

async function getFriendRequest(userId, success, fail) {
  await local.get(`/member/requestFriend/${userId}`).then(success).catch(fail);
}

async function addFriend(userId, friend, success, fail) {
  await local
    .get(`/member/addFriend/${userId}/${friend}`)
    .then(success)
    .catch(fail);
}

async function acceptFriendRequest(userId, friend, success, fail) {
  await local
    .get(`/member/acceptFriendRequest/${userId}/${friend}`)
    .then(success)
    .catch(fail);
}

export {
  userConfirm,
  findById,
  tokenRegeneration,
  logout,
  regist,
  update,
  findUser,
  getFriends,
  addFriend,
  getFriendRequest,
  acceptFriendRequest,
  deleteById,
};
