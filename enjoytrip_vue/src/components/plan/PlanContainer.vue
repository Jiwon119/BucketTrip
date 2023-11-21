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
      getAttrInfo();
    },
    (error) => {
      console.log(error);
    }
  );

}

const getAttrInfo = () => {
  plans.value.forEach(element => {
    listJoinPlace(
      element.id,
      ({ data }) => {
        element.attrInfo = data.planList;
      },
      (error) => {
        console.log(error);
      }
    );
  });

  console.log(plans.value);
}



const selected = ref({})


</script>

<template>
  <div class="row">
    <div class="col-6">
      <planMap />
    </div>
    <div class="col-6">
      <planList :planList="plans" />
    </div>
  </div>
</template>

<style scoped></style>