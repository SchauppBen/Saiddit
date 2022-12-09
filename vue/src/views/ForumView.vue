<template>
  <div class="posts">
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
</style>
