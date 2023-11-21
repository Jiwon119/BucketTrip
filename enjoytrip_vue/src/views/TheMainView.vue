<script setup>
import Tiptap from '@/components/common/Tiptap.vue';
import { ref } from 'vue';

const imgSrc = ref('http://tong.visitkorea.or.kr/cms/resource/70/608470_image2_1.jpg')
const title = ref("제목")
const content = ref("내용")


</script>

<template>
  <div>Main 화면 입니다.</div>
  <img src="https://i.imgur.com/ZLg4ojd.png" />
  <Tiptap v-model="content" />
  <div class="content">
    <h3>Content</h3>
    <pre><code>{{ content }}</code></pre>
  </div>
</template>

<style lang="scss">
/* Basic editor styles */
.tiptap {
  >*+* {
    margin-top: 0.75em;
  }

  code {
    background-color: rgba(#616161, 0.1);
    color: #616161;
  }
}

.content {
  padding: 1rem 0 0;

  h3 {
    margin: 1rem 0 0.5rem;
  }

  pre {
    border-radius: 5px;
    color: #333;
  }

  code {
    display: block;
    white-space: pre-wrap;
    font-size: 0.8rem;
    padding: 0.75rem 1rem;
    background-color: #e9ecef;
    color: #495057;
  }
}
</style>