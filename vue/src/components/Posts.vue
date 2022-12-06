<template>
  <div id="Posts">
      <Post v-for="post in $store.state.posts" :key="post.id" :post="post" />
  </div>
</template>

<script>
import Post from "../components/Post.vue";
import postService from "../services/PostService.js";


export default {
  name: "posts-list",
  components: { Post },
  methods: {
    getPosts() {
      postService.getPosts().then(response => {
        this.$store.commit("SET_POSTS", response.data);
      });
    }
  },
  computed: {},
  created() {
    this.getPosts();
  }
};
</script>

<style></style>
