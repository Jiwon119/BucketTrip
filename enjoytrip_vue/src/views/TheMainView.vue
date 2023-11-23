<script setup>
import { ref } from 'vue'

const travelPlan = ref([
  {
    destination: 'Paris, France',
    date: '2023-12-01',
    description: 'Explore the beautiful city of lights!',
    image: 'https://source.unsplash.com/800x600/?paris,city',
  },
  {
    destination: 'Tokyo, Japan',
    date: '2023-12-10',
    description: 'Discover the unique blend of tradition and modernity.',
    image: 'https://source.unsplash.com/800x600/?tokyo,city',
  },
  {
    destination: 'New York City, USA',
    date: '2023-12-20',
    description: 'Experience the energy of the city that never sleeps.',
    image: 'https://source.unsplash.com/800x600/?new-york,city',
  },
])
const backgroundImage = ref('https://img.freepik.com/free-vector/flat-design-of-travel-landing-page_23-2149131717.jpg?w=1380&t=st=1700727058~exp=1700727658~hmac=3aa9038d39ec7d17142cceda97f30139e2878b9b3788116addd7b0b298da0ccc')

const logo = 'Your Logo';
const description = '나만의 여행지를 찾고 나만의 버킷리스트를 만들어<br> 여행다니자! <br> 나의 버킷리스트를 친구와 공유하고 일기를 공유해요'
const travelCards = [
  {
    destination: 'Paris, France',
    image: 'https://source.unsplash.com/800x600/?paris,city',
  },
  {
    destination: 'Tokyo, Japan',
    image: 'https://source.unsplash.com/800x600/?tokyo,city',
  },
  {
    destination: 'New York City, USA',
    image: 'https://source.unsplash.com/800x600/?new-york,city',
  },
  {
    destination: 'Barcelona, Spain',
    image: 'https://source.unsplash.com/800x600/?barcelona,city',
  },
];

</script>


<template>
  <div class="main-container">
    <div class="left-section">
      <img src="@/assets/main.png" alt="Background" class="background-image">
      <div class="left-content">
        <img src="@/assets/logo_white.png" alt="Logo" class="logo">
        <p class="description" v-html="description"></p>
      </div>
    </div>
    <div class="right-section">
      <div v-for="card in travelCards" :key="card.destination" class="card">
        <img :src="card.image" alt="Travel Destination" class="card-img-top">
        <div class="card-body">
          <h5 class="card-title">{{ card.destination }}</h5>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
/* styles.css */

body,
html {
  height: 100%;
  margin: 0;
}

.main-container {
  display: flex;
  height: 100%;
}

.left-section {
  flex: 1;
  position: relative;
  overflow: hidden;
}

.background-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  position: absolute;
  top: 0;
  left: 0;
  opacity: 0.7;
  /* Adjust the opacity as needed */
}

.left-content {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  color: #fff;
}

.logo {
  width: 450px;
  height: auto;
  margin-bottom: 20px;
}

.description {
  font-size: 20px;
  line-height: 1.5;
  font-weight: bold;
}

.right-section {
  flex: 1;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: center;
  padding: 20px;
}

.card {
  width: calc(50% - 20px);
  margin-bottom: 20px;
}

.card-img-top {
  width: 100%;
  height: auto;
}
</style>
