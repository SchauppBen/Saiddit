<template>
  <div>
    <h4>Posted in [{{ post.forumName }}]</h4>
    <h1>{{ post.title }}</h1>
    <img id="detail-img" :src="post.mediaLink" v-show="post.mediaLink" />
    <h3>{{ post.text }}</h3>
    <h2>Posted by: {{ post.username }}</h2>
    <h3>{{ post.datetime }}</h3>

    <label for="replyInput">Reply</label>
    <input type="text" id="replyInput" name="replyInput">

    <div v-for="reply in replies" :key="reply.replyId">
      <div> User {{reply.userFrom}} : {{reply.replyText}} </div>
       
    </div>
  </div>
</template>

<script>
import replyService from "../services/ReplyService.js";

export default {
    name: "post-details",
    components: {},
    computed: {
        post() {  return this.$store.state.posts.find( (post) => {
                return post.postId === this.$store.state.activePostId;
            });
        },
        replies() { 
          return this.$store.state.activeReplies;
        }
    },
    methods: {
      getReplies() {
        replyService.getRepliesByPost(this.$route.params.postId).then(response => {
            this.$store.commit("SET_ACTIVE_REPLIES", response.data);
        });
      }
    },
    created() {
      this.$store.commit('SET_ACTIVE_POST', this.$route.params.postId);
      this.getReplies();

    }
}
</script>

<style scoped>
#detail-img {
  width: 500px;
}
div {
  text-align: center;
}
</style>
