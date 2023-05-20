<template>
    <div>
           <!-- 计算属性自动解包，无需.value -->
      <span>{{ publishedBooksMessage }}</span>
    </div>

    <div>
      <li v-for="item in items " :items="items" :key="item">
          {{item.message}}
      </li>
    </div>
</template>
<!-- <script lang="ts">
import {defineComponent} from 'vue';
export default defineComponent({})
</script> -->

<script setup>
import { reactive, computed ,ref} from 'vue'
const author = reactive({
  name: 'John Doe',
  books: [
    'Vue 2 - Advanced Guide',
    'Vue 3 - Basic Guide',
    'Vue 4 - The Mystery'
  ]
})

const items = ref([{ message: 'Foo' }, { message: 'Bar' }])

// 一个计算属性 ref
const publishedBooksMessage = computed(() => {
  return author.books.length > 0 ? 'Yes' : 'No'
})

console.log("publishedBooksMessage: "+publishedBooksMessage.value);

</script>
<style scoped>
</style>