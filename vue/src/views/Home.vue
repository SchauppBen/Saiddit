<template>
  <div class="home">
    <div id="body">
      <posts id="post-list" />
    </div>

    <!-- ACTIVE FORUMS ON THE RIGHT SIDE -->

    <!-- POSTS COMPONENT -->
  </div>
</template>

<script>
import posts from "../components/Posts.vue";
export default {
  name: "home",
  components: { posts },
};
</script>

<style scoped>
#body {
  display: flex;
}
#post-list {
  justify-content: center;
  width: 100%;
}
</style>
