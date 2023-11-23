// localAxios가 함수인데 함수가 넘어온거임.
import { localAxios } from "@/util/http-commons";

// local은 localAxios의 객체를 받음
const local = localAxios();

const url = "/plan";

function listPlan(userId, success, fail) {
  console.log("userId", userId);
  local.get(`${url}/list/${userId}`).then(success).catch(fail);
}

function listJoinPlace(planId, success, fail) {
  local.get(`${url}/placeList/${planId}`).then(success).catch(fail);
}

function writePlan(param, success, fail) {
  console.log(param);
  local.post(`${url}/write`, param).then(success).catch(fail);
}

function getFriends(planId, success, fail) {
  local.get(`${url}/friend/${planId}`).then(success).catch(fail);
}



export { listPlan, writePlan, listJoinPlace, getFriends };
