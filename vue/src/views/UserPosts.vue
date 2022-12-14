<template>
  <div>
    <h1 class="title pink-border">@{{ this.$route.params.username }}</h1>
    <post
      class="post"
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

<style>
.post {
  margin: 34px 10px 0px 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
