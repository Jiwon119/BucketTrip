<script setup>
import { ref, onMounted } from "vue";
// import { useRouter } from "vue-router";
import { listAttraction } from "@/api/attraction";
import VKakaoMap from "@/components/common/VKakaoMap.vue";

const attractionData = ref([]);
const selectCampsite = ref({
  contentId: '',
  mapX : '',
  mapY : '',
  title: '',
  firstImage: '',
  addr: '',
});

onMounted(() => {
  getAttractionList();
});

const getAttractionList = () => {
  // API 호출
  listAttraction(
    ({ data }) => {
      console.log(data);
        data.forEach(element => {
          attractionData.value.push({
            id: element.contentId,
            title: element.title,
            content: element.addr1,
            mapY: element.latitude,
            mapX: element.longitude
          });
        });
        console.log(attractionData.value);

    },
    (error) => {
      console.log(error);
    }
  );
  console.log(attractionData);
};

const viewcampsite = (camp) => {
  // selectCampsite.value = camp;
  selectCampsite.value.mapX = camp.longitude;
  selectCampsite.value.mapY = camp.latitude;
  console.log(selectCampsite.value);
};

</script>

<template>
    <div>
        <h2>캠핑장 정보</h2>
        <div class="row">
        <div class="col-8">
            <VKakaoMap :campingData="attractionData" :selectCampsite="selectCampsite" />
        </div>
        <div class="col-4">
            <table class="table table-hover">
            <thead>
                <tr class="text-center">
                <th scope="col">캠핑장 이름</th>
                <!-- <th scope="col">소개</th> -->
                <!-- <th scope="col">캠핑장 소개</th> -->
                <th scope="col">위치</th>
                <!-- <th scope="col">시설</th> -->
                <!-- <th scope="col">이미지</th> -->
                </tr>
            </thead>
            <tbody>
                <tr class="text-center" v-for="camp in attractionData" :key="camp.contentId" @click="viewcampsite(camp)">
                <td>{{ camp.title }}</td>
                <!-- <td>{{ camp.lineIntro }}</td> -->
                <!-- <td v-if="camp.featureNm == ''">{{ camp.lineIntro }}</td> -->
                <!-- <td v-else>{{ camp.featureNm }}</td> -->
                <td>{{ camp.content }}</td>
                <!-- <td>{{ camp.sbrsEtc }}</td> -->
                <!-- <td><img :src="camp.firstImageUrl" style="width: 180px;"> </td> -->
                </tr>
            </tbody>
            </table>
        </div>
        </div>
    </div>
</template>

<style scoped>

</style>