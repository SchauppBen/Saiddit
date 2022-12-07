<template>
  <div id="Posts">
    <Post v-for="post in posts" :key="post.id" :post="post" />
  </div>
</template>

<script>
import Post from "../components/Post.vue";
export default {
  name: "posts-list",
  components: { Post },
  computed: {
    posts() {
      return this.$store.state.posts;
    },
  },
};
</script>

<style></style>
