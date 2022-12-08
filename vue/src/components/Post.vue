<template>
  <div id="post-container">
    <router-link
      :to="{
        name: 'post-details',
        params: { forumName: post.forumName, postId: post.postId },
      }"
    >
      <div id="postBox">
        <h4>[{{ post.forumName }}]</h4>
        <h1>{{ post.title }}</h1>
        <img :src="post.mediaLink" v-show="post.mediaLink" />
        <h3>{{ post.text }}</h3>
        <h2>Posted by: {{ post.username }}</h2>
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
