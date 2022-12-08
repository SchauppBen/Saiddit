<template>
  <router-link
    :to="{
      name: 'post-details',
      params: { forumName: post.forumName, postId: post.postId },
    }"
  >
    <div id="postBox">
      <h4>{{ post.forumName }}</h4>
      <h1>{{ post.title }}</h1>
      <img :src="post.mediaLink" v-show="post.mediaLink" />
      <h3>{{ post.text }}</h3>
      <h2>User: {{ post.username }}</h2>
      <h3>{{ post.datetime }}</h3>
    </div>
  </router-link>
</template>

<script>
export default {
  props: {
    Post: Object,
  },
};
</script>

<style scoped>
#postBox img {
  width: 500px;
}
</style>
