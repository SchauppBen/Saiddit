<template>
  <div>
    <h1 class="title">Post details</h1>
    <div class="posts">
      <h4>
        Posted in
        <router-link
          class="highlighted"
          :to="{ name: 'forum-view', params: { forumName: post.forumName } }"
          >[{{ post.forumName }}]</router-link
        >
      </h4>
      <h1 class="text">{{ post.title }}</h1>
      <img id="detail-img" :src="post.mediaLink" v-show="post.mediaLink" />
      <h3 class="text">{{ post.text }}</h3>
      <h2 class="text">
        Posted by:
        <router-link
          class="highlighted"
          :to="{ name: 'user-posts', params: { username: post.username } }"
          >{{ post.username }}</router-link
        >
      </h2>
      <h3 class="text">{{ post.datetime }}</h3>

      <label for="replyInput">Reply</label>
      <input type="text" id="replyInput" name="replyInput" />

      <div v-for="reply in replies" :key="reply.replyId">
        <div>User {{ reply.userFrom }} : {{ reply.replyText }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import replyService from "../services/ReplyService.js";

export default {
  name: "post-details",
  components: {},
  computed: {
    post() {
      return this.$store.state.posts.find((post) => {
        return post.postId === this.$store.state.activePostId;
      });
    },
    replies() {
      return this.$store.state.activeReplies;
    },
  },
  methods: {
    getReplies() {
      replyService
        .getRepliesByPost(this.$route.params.postId)
        .then((response) => {
          this.$store.commit("SET_ACTIVE_REPLIES", response.data);
        });
    },
  },
  created() {
    this.$store.commit("SET_ACTIVE_POST", this.$route.params.postId);
    this.getReplies();
  },
};
</script>

<style scoped>
#detail-img {
  width: 500px;
}
.text {
  font-family: sans-serif;
  color: royalblue;
  font-size: 20px;
}
.posts {
  text-align: center;
  color: #7e96a7;
  background-color: #c5d6db;
  border: groove;
  border-width: 5px;
  border-color: #ffd3d7;
}
.links {
  color: pink;
}
</style>
