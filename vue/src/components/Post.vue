<template class="posts">
  <div id="post-container">
    <div id="postBox">
      <div class="inline head-banner">
        <!-- Link to user profile -->
        <h2 id="user">
          <router-link
            class="highlighted"
            :to="{ name: 'user-posts', params: { username: post.username } }">
            <font-awesome-icon :icon="['fas', 'circle-user']" size="lg" />
            {{ post.username }}
          </router-link>
        </h2>
        <h2> in </h2>
        <h3>{{ post.datetime }}</h3>
      <h4>
        <router-link
          class="highlighted forum-name"
          :to="{ name: 'forum-view', params: { forumName: post.forumName } }"
          >[{{ post.forumName }}]</router-link
        >
      </h4>
      </div>
      <div class="posts">
        <router-link
        class="link-color"
        :to="{
          name: 'post-details',
          params: { forumName: post.forumName, postId: post.postId },
        }"
      >
        <h1 class="post-title">{{ post.title }}</h1>
        <img id="post-image" :src="post.mediaLink" v-show="post.mediaLink" />
        <h3 class="text">{{ post.text }}</h3>
      </router-link>
      <div class="inline">
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
          <button class="delete-btn" v-if="this.post.userId == this.$store.state.user.id" @click="doDelete()">
            <font-awesome-icon class="delete-color" :icon="['fas', 'trash-can']" size="lg" />
          </button>
          <confirm-dialogue ref="confirmDialogue"></confirm-dialogue>
        </div>
      </div>

      </div>
    </div>
  </div>
</template>

<script>
import postService from '../services/PostService.js';
import ConfirmDialogue from '../components/ConfirmDialogue.vue';
// import Votes from './Votes.vue'

export default {
  name: 'Post',
  components: { ConfirmDialogue },
  props: {
    post: Object,
    votes: Object,
  },
  data() {
    return {
      isUpActive: false,
      isDownActive: false,
      upClick: false,
      downClick: false
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
    },
    async doDelete() {
      const ok = await this.$refs.confirmDialogue.show({
        title: 'Delete Post',
        message: 'Are you sure you want to delete this post? It cannot be undone.',
        okButton: 'Delete',
      });
      // If you throw an error, the method will terminate here unless you surround it wil try/catch
      if (ok) {
        this.deletePost();
        alert('You have successfully delete this post.');
      }
    },
  }
};

</script>

<style scoped>
.link-color {
  text-decoration-color: none;
}
button {
  background-color: transparent;
  background-repeat: no-repeat;
  border: none;
  cursor: pointer;
  overflow: hidden;
  outline: none;
  font-size: 17px;
  width: 80%;
  height: 90%;
}

.head-banner {
  display: flex;
  background-color: #a0c6d3;
  width: 100%;
  height: 100%;
  padding: 7px 0 7px 0;
  border-top-right-radius: 5px;
  border-top-left-radius: 5px;
}

.post-title {
  font-size: 25px;
  font-weight: bold;
}

.inline, .votes {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.highlighted {
  text-decoration-line: underline;
}

#post-image {
  width: 500px;
}

#post-container {
  border: groove;
  border-width: 5px;
  border-color: #76acbd;
  background-color: #c5d6db;
  margin-bottom: 10px;
  border-radius: 10px;
  width: 70%;
}

#forum-name {
  font-weight: bold;
}
</style>
