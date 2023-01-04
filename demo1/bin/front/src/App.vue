<template>
  <div id="app">
    <EmpList/>
  </div>
</template>

<script>

import EmpList from './components/EmpList.vue';
export default {
  name: 'App',
  components: {
    EmpList
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
