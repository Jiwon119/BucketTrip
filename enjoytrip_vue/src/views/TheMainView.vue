<script setup>


import post from "@/components/main/post.vue";
import intro from "@/components/main/intro.vue";

</script>


<template>
  <div>
    <div class="row">
      <div class="col-5 left">
        <div class="main-title">
          Travel to make <br>
          memories all around <br>
          the world <br>
        </div>
        <div class="main-text">
          당신의 여행 버킷 리스트를<br>
          버킷트립과 함께하세요
        </div>
        <button class="btn btn-outline-secondary fs-4 ps-5 pe-5 p-2 main-btn">
          <font-awesome-icon icon="campground" />
          Start

          Bucket Trip</button>
      </div>
      <div class="col-7 right">
        <div class="row p-3">
          <div class="col-4">
            <label class="main-label">라벨</label>
          </div>
          <div class="col-8">
            <img class="main-img-1" src="@/assets/main_1.jpg" />
          </div>
        </div>
        <div class="row p-3">
          <!-- <img class="main-img-1" src="@/assets/main_2.jpg" /> -->
          <div class="col">
            <img class="main-img-3" src="@/assets/main_4.jpg" />
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<style>
.left {
  text-align: left;
}

.main-title {
  margin: 30% 10% 10px 10%;
  font-size: 45px;
  font-weight: bold;
}

.main-label {
  background-color: white;
  width: 100%;
  height: 100%
}

.main-text {
  margin: 0 10%;
  font-size: 20px;
}

.main-btn {
  margin: 30px 30% 10px 10%;
}

.main-img-1 {
  width: 100%;
  border-radius: 10px;
}

.main-img-3 {
  width: 100%;
  border-radius: 10px;
}
</style>
