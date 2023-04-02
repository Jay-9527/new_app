<script setup>
import HelloWorld from './components/HelloWorld.vue'
import Login from './views/Login.vue'
</script>

<template>
  <!-- haeder -->
  <!-- content -->
  <div class="">
    <RouterView />
  </div>
  <!-- footer -->
  <div class="footer"></div>
</template>
<style>
#app {
  background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
  background-size: 100% 100%;
  -webkit-animation: Gradient 15s ease infinite;
  animation: Gradient 15s ease infinite;
}

@-webkit-keyframes Gradient {
  0% {
    background-position: 0% 50%;
  }

  50% {
    background-position: 100% 50%;
  }

  100% {
    background-position: 0% 50%;
  }
}

@keyframes Gradient {
  0% {
    background-position: 0% 50%;
  }

  50% {
    background-position: 100% 50%;
  }

  100% {
    background-position: 0% 50%;
  }
}

#app,
html {
  height: 100%;
  overflow: hidden;
}
</style>