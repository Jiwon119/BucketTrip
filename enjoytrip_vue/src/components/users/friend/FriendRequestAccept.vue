<script setup>
import { ref } from 'vue';

const friendRequests = ref([
    {
        name: '친구1의 이름',
        profilePicture: 'https://i.ibb.co/Vm3Hv1C/ssafy-logo.png',
        id: '친구1의 아이디',
    },
    {
        name: '친구2의 이름',
        profilePicture: 'https://i.ibb.co/Vm3Hv1C/ssafy-logo.png',
        id: '친구2의 아이디',
    },
    {
        name: '친구3의 이름',
        profilePicture: 'https://i.ibb.co/Vm3Hv1C/ssafy-logo.png',
        id: '친구3의 아이디',
    },
    {
        name: '친구4의 이름',
        profilePicture: 'https://i.ibb.co/Vm3Hv1C/ssafy-logo.png',
        id: '친구4의 아이디',
    },
    {
        name: '친구5의 이름',
        profilePicture: 'https://i.ibb.co/Vm3Hv1C/ssafy-logo.png',
        id: '친구5의 아이디',
    },
    {
        name: '친구6의 이름',
        profilePicture: 'https://i.ibb.co/Vm3Hv1C/ssafy-logo.png',
        id: '친구6의 아이디',
    },
]);

const acceptRequest = (friend) => {
    alert(`친구 요청을 수락했습니다. 요청을 보낸 사람: ${friend.name}`);
};
</script>

<template>
    <div class="friend-request-accept">
        <div v-for="friend in friendRequests" :key="friend.id" class="friend-request-item">
            <div class="friend-request-info">
                <img :src="friend.profilePicture" :alt="'프로필 이미지 - ' + friend.name" class="friend-request-picture" />
                <h5>{{ friend.name }}</h5>
                <p>아이디: {{ friend.id }}</p>
            </div>
            <button @click="() => acceptRequest(friend)" class="btn btn-success">수락</button>
        </div>
    </div>
</template>

<style scoped>
.friend-request-accept {
    display: flex;
    overflow-x: auto;
    border: 1px solid #ddd;
    padding: 20px;
    margin: 10px;
    width: 100%;
}

.friend-request-item {
    flex: 0 0 auto;
    box-sizing: border-box;
    margin-right: 15px;
    min-width: 200px;
}

.friend-request-info {
    display: block;
    align-items: center;
}

.friend-request-picture {
    width: 70px;
    height: 70px;
    border-radius: 50%;
    border: rgb(203, 203, 203) solid 1px;
    margin-right: 10px;
}

.friend-request-details {
    display: flex;
    flex-direction: column;
}

.btn-success {
    margin-top: 10px;
}
</style>
