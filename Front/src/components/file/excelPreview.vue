<template>
  <vue-office-excel
    :src="`http://127.0.0.1:9011${path}`"
    @rendered="rendered"
  />
</template>

<script setup>
//引入VueOfficeExcel组件
import VueOfficeExcel from "@vue-office/excel";
//引入相干款式
import axios from "axios";
import "@vue-office/excel/lib/V3/index.css";
import { toRefs, reactive } from "vue";

const props = defineProps({
  path: String,
});

const excelFile = reactive(null);

const { path } = toRefs(props);

</script>
