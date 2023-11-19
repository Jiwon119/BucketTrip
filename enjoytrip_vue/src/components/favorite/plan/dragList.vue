<script>
import draggable from "vuedraggable";
import VCard from "@/components/common/VCard.vue";

export default {
  props: {
    list: Array
  },
  components: {
    draggable,
    VCard
  },
  data() {
    return {
      enabled: true,
      list: this.list,
      dragging: false
    };
  }
};
</script>

<template>
  <draggable :list="list" :disabled="!enabled" item-key="name" class="list-group" @start="dragging = true"
    @end="dragging = false">
    <template #item="{ element }">
      <div class="list-group-item" :class="{ 'not-draggable': !enabled }">
        <img :src="element.img">
        <h5>{{ element.title }}</h5>
        <p>{{ element.content }}</p>
        <button class="btn btn-outline-secondary"> 상세 페이지</button>
      </div>
    </template>
  </draggable>
</template>


<style scoped>
.buttons {
  margin-top: 35px;
}

h5 {
  font-weight: bold;
}

.not-draggable {
  cursor: no-drop;
}

img {
  width: 100px;
}
</style>