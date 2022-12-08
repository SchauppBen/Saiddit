<template>
  <div>
    <h2><em>Active Forums</em></h2>
    <ul>
      <li v-for="forum in forums" :key="forum.name">
        <router-link :to="{ name: 'forum-view', params: forum.name }">
          <br />{{ forum.name }}</router-link
        >
      </li>
    </ul>
  </div>
</template>

<script>
import forumService from "../services/ForumService.js";
export default {
  name: "active-forums",

  methods: {
    getForums() {
      forumService.getForums().then((response) => {
        this.$store.commit("SET_FORUMS", response.data);
      });
    },
  },
  computed: {
    forums() {
      return this.$store.state.forums;
    },
  },
  created() {
    this.getForums();
  },
};
</script>

<style scoped>
div {
  margin: 0 0 0 5px;
}
h2 {
  text-align: center;
}
ul {
  list-style: none;
}
</style>
