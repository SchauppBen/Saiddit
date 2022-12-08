<template>
  <div class="home">
    <div id="body">
      <posts id="post-list" />
      <active-forums id="forums-list" />
    </div>

    <!-- ACTIVE FORUMS ON THE RIGHT SIDE -->

    <!-- POSTS COMPONENT -->
  </div>
</template>

<script>
import ActiveForums from "../components/ActiveForums.vue";
import posts from "../components/Posts.vue";
export default {
  name: "home",
  components: { posts, ActiveForums },
};
</script>

<style scoped>
#body {
  display: flex;
}
#post-list {
  justify-content: center;
  width: 85%;
}
#forums-list {
  background-color: skyblue;
  width: 200px;
}
</style>
