<template>
  <div class="posts">
    <h3 @click="sortPosts">{{sortByMostRecent ? "Sort by most popular posts." : "Sort by most recent posts"}}</h3>
    <post class="allPosts" v-for="post in posts" :key="post.id" :post="post" />
  </div>
</template>

<script>
import Post from "./Post.vue";
import postService from "../services/PostService.js";

export default {
  name: "posts-list",
  components: { Post },
  data() {
    return {
      sortByMostRecent: true
    }
  },
  methods: {
    getPosts() {
      postService.getPosts().then((response) => {
        this.$store.commit("SET_POSTS", response.data);
      });
    },
    sortPosts() {
      if (this.sortByMostRecent) {
        this.sortByMostPopular();
      } else {
        this.sortByRecent();
      }
      this.sortByMostRecent = !this.sortByMostRecent;
    },
    sortByRecent() {
      this.$store.state.posts.sort((post1, post2) => {
        if (post1.dateTime > post2.dateTime) {
          return 1;
        } else if (post1.dateTime < post2.dateTime) {
          return -1;
        } else {
          return 0;
        }
      });
    },
    sortByMostPopular() {
      this.$store.state.posts.sort((post1, post2) => {
        const post1Votes = postService.getVotesByPost(post1.id);
        const post2Votes = postService.getVotesBYPost(post2.id);
        if ((post1Votes.upvotes - post1Votes.downvotes) > (post2Votes.upvotes - post2Votes.downvotes)) {
          return 1;
        } else if ((post1Votes.upvotes - post1Votes.downvotes) < (post2Votes.upvotes - post2Votes.downvotes)) {
          return -1;
        } else {
          return 0;
        }
      });
    }
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
