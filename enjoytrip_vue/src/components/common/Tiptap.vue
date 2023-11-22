<template>
  <div v-if="editor">
    <button class="btn btn" @click="editor.chain().focus().toggleBold().run()"
      :disabled="!editor.can().chain().focus().toggleBold().run()" :class="{ 'is-active': editor.isActive('bold') }">
      bold
    </button>
    <button class="btn btn" @click="editor.chain().focus().toggleItalic().run()"
      :disabled="!editor.can().chain().focus().toggleItalic().run()" :class="{ 'is-active': editor.isActive('italic') }">
      italic
    </button>
    <button class="btn btn" @click="editor.chain().focus().toggleStrike().run()"
      :disabled="!editor.can().chain().focus().toggleStrike().run()" :class="{ 'is-active': editor.isActive('strike') }">
      strike
    </button>
    <button class="btn btn" @click="editor.chain().focus().toggleCode().run()"
      :disabled="!editor.can().chain().focus().toggleCode().run()" :class="{ 'is-active': editor.isActive('code') }">
      code
    </button>
    <button class="btn btn" @click="editor.chain().focus().unsetAllMarks().run()">
      clear marks
    </button>
    <button class="btn btn" @click="editor.chain().focus().clearNodes().run()">
      clear nodes
    </button>
    <button class="btn btn" @click="editor.chain().focus().setParagraph().run()"
      :class="{ 'is-active': editor.isActive('paragraph') }">
      paragraph
    </button>
    <button class="btn btn" @click="editor.chain().focus().toggleHeading({ level: 1 }).run()"
      :class="{ 'is-active': editor.isActive('heading', { level: 1 }) }">
      h1
    </button>
    <button class="btn btn" @click="editor.chain().focus().toggleHeading({ level: 2 }).run()"
      :class="{ 'is-active': editor.isActive('heading', { level: 2 }) }">
      h2
    </button>
    <button class="btn btn" @click="editor.chain().focus().toggleHeading({ level: 3 }).run()"
      :class="{ 'is-active': editor.isActive('heading', { level: 3 }) }">
      h3
    </button>
    <button class="btn btn" @click="editor.chain().focus().toggleHeading({ level: 4 }).run()"
      :class="{ 'is-active': editor.isActive('heading', { level: 4 }) }">
      h4
    </button>
    <button class="btn btn" @click="editor.chain().focus().toggleHeading({ level: 5 }).run()"
      :class="{ 'is-active': editor.isActive('heading', { level: 5 }) }">
      h5
    </button>
    <button class="btn btn" @click="editor.chain().focus().toggleHeading({ level: 6 }).run()"
      :class="{ 'is-active': editor.isActive('heading', { level: 6 }) }">
      h6
    </button>
    <button class="btn btn" @click="editor.chain().focus().toggleBulletList().run()"
      :class="{ 'is-active': editor.isActive('bulletList') }">
      bullet list
    </button>
    <button class="btn btn" @click="editor.chain().focus().toggleOrderedList().run()"
      :class="{ 'is-active': editor.isActive('orderedList') }">
      ordered list
    </button>
    <button class="btn btn" @click="editor.chain().focus().toggleCodeBlock().run()"
      :class="{ 'is-active': editor.isActive('codeBlock') }">
      code block
    </button>
    <button class="btn btn" @click="editor.chain().focus().toggleBlockquote().run()"
      :class="{ 'is-active': editor.isActive('blockquote') }">
      blockquote
    </button>
    <button class="btn btn" @click="editor.chain().focus().setHorizontalRule().run()">
      horizontal rule
    </button>
    <button class="btn btn" @click="editor.chain().focus().setHardBreak().run()">
      hard break
    </button>
    <button class="btn btn" @click="editor.chain().focus().undo().run()"
      :disabled="!editor.can().chain().focus().undo().run()">
      undo
    </button>
    <button class="btn btn" @click="editor.chain().focus().redo().run()"
      :disabled="!editor.can().chain().focus().redo().run()">
      redo
    </button>

    <input type="file" id="upload_image">
    <button @click="uploadImageTest">upload</button>
    <!-- <button class="btn btn" @click="uploadImageTest">add image from URL</button> -->
  </div>
  <editor-content :editor="editor" />
</template>

<script setup>
import { watch, ref } from 'vue'
import { useEditor, EditorContent } from '@tiptap/vue-3'
import StarterKit from '@tiptap/starter-kit'
import Document from '@tiptap/extension-document'
import Dropcursor from '@tiptap/extension-dropcursor'
import Image from '@tiptap/extension-image'
import Paragraph from '@tiptap/extension-paragraph'
import Text from '@tiptap/extension-text'
import axios from 'axios'

const props = defineProps({
  modelValue: {
    type: String,
    default: ``
  },
});

const emit = defineEmits(["update:modelValue"])

const editor = ref(useEditor({
  content: `<p>This is a basic example of implementing images. Drag to re-order.</p>
        <img src="https://source.unsplash.com/8xznAGy4HcY/800x400" />
        <img src="https://source.unsplash.com/K9QHL52rE2k/800x400" />`,
  extensions: [
    StarterKit,
    Document,
    Paragraph,
    Text,
    Image,
    Dropcursor,],
  onUpdate: () => {
    emit('update:modelValue', editor.value.getHTML());
  }
})
)

watch(
  () => props.modelValue,
  (value) => {
    const isSame = editor.value.getHTML() === value;
    if (isSame) {
      return;
    }
    editor.value.commands.setContent(value, false)
  }
)

const addImage = () => {
  const url = window.prompt('URL')

  if (url) {
    editor.value.chain().focus().setImage({ src: url }).run()
  }
}

const uploadImageTest = () => {
  var bodyData = new FormData();
  var imageFile = document.querySelector("#upload_image").files[0];
  bodyData.append("image", imageFile);
  axios.post("https://api.imgbb.com/1/upload?key=8239173c3bd0edf4cc0718df6b8b1874", bodyData)
  .then(res => {
    console.log(res.data.data.display_url);
    const url = res.data.data.display_url;
    editor.value.chain().focus().setImage({src: url}).run();
    console.log("Add img on Editor");
  })
  .catch((error) => {
    console.log(error);
  })
  // fetch("https://api.imgur.com/3/image", {
  //   method: "POST",
  //   headers: {
  //     "user-agent": "curl/7.84.0",
  //     Authorization: `Client-ID a96cb206061dfee`,
  //     Accept: "application/json",
  //   },
  //   body: bodyData,
  // }).then(function (res) {
  //   return res.json();
  // }).then(function (json) {
  //   const url = json.data.link;
  //   editor.value.chain().focus().setImage({ src: url }).run()
  //   console.log(json.data.link);
  // });

  // 

  // if (url) {
  //   editor.value.chain().focus().setImage({ src: url }).run()
  // }
}
</script>

<style lang="scss" scope>
.icon {
  position: relative;
  display: inline-block;
  vertical-align: middle;
  width: 0.8rem;
  height: 0.8rem;
  margin: 0 .3rem;
  top: -.05rem;
  fill: currentColor;

  // &.has-align-fix {
  // 	top: -.1rem;
  // }

  &__svg {
    display: inline-block;
    vertical-align: top;
    width: 100%;
    height: 100%;
  }

  &:first-child {
    margin-left: 0;
  }

  &:last-child {
    margin-right: 0;
  }

}

// svg sprite
body>svg,
.icon use>svg,
symbol {

  path,
  rect,
  circle,
  g {
    fill: currentColor;
    stroke: none;
  }

  *[d="M0 0h24v24H0z"] {
    display: none;
  }
}
</style>