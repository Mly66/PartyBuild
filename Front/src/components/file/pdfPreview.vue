<template>
  <vue-office-pdf :src="pdf" @rendered="rendered" />
</template>

<script>
//引入VueOfficePdf组件
import VueOfficePdf from "@vue-office/pdf";

export default {
  components: {
    VueOfficePdf,
  },
  data() {
    return {
      pdf: "", 
    };
  },
  methods: {
    rendered() {
    },
  },
};
</script>
