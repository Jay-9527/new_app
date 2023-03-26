<script setup>
import HelloWorld from './components/HelloWorld.vue'
import Login from './views/Login.vue'
</script>

<template>
  <!-- haeder -->
  <h4></h4>
  <!-- content -->
  <div class="container">
    <RouterView />
  </div>
  <!-- footer -->
  <div class="footer"></div>
</template>
