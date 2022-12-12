<template>
  <div class="post-container">
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
        <div class="inline">
          <router-link
            class="highlighted"
            :to="{ name: 'user-posts', params: { username: post.username } }"
            ><font-awesome-icon :icon="['fas', 'circle-user']" size="lg" />  {{ post.username }}</router-link
          >
          <div class="votes">
            <div class="up-vote" >
              <a href="#" @mouseover="isActive=true" @mouseleave="isActive=false">
                <i v-if="!isActive">
                  <font-awesome-icon :icon="['far', 'circle-up']" size="lg" class="up-color" />
                </i>
                <span v-else >
                  <font-awesome-icon :icon="['fas', 'circle-up']" size="lg" class="up-color" />
                </span>
              </a>
            </div>
            <div class="down-vote" >
              <a href="#" @mouseover="isActive=true" @mouseleave="isActive=false">
                <i v-if="!isActive">
                  <font-awesome-icon :icon="['far', 'circle-down']" size="lg" class="down-color" />
                </i>
                <span v-else >
                  <font-awesome-icon :icon="['fas', 'circle-down']" size="lg" class="down-color" />
                </span>
              </a>
            </div>
          </div>
        </div>
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
.inline, .votes {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.up-color {
  color: rgb(255, 102, 0);
}

.down-color {
  color: rgb(76, 0, 255);
}

.title {
  margin-top: -10px;
  margin-right: 0px;
  margin-bottom: 34px;
  margin-left: 0px;
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
  border-color: #76acbd;
}
.links {
  color: pink;
}
.post-container {
  padding: 10px;
}
</style>
