<template>
  <div id="postBox">
    <h1>{{ post.title }}</h1>
    <img :src="post.media" v-show="post.media" />
    <h3>{{ post.text }}</h3>
    <h2>User: {{ post.username }}</h2>
    <h3>{{ post.datetime }}</h3>
  </div>
</template>

<script>
export default {
  props: {
    post: Object,
  },
};
</script>

<style></style>
