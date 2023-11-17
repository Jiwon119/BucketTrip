// localAxios가 함수인데 함수가 넘어온거임.
import { localAxios } from "@/util/http-commons";

// local은 localAxios의 객체를 받음
const local = localAxios();

const url = "/map";

function listAttraction(success, fail) {
  local.get(`${url}/mapList`).then(success).catch(fail);
}

export {
  listAttraction,
};
