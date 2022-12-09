<template>
  <div id="post-container" class="posts">
    <router-link
      :to="{
        name: 'post-details',
        params: { forumName: post.forumName, postId: post.postId },
      }"
    >
      <div id="postBox">
        <h4>
          <router-link
            class="highlighted"
            :to="{ name: 'forum-view', params: { forumName: post.forumName } }"
            >[{{ post.forumName }}]</router-link
          >
        </h4>
        <h1>{{ post.title }}</h1>
        <img :src="post.mediaLink" v-show="post.mediaLink" />
        <h3>{{ post.text }}</h3>
        <h2>
          <router-link
            class="highlighted"
            :to="{ name: 'user-posts', params: { username: post.username } }"
            >User: {{ post.username }}</router-link
          >
        </h2>
        <h3>{{ post.datetime }}</h3>
      </div>
    </router-link>
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
#postBox img {
  width: 500px;
}

#post-container {
  border: groove;
  border-width: 5px;
  border-color: #ffd3d7;
  background-color: #c5d6db;
  margin: 10px;
}
</style>
