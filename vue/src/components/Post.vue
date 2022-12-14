<template id="font-awesome-icon">
  <div id="post-container" class="posts">
    <div id="postBox">
      <h4>
        <router-link
          class="highlighted"
          :to="{ name: 'forum-view', params: { forumName: post.forumName } }"
          >[{{ post.forumName }}]</router-link
        >
      </h4>
      <router-link
        :to="{
          name: 'post-details',
          params: { forumName: post.forumName, postId: post.postId },
        }"
      >
        <h1 class="text">{{ post.title }}</h1>
        <img id="post-image" :src="post.mediaLink" v-show="post.mediaLink" />
        <h3 class="text">{{ post.text }}</h3></router-link
      >
      <div class="inline">
        <!-- Link to user profile -->
        <h2 id="user">
          <router-link
            class="highlighted"
            :to="{ name: 'user-posts', params: { username: post.username } }"
          >
            <font-awesome-icon :icon="['fas', 'circle-user']" size="lg" />
            {{ post.username }}
          </router-link>
        </h2>
        <h3>{{ post.datetime }}</h3>

        <!-- Up-vote & down-vote buttons -->
        <div class="votes">
          <div class="up-vote" >
            <button @mouseover="isUpActive=true" @mouseleave="isUpActive=false" @click="upClick=!upClick; downClick=false" class="ui button toggle" >
              <i v-if="toggleUp(upClick) == false && isUpActive == false" >
                <font-awesome-icon :icon="['far', 'circle-up']" size="lg" class="up-color" />
              </i>
              <span v-else>
                <font-awesome-icon :icon="['fas', 'circle-up']" size="lg" class="up-color" />
              </span>
            </button>
          </div>
          <div class="down-vote" >
            <button @mouseover="isDownActive=true" @mouseleave="isDownActive=false" @click="downClick=!downClick; upClick=false" class="ui button toggle" >
              <i v-if="toggleDown(downClick) == false && isDownActive == false" >
                <font-awesome-icon :icon="['far', 'circle-down']" size="lg" class="down-color" />
              </i>
              <span v-else>
                <font-awesome-icon :icon="['fas', 'circle-down']" size="lg" class="down-color" />
              </span>
            </button>
          </div>
        </div>

        <!-- Delete post button -->
        <div>
          <button
            v-if="this.post.userId == this.$store.state.user.id"
            v-on:click="deletePost()"
          >
            Delete Post
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import postService from "../services/PostService.js";

export default {
  name: 'post',
  props: {
    post: Object,
  },
  data() {
    return {
      isUpActive: false,
      isDownActive: false,
      upClick: false,
      downClick: false,
    }
  },
  methods: {
    toggleUp(clicked) {
      if (this.upClick == true) {
        clicked = true;
        return clicked;
      } else {
        clicked = false
        return clicked;
      }
    },
    toggleDown(clicked) {
      if (this.downClick == true) { 
        clicked = true;
        return clicked;
      } else {
        clicked = false
        return clicked;
      }
    },
    upVote() {
      this.upClick = true;
      this.$store.commit("UP_VOTE", this.upClick=true);
    },
    downVote() {
      this.downClick = true;
      this.$store.commit("DOWN_VOTE", this.downClick=true);
    },
    deletePost() {
      this.$store.commit("DELETE_POST", this.post);
      postService.deletePost(this.post.postId).catch((error) => {
        this.handleErrorResponse(error, "deleting");
      });
    }
  }
};

</script>

<style scoped>
.highlighted {
  text-decoration-line: underline;
}
button {
    background-color: transparent;
    background-repeat: no-repeat;
    border: none;
    cursor: pointer;
    overflow: hidden;
    outline: none;
    font-size: 17px;
    width: 40%;
    height: 70%;
}

#post-image {
  width: 500px;
}

.inline,
.votes {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

#post-container {
  border: groove;
  border-width: 5px;
  border-color: #76acbd;
  background-color: #c5d6db;
  margin-bottom: 10px;
}
</style>
