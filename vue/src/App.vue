<template>
  
  <div id="app" class="has-background-grey-dark has-text-primary">
    
    <div id="nav">
      <Header />
    </div>
    <router-view />
  </div>
</template>

<script>
import Header from "./components/Header.vue";

export default {
  components: {Header}
}
</script>

