<template>
  <div class="col-6">
    <draggable :list="list" :disabled="!enabled" class="list-group" ghost-class="ghost">
      <template #item="{ element }" class="list-group-item" v-for="element in list" :key="element.name">
        {{ element.name }}
      </template>
    </draggable>
  </div>
</template>

<script>
import draggable from "vuedraggable";

export default {
  components: {
    draggable,
  },
  data() {
    return {
      enabled: true,
      list: [
        { name: "John", id: 0 },
        { name: "Joao", id: 1 },
        { name: "Jean", id: 2 }
      ],
      dragging: false,
    };
  }
};

</script>

<style scoped>
.buttons {
  margin-top: 35px;
}

.ghost {
  opacity: 0.5;
  background: #c8ebfb;
}
</style>
