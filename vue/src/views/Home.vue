<template>
  <div class="home">
    <Header />
    <!-- NAVIGATION BAR ON THE LEFT SIDE -->
    <!-- ACTIVE FORUMS ON THE RIGHT SIDE -->
    <posts />
    <!-- POSTS COMPONENT -->
  </div>
</template>

<script>
import Header from "../components/Header.vue";
import posts from "../components/Posts.vue";
export default {
  name: "home",
  components: { posts, Header },
};
</script>
