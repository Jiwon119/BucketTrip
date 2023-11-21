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
    userInfo.value.id,
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

const MapListId = ref(0);

const clickPlan = (id) => {
  MapListId.value = id
}

const selected = ref({})


</script>

<template>
  <div class="row">
    <div class="col-6">
      <planMap :planList="plans[MapListId].attrInfo" />
    </div>
    <div class="col-6">
      <planList :plan-list="plans" v-on:on-click-plan="clickPlan" />
    </div>
  </div>
</template>

<style scoped></style>