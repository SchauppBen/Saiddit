<template>
  <div class="home">
    <div id ="body">
      <NavBar id="nav"/>
      <posts />
    </div>
    
    <!-- ACTIVE FORUMS ON THE RIGHT SIDE -->
    
    <!-- POSTS COMPONENT -->
  </div>
</template>

<script>
import NavBar from "../components/NavBar.vue";
import posts from "../components/Posts.vue";
export default {
  name: "home",
  components: { posts, NavBar },
};
</script>

<style scoped>
  #body {
    display: flex
    
  }

</style>
  

