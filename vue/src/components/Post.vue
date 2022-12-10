<template>
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
        <h1>{{ post.title }}</h1>
        <img id="post-image" :src="post.mediaLink" v-show="post.mediaLink" />
        <h3>{{ post.text }}</h3></router-link
      >
      <h2>
        <router-link
          class="highlighted"
          :to="{ name: 'user-posts', params: { username: post.username } }"
          >User: {{ post.username }}</router-link
        >
      </h2>
      <h3>{{ post.datetime }}</h3>
      <div id="votes">
        <img id="up-arrow" src="../assets/arrowup.png" />#of upvotes<img
          id="down-arrow"
          src="../assets/arrowdown.png"
        />#of downvotes
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    post: Object,
  },
};
</script>

<style scoped>
#post-image {
  width: 500px;
}

#votes img {
  height: 20px;
}
#up-arrow:hover {
  background-color: green;
}
#down-arrow:hover {
  background-color: red;
}

#post-container {
  border: groove;
  border-width: 5px;
  border-color: #ffd3d7;
  background-color: #c5d6db;
  margin: 10px;
}
</style>
