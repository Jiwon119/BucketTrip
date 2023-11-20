// localAxios가 함수인데 함수가 넘어온거임.
import { localAxios } from "@/util/http-commons";

// local은 localAxios의 객체를 받음
const local = localAxios();

const url = "/favorite";

function listFavorite(memberId, success, fail) {
  local.get(`${url}/list/${memberId}`).then(success).catch(fail);
}

function addFavorite(param, isFavorite, success, fail) {
  local
    .post(`${url}/favoriteAdd`, JSON.stringify(param), {
      params: { isFavorite: isFavorite },
    })
    .then(success)
    .catch(fail);
}

function isFavorited(memberId, destinationId, success, fail) {
  // console.log("gdgdgdgdgdgd" + memberId + " " + destinationId + "@@@@@@");
  local
    .get(`${url}/isFavorited/`, {
      params: { userId: memberId, destinationId: destinationId },
    })
    .then(success)
    .catch(fail);
}

export { listFavorite, addFavorite, isFavorited };
