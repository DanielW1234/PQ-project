<template>
  <div>
    {{data.a}}
  </div>
  <div>
    <input v-model="data.a"/>
    <span v-if="data.a == '1111'">111111</span>
  </div>
</template>


<script setup>
import {reactive, ref} from 'vue';
const data = reactive({
    a : "1111",
    b : "2222",
})
</script>

