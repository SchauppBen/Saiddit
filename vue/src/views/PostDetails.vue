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
          <div id="user">
            <router-link
            class="highlighted"
            :to="{ name: 'user-posts', params: { username: post.username } }"
            ><font-awesome-icon :icon="['fas', 'circle-user']" size="lg" />  {{ post.username }}</router-link
          >
          </div>
          <div class="votes">
            <div class="up-vote" >
              <button @click.prevent @mouseover="isUpActive=true" @mouseleave="isUpActive=false">
                <i v-if="!isUpActive">
                  <font-awesome-icon :icon="['far', 'circle-up']" size="lg" class="up-color" />
                </i>
                <span v-else >
                  <font-awesome-icon :icon="['fas', 'circle-up']" size="lg" class="up-color" />
                </span>
              </button>
            </div>
            <div class="down-vote" >
              <button @click.prevent @mouseover="isDownActive=true" @mouseleave="isDownActive=false">
                <i v-if="!isDownActive">
                  <font-awesome-icon :icon="['far', 'circle-down']" size="lg" class="down-color" />
                </i>
                <span v-else >
                  <font-awesome-icon :icon="['fas', 'circle-down']" size="lg" class="down-color" />
                </span>
              </button>
            </div>
          </div>
        </div>
      </h2>
      <h3 class="text">{{ post.datetime }}</h3>
      <!-- Direct Reply Input -->
      <div class = "reply-input">
        <div>
          <label for="reply-input">Reply to This Post as {{this.$store.state.user.username}}</label>
        </div>
        <div>
          <input type="text" id="reply-input" name="replyInput" v-model="directReplyInput.replyText"/>
        </div>
      </div>
      <div>
        <button v-on:click="saveReply()">reply</button>
      </div>
    </div>
    <!-- v-if is required here to make sure when post details page is rendered, the replies passed in has been updated to a nonzero length rather than its default [] state with 0 elements. Without it here, the replies could be unaccessible the first time post-details page is rendered. -->
    <reply-section class="reply-section" v-if="this.replies.length" :replies="this.replies"/> 
  </div>
</template>

<script>
import replyService from "../services/ReplyService.js";
import replySection from "../components/ReplySection.vue"

export default {
  name: "post-details",
  data() {
    return {
      directReplyInput: {
        replyText: "",
        mediaLink: ""
      },
      isUpActive: false,
      isDownActive: false
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
    directReply() {
      return this.createDirectReply(this.directReplyInput);
    }
  },
  methods: {
    // update currently active replies in $store
    getReplies() {
      replyService
        .getRepliesByPost(this.$route.params.postId)
        .then((response) => {
          this.$store.commit("SET_ACTIVE_REPLIES", response.data);
        });
    },
    createDirectReply(directReplyInput) {
      const directReply = {};
      directReply.replyToReplyId = 0;
      directReply.postId = this.post.postId;
      directReply.userFrom = this.$store.state.user.id;
      directReply.usernameFrom = this.$store.state.user.username;
      directReply.replyText = directReplyInput.replyText;
      directReply.mediaLink = directReplyInput.mediaLink;
      return directReply;
    },
    saveReply() {
      this.$store.commit("SAVE_REPLY", this.directReply);
      replyService.addReply(this.directReply)
        .then(response => {
            if (response.status === 201) {
                this.directReplyInput = {
                  replyText: "",
                  mediaLink: ""
                }
            }
        })
        .catch(error => {
            this.handleErrorResponse(error, "adding");
        });
    }
  },
  created() {
    this.$store.commit("SET_ACTIVE_POST", this.$route.params.postId);
    this.getReplies();
  },
};
</script>

<style scoped>
button {
    background-color: transparent;
    background-repeat: no-repeat;
    border: none;
    cursor: pointer;
    overflow: hidden;
    outline: none;
    font-size: 17px;
}

.inline, .votes {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.title {
  margin-top: -10px;
  margin-right: 0px;
  margin-bottom: 34px;
  margin-left: 0px;
}

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
  border-color: #76acbd;
}
.links {
  color: pink;
}
.post-container {
  padding: 10px;
}
</style>
