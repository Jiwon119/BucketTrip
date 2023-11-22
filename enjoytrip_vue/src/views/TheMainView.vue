<script setup>
import Tiptap from '@/components/common/Tiptap.vue';
import { ref } from 'vue';
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css';
import axios from 'axios'

const imgSrc = ref('http://tong.visitkorea.or.kr/cms/resource/70/608470_image2_1.jpg')
const title = ref("제목")
const content = ref("내용")

const editor = ref(null);

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
    axios.post("https://api.imgbb.com/1/upload?key=8239173c3bd0edf4cc0718df6b8b1874", bodyData)
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

</script>


<template>
  <div>Main 화면 입니다.</div>
  <div>
    <QuillEditor theme="snow" :toolbar="toolbarOptions" id="editor" ref="editor" @editor-change="Change" />
  </div>
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
