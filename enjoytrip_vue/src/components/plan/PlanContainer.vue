<script setup>
import { ref, onMounted, watch } from "vue";
import { storeToRefs } from "pinia";
import { listPlan, listJoinPlace } from "@/api/plan";
import { searchAttractionId } from "@/api/attraction";
import { useMemberStore } from "@/stores/member";
import { useRouter } from "vue-router";

import planList from "./PlanList.vue";
import planMap from "./PlanMap.vue";

const router = useRouter();
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const plans = ref([{
  id: "",
  userId: "",
  title: "",
  content: "",
  creationDate: "",
  attrInfo: {},
  attrDesc: {}
}]);

onMounted(() => {
  getPlanList();
});


const getPlanList = () => {
  listPlan(
    // userInfo.value.id,
    "ssafy",
    ({ data }) => {
      plans.value = data.planList

      plans.value.forEach(element => {
        getAttrInfo(element.id);
      })
    },
    (error) => {
      console.log(error);
    }
  );

}

const getAttrInfo = (id) => {
  listJoinPlace(
    id,
    ({ data }) => {
      console.log("data.listJoinPlace------", data)
      plans.value.push(data)
    },
    (error) => {
      console.log(error);
    }
  );
}



const selected = ref({})


</script>

<template>
  <div class="row">
    <div class="col-8">
      <!-- <planMap :planList="plans" :selectedList="selected" /> -->
    </div>
    <div class="col-4">
      <planList :planList="plans" />
    </div>
  </div>
</template>

<style scoped></style>