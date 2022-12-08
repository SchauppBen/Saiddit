<template>
  <router-link
    :to="{
      name: 'post-details',
      params: { forumId: post.forumId, postId: post.id },
    }"
  >
    <div id="postBox">
      <h4>{{ post.forumName }}</h4>
      <h1>{{ post.title }}</h1>
      <img :src="post.mediaLink" v-show="post.mediaLink" />
      <h3>{{ post.text }}</h3>
      <h2>User: {{ post.username }}</h2>
      <h3>{{ post.datetime }}</h3>
    </div>
  </router-link>
</template>

<script>
import PostService from "../services/PostService";
export default {
  props: {
    Post: Object,
  },

  computed: {
    post() {
      return this.$store.state.activePost;
    },

    methods: {
      created() {
        PostService.getPost(this.post.forumId, this.post.id).then(
          (response) => {
            this.$store.commit("SET_ACTIVE_POST", response.data);
          }
        );
      },
    },
  },
};
</script>

<style></style>
