<template>
  <div>
    <post v-for="post in forumPosts" :key="post.postId" :post="post" />
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
  },
  created() {
    this.$store.commit("SET_ACTIVE_FORUM", this.$route.params.forumName);
  },
  methods: {
    forumPosts() {
      return this.$store.state.posts.filter((post) => {
        return post.forumName == this.thisForum.name;
      });
    },
  },
};
</script>

<style></style>
