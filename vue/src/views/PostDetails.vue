<template>
  <div>
    <h4>{{ post.forumId }}</h4>
    <h1>{{ post.title }}</h1>
    <img :src="post.mediaLink" v-show="post.mediaLink" />
    <h3>{{ post.text }}</h3>
    <h2>User: {{ post.username }}</h2>
    <h3>{{ post.datetime }}</h3>

    <label for="replyInput">Reply</label>
    <input type="text" id="replyInput" name="replyInput" />
  </div>
</template>

<script>
import PostService from "../services/PostService";

export default {
  name: "post-details",

  components: {},
  computed: {
    post() {
      return this.$store.state.activePost;
    },
    // return this.$store.state.posts.find( (post) => {
    //     return post.id === this.$store.state.activePostId;
    // });
    // }
  },
  created() {
    PostService.getPost(this.post.forumId, this.post.postId).then(
      (response) => {
        this.$store.commit("SET_ACTIVE_POST", response.data);
      }
    );
  },
};
</script>

<style></style>
