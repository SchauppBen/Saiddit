<template>
  <div class="posts">
    <h4>Posted in [{{ post.forumName }}]</h4>
    <h1>{{ post.title }}</h1>
    <img id="detail-img" :src="post.mediaLink" v-show="post.mediaLink" />
    <h3>{{ post.text }}</h3>
    <h2>Posted by: {{ post.username }}</h2>
    <h3>{{ post.datetime }}</h3>
    <div class = "reply-input">
      <div>
        <label for="reply-input">Reply to This Post as {{this.$store.state.user.username}}</label>
      </div>
      <div>
        <input type="text" id="reply-input" name="replyInput" v-model="directReply.replyText"/>
      </div>
    </div>
    <div>
        <button v-on:click="saveReply()">reply</button>
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
    <!-- v-if is required here to make sure when post details page is rendered, the replies passed in has been updated to a nonzero length rather than its default [] state with 0 elements. Without it here, the replies could be unaccessible the first time post-details page is rendered. -->
    <reply-section class="reply-section" v-if="this.replies.length" :replies = replies /> 
  </div>
</template>

<script>
import replyService from "../services/ReplyService.js";
import replySection from "../components/ReplySection.vue"

export default {
  name: "post-details",
  data() {
    return {
      directReply: {
        replyToReplyId: 0,
        postId: this.$store.state.activePostId,
        userFrom: this.$store.state.user.id,
        replyText: "",
        mediaLink: "",
      }
    }
  },
  components: {replySection},
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
    saveReply() {
      this.$store.commit("SAVE_REPLY", this.directReply);
      replyService.addReply(this.directReply)
        .then(response => {
            if (response.status === 201) {
                this.directReply = {
                  replyToReplyId: 0,
                  postId: this.$store.state.activePostId,
                  userFrom: this.$store.state.user.id,
                  replyText: "",
                  mediaLink: "",
                }
            }
        })
        .catch(error => {
            this.handleErrorResponse(error, "adding");
        });
      this.getReplies();   
      this.replies = this.$store.state.activeReplies;
    }
  },
  created() {
    this.$store.commit("SET_ACTIVE_POST", this.$route.params.postId);
    this.getReplies();
  },
};
</script>

<style scoped>
.reply-input {
  text-indent: 10%;
  text-align: left;
}

#reply-input {
  display: inline-block;
  width: 50%;
  padding:50px 10px;
}

.reply-section {
  margin-top: 15px;
  padding-top:15x;
}

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
