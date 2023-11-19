// localAxios가 함수인데 함수가 넘어온거임.
import { localAxios } from "@/util/http-commons";

// local은 localAxios의 객체를 받음
const local = localAxios();

const url = "/favorite";

function listFavorite(memberId, success, fail) {
  local.get(`${url}/list/${memberId}`).then(success).catch(fail);
}

export { listFavorite };
