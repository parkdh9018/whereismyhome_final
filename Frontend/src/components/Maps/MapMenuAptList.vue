<template>
  <b-container>
    <b-row>
      <b-col
        class="border rounded text-center"
        cols="12"
        v-for="apt in aptlist"
        :key="apt.aptCode"
        >{{ apt.apartmentName }}</b-col
      >
      <b-col v-if="aptlist.length == 0"> 정보가 없습니다. </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  props: {
    aptlist: Array,
  },
};
</script>

<style>
</style>