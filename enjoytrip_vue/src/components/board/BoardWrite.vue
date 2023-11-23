<script setup>
import { ref, onMounted } from 'vue';
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css';
import axios from 'axios'
import { registArticle } from "@/api/board";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";

import { useRouter } from "vue-router";

import { searchAttractionId } from "@/api/attraction";

const router = useRouter();

const props = defineProps({ type: String, contentId: String });

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const attraction = ref({});

const param = ref({
  destinationId: "",
  userId: "ssafy",
  userName: "김싸피",
  subject: "",
  content: "",

})

onMounted(() => {
  param.value.destinationId = props.contentId;
  param.value.userId = userInfo.value.id;
  param.value.userName = userInfo.value.name;
  console.log("props.contentId", props.contentId);

  searchAttractionId(
    { id: props.contentId },
    ({ data }) => {
      console.log(data.title + "gdggddgd" + JSON.stringify(data));
      attraction.value = data;
    },
    (error) => {
      console.log(error);
    }
  );
});


const editor = ref(null);
const title = ref("");

const handleImage = () => {
  const input = document.createElement("input");
  input.setAttribute("type", "file");
  input.setAttribute("accept", "image/*");
  input.click();
  input.onchange = async () => {
    const file = input.files[0];

    var bodyData = new FormData();
    var imageFile = file
    bodyData.append("image", imageFile);
    axios.post(`https://api.imgbb.com/1/upload?key=${import.meta.env.VITE_IMGBB_API_KEY}`, bodyData)
      .then(res => {
        const url = res.data.data.display_url;
        const index = editor.value.getQuill().getSelection().index;
        editor.value.getQuill().setSelection(index, 1);
        editor.value.getQuill().insertEmbed(index, 'image', url);
      })
      .catch((error) => {
        console.log(error);
      })
  }
}

var toolbarOptions = {
  container: [
    ["bold", "italic", "underline", "strike"], // toggled buttons
    ["blockquote", "code-block"],

    // [{ header: 1 }, { header: 2 }], // custom button values
    [{ list: "ordered" }, { list: "bullet" }],
    // [{ script: "sub" }, { script: "super" }], // superscript/subscript
    [{ indent: "-1" }, { indent: "+1" }], // outdent/indent
    [{ direction: "rtl" }], // text direction

    [{ size: ["small", false, "large", "huge"] }], // custom dropdown
    [{ header: [1, 2, 3, 4, 5, 6, false] }],

    [{ color: [] }, { background: [] }], // dropdown with defaults from theme
    [{ font: [] }],
    [{ align: [] }],

    ["link", "image"],

    ["clean"], // remove formatting button

  ],  // Selector for toolbar container
  handlers: {
    'image': handleImage
  }


};

const onRegistArticle = () => {
  console.log("글쓰기");
  // API 호출
  registArticle(
    param.value,
    ({ data }) => {
      console.log(data);
    },
    (error) => {
      console.log(error);
    }
  );
  router.push("/board/list");
};

const Change = () => {
  param.value.content = editor.value.getHTML();
  console.log("param.value", param.value);
}


</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3">
          글쓰기
        </h2>
      </div>
    </div>
    <hr>
    <div class="row justify-content-center mt-3">
      <div class="col-lg-7">
        <!-- 장소 카드 -->
        <div class="card">
          <div class="row g-0">
            <!-- 이미지 -->
            <div class="col-md-4">
              <img v-if="attraction.firstImage" :src="attraction.firstImage" class="img-fluid rounded-start" alt="Attraction Image">
              <img v-else class="card-img" src="https://i.ibb.co/CWZysc0/mountains.png">
            </div>
            <!-- 제목 -->
            <div class="col-md-8">
              <div class="card-body">
                <p class="card-text">{{ attraction.title }}</p>
                <p class="card-text">{{ attraction.addr1 }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <!-- 제목 입력 -->
        <div class="row mb-3" style="margin-top: 20px;">
          <div class="col-md-1">
            <label for="subject">제목</label>
          </div>
          <div class="col-md-10">
            <input type="text" class="form-control" v-model="param.subject">
          </div>
          <div class="col-md-1">
            <input type="button" class="btn btn-outline-secondary" value="글쓰기" @click="onRegistArticle" />
          </div>
        </div>
      </div>
    </div>
    <div class="row justify-content-center mt-3">
      <div class="col-lg-10">
        <!-- 버튼 및 편집기 -->
        <QuillEditor theme="snow" :toolbar="toolbarOptions" id="editor" ref="editor" @editor-change="Change" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  margin-bottom: 150px;
}

input {
  /* margin-top: 150px; */
  margin-right: 50px;
}
.card-img {
  width: 178px;
  height: 118px;
  max-width: 100%;
  max-height: 100%;
  margin: 0 auto;
  display: block;
  position: relative;
}
</style>
