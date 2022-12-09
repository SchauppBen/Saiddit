<template>
  <div id="post-container">
    <router-link
      :to="{
        name: 'post-details',
        params: { forumName: post.forumName, postId: post.postId },
      }"
    >
      <div id="postBox">
        <router-link
          class="highlighted"
          :to="{ name: 'forum-view', params: { forumName: post.forumName } }"
          ><h4>[{{ post.forumName }}]</h4></router-link
        >
        <h1>{{ post.title }}</h1>
        <img :src="post.mediaLink" v-show="post.mediaLink" />
        <h3>{{ post.text }}</h3>
        <router-link
          class="highlighted"
          :to="{ name: 'user-posts', params: { username: post.username } }"
          ><h2>User: {{ post.username }}</h2></router-link
        >
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
  border-color: aquamarine;
  margin-bottom: 5px;
  background-color: whitesmoke;
}
</style>
