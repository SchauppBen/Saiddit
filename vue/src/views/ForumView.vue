<template>
  <div class="posts">
    <h1 class="title pink-border">Posts in {{ thisForum.name }} forum</h1>
    <post
      class="allPosts"
      v-for="post in forumPosts"
      :key="post.postId"
      :post="post"
    />
  </div>
</template>

<script>
import Post from "../components/Post.vue";

export default {
  components: { Post },
  name: "forum-view",

  computed: {
    thisForum() {
      return this.$store.state.forums.find((forum) => {
        return forum.name == this.$store.state.activeForumName;
      });
    },
    forumPosts() {
      return this.$store.state.posts.filter((post) => {
        return post.forumName == this.thisForum.name;
      });
    },
  },
  created() {
    this.$store.commit("SET_ACTIVE_FORUM", this.$route.params.forumName);
  },
  methods: {},
};
</script>

<style scoped>
#forum-posts {
  align-content: center;
}
.title {
  margin-top: -10px;
  margin-right: 0px;
  margin-bottom: 34px;
  margin-left: 0px;
}
</style>
