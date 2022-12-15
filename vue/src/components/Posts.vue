<template>
  <div class="posts">
    <h3 @click="sortPosts(); changeSortType()">{{this.$store.state.sortByMostRecent ? "Sort by most popular posts." : "Sort by most recent posts"}}</h3>
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
        this.sortPosts();
      });
    },
    sortPosts() {
      if (this.$store.state.sortByMostRecent) {
        this.sortByMostPopular();
      } else {
        this.sortByMostRecent();
      }
    },
    sortByMostRecent() {
      const currentPosts = this.$store.state.posts;
      currentPosts.sort((post1, post2) => {
        if (post1.timeInMillis > post2.timeInMillis) {
          return -1;
        } else if (post1.timeInMillis < post2.timeInMillis) {
          return 1;
        } else {
          return 0;
        }
      });
      this.$store.commit("CLEAR_POSTS");
      this.$store.commit("SET_POSTS", currentPosts);
    },
    changeSortType() {
      this.$store.commit("TOGGLE_SORTED_POSTS");
    },
    sortByMostPopular() {
      let currentPosts = this.$store.state.posts;
      currentPosts = currentPosts.sort((post1, post2) => {
        if (post1.votes > post2.votes) {
          return -1;
        } else if (post1.votes < post2.votes) {
          return 1;
        } else {
          return 0;
        }
      });
      this.$store.commit("CLEAR_POSTS");
      this.$store.commit("SET_POSTS", currentPosts);
    }
  },
  computed: {
    posts() {
      return this.$store.state.posts;
    },
  },
  mounted() {
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
