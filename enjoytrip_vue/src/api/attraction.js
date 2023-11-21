// localAxios가 함수인데 함수가 넘어온거임.
import { localAxios } from "@/util/http-commons";

// local은 localAxios의 객체를 받음
const local = localAxios();

const url = "/map";

function listAttraction(success, fail) {
  local.get(`${url}/mapList`).then(success).catch(fail);
}

function searchAttraction(param, success, fail) {
  local.get(`${url}/mapSearch`, { params: param }).then(success).catch(fail);
}

function searchAttractionId(param, success, fail) {
  console.log("Param" + param);
  local.get(`${url}/mapSearchId`, { params: param }).then(success).catch(fail);
}

function listSido(success, fail){
  local.get(`${url}/sidoList`).then(success).catch(fail);
}

function listGugun(param, success, fail){
  local.get(`${url}/gugunList`, {params: param}).then(success).catch(fail);
}

export { listAttraction, searchAttraction, searchAttractionId, listSido, listGugun};
