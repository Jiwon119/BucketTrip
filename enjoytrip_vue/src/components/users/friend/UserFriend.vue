<script setup>
import { defineProps } from 'vue';

const props = defineProps({ info: Object });
const friendCount = 3; // 수정할 프로필의 개수

const friendProfiles = Array.from({ length: friendCount }, () => props.info);
</script>

<template>
  <div class="friend-card">
    <div v-for="friend in friendProfiles" :key="friend.id" class="friend-profile">
      <img :src="friend.profilePicture" :alt="'프로필 이미지 - ' + friend.name" class="friend-profile-picture" />
      <h5>{{ friend.name }}</h5>
      <p>아이디: {{ friend.id }}</p>
    </div>
  </div>
</template>

<style scoped>
.friend-card {
  display: flex;
  border: 1px solid #ddd;
  padding: 10px;
  margin: 10px;
}

.friend-profile {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-right: 10px;
}

.friend-profile-picture {
  width: 70px;
  height: 70px;
  border-radius: 50%;
  border: solid black 1px;
}

.friend-info {
  display: flex;
  flex-direction: column;
}
</style>
