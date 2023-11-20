// localAxios가 함수인데 함수가 넘어온거임.
import { localAxios } from "@/util/http-commons";

// local은 localAxios의 객체를 받음
const local = localAxios();

const url = "/plan";

function listPlan(param, success, fail){
    local.get(`${url}/list/${param}`).then(success).catch(fail);
}

function writePlan(param, success, fail) {
    console.log(param);
    local.post(`${url}/write`, param).then(success).catch(fail);
}
  
export { listPlan, writePlan };
  