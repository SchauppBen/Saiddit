<template>
  <div>
    <post
      class="allPosts"
      v-for="post in userPosts"
      :key="post.postId"
      :post="post"
    />
  </div>
</template>

<script>
import Post from "../components/Post.vue";
export default {
  components: { Post },
  name: "user-posts",
  props: {
    post: Object,
  },
  computed: {
    userPosts() {
      return this.$store.state.posts.filter((element) => {
        return element.username == this.$route.params.username;
      });
    },
  },
};
</script>

<style></style>
