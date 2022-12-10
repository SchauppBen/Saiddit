<template>
  <div class="posts">
    <post class="allPosts" v-for="post in posts" :key="post.id" :post="post" />
  </div>
</template>

<script>
import Post from "./Post.vue";
import postService from "../services/PostService.js";

export default {
  name: "posts-list",
  components: { Post },
  methods: {
    getPosts() {
      postService.getPosts().then((response) => {
        this.$store.commit("SET_POSTS", response.data);
      });
    },
  },
  computed: {
    posts() {
      return this.$store.state.posts;
    },
  },
  created() {
    this.getPosts();
  },
};
</script>

<style>
.posts {
  text-align: center;
}

.allPosts {
  text-align: center;
}
</style>
