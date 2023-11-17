<script setup>
import { ref, onMounted } from "vue";
// import { useRouter } from "vue-router";
import { listAttraction } from "@/api/attraction";
import VKakaoMap from "@/components/common/VKakaoMap.vue";

const attractionData = ref([]);
const selected = ref({
  contentId: '',
  mapX: '',
  mapY: '',
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
          mapX: element.longitude,
          img: element.firstImage
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


const viewMarker = (data) => {
  // selected.value = camp;
  selected.value = data;
};

</script>

<template>
  <div>
    <h2>캠핑장 정보</h2>
    <div class="row">
      <div class="col-8">
        <VKakaoMap :data="attractionData" :selected="selected" />
      </div>
      <div class="col-4">
        <table class="table table-hover">
          <thead>
            <tr class="text-center">
              <th scope="col">이름</th>
              <!-- <th scope="col">소개</th> -->
              <!-- <th scope="col">캠핑장 소개</th> -->
              <th scope="col">위치</th>
              <!-- <th scope="col">시설</th> -->
              <!-- <th scope="col">이미지</th> -->
            </tr>
          </thead>
          <tbody>
            <tr class="text-center" v-for="list in attractionData" :key="list.contentId" @click="viewMarker(list)">
              <td>{{ list.title }}</td>
              <!-- <td>{{ camp.lineIntro }}</td> -->
              <!-- <td v-if="camp.featureNm == ''">{{ camp.lineIntro }}</td> -->
              <!-- <td v-else>{{ camp.featureNm }}</td> -->
              <td>{{ list.content }}</td>
              <!-- <td>{{ camp.sbrsEtc }}</td> -->
              <!-- <td><img :src="camp.firstImageUrl" style="width: 180px;"> </td> -->
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped></style>