<template>
  <div class="posts">
    <h3 @click="changeSortType()">{{this.$store.state.sortByMostRecent ? "Sort by most popular posts." : "Sort by most recent posts"}}</h3>
    <div v-if="this.$store.state.sortByMostRecent">
      <post class="allPosts" v-for="post in mostRecentPosts" :key="post.id" :post="post" />
    </div>
    <div v-else>
      <post class="allPosts" v-for="post in mostPopularPosts" :key="post.id" :post="post" />
    </div>
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
      this.sortByMostPopular();
      this.sortByMostRecent();
      
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
      this.$store.commit("SET_MOST_RECENT_POSTS", currentPosts);
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
      this.$store.commit("SET_MOST_POPULAR_POSTS", currentPosts);
    }
  },
  computed: {
    posts() {
      return this.$store.state.posts;
    },
    mostRecentPosts() {
      return this.$store.state.mostRecentPosts;
    },
    mostPopularPosts() {
      return this.$store.state.mostPopularPosts;
    }
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
