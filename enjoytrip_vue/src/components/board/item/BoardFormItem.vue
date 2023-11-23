<script setup>
import { ref, watch, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { registArticle, modifyArticle, detailArticle } from "@/api/board";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { QuillEditor } from '@vueup/vue-quill'
import axios from 'axios'

const router = useRouter();
const route = useRoute();

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const props = defineProps({ type: String });

const isUseId = ref(false);

const article = ref({
  articleNo: 0,
  destinationId: 0,
  userId: "ssafy",
  userName: "김싸피",
  subject: "1234",
  content: "123",
  likes: 0,
  hit: 0,
  registerTime: "",
  fileInfos: null,
});


if (props.type === "modify") {
  let { articleno } = route.params;
  // API 호출
  isUseId.value = true;

  detailArticle(articleno, ({ data }) => {
    console.log("data", data);
    article.value = data.article;
    console.log(data)
    editor.value.getQuill().pasteHTML(article.value.content);
  },
    (error) => {
      console.log(error);
    })
  console.log(article)
}

onMounted(() => {
  article.value.destinationId = history.state.contentId;
  article.value.userId = userInfo.value.id;
  article.value.userName = userInfo.value.name;
});

const subjectErrMsg = ref("");
const contentErrMsg = ref("");
watch(
  () => article.value.subject,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 30) {
      subjectErrMsg.value = "제목을 확인해 주세요!!!";
    } else subjectErrMsg.value = "";
  },
  { immediate: true }
);
watch(
  () => article.value.content,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 500) {
      contentErrMsg.value = "내용을 확인해 주세요!!!";
    } else contentErrMsg.value = "";
  },
  { immediate: true }
);

function onSubmit() {

  if (subjectErrMsg.value) {
    alert(subjectErrMsg.value);
  } else if (contentErrMsg.value) {
    alert(contentErrMsg.value);
  } else {
    props.type === "regist" ? writeArticle() : updateArticle();
  }
}


function writeArticle() {
  console.log("글등록하자!!", article.value);
  // registArticle(article.value, upfile, (response) => {
  registArticle(article.value, (response) => {
    console.log(response);
  },
    (error) => {
      console.log(error);
    })
  moveList();
}

function updateArticle() {
  console.log(article.value.articleNo + "번글 수정하자!!", article.value);
  // API 호출
  modifyArticle(article.value, () => {
    console.log(article.value);
  },
    (error) => {
      console.log(error);
    })
  moveList();
}

function moveList() {
  router.push({ name: "article-list" });
}

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
    ,
  ],  // Selector for toolbar container
  handlers: {
    'image': handleImage
  }


};

const Change = () => {
  article.value.content = editor.value.getHTML();
  console.log("article.value", article.value);
}


</script>

<template>
  <form @submit.prevent="onSubmit">
    <!-- <div class="mb-3">
                                                <label for="subject" class="form-label">제목 : </label>
                                                <input type="text" class="form-control" v-model="article.subject" placeholder="제목..." />
                                              </div>
                                              <div class="mb-3">
                                                <label for="content" class="form-label">내용 : </label>
                                                <textarea class="form-control" v-model="article.content" rows="10"></textarea>
                                              </div> -->
    <div>
      제목: <input type="text" class="form-control" v-model="article.subject">
      장소: <input type="text" class="form-control" v-model="article.destinationId">
      <input type="button" class="btn btn-outline-secondary" value="버튼" @click="onRegistArticle" />
      <QuillEditor theme="snow" :toolbar="toolbarOptions" id="editor" ref="editor" @editor-change="Change" />
    </div>

    <!-- <div class="mb-3">
                                                          <label for="upfile" class="form-label">파일:</label>
                                                          <input type="file" class="form-control border" @change="upload(this)" id="upfile" name="upfile" multiple="multiple">
                                                        </div> -->

    <div class="col-auto text-center">
      <button type="submit" class="btn btn-outline-primary mb-3" v-if="type === 'regist'" @click="writeArticle">
        글작성
      </button>
      <button type="submit" class="btn btn-outline-success mb-3" v-else @click="updateArticle">글수정</button>
      <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="moveList">
        목록으로이동...
      </button>
    </div>
  </form>
</template>

<style scoped></style>
