<template>
  <div>
    <h2><em>My Forums</em></h2>
    <ul>
      <li v-for="forum in myForums" :key="forum.name">
        <router-link
          class="highlighted"
          :to="{ name: 'forum-view', params: { forumName: forum.name } }"
        >
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
      forumService.getForumUsers().then((response) => {
        this.$store.commit("SET_FORUM_USERS", response.data);
      });
    },
    myForums() {
      return this.$store.state.forumUsers.filter((forum) => {
        forum.username === this.$store.state.user;
      });
    },
  },
  computed: {
    forums() {
      return this.$store.state.activeForums;
    },
  },
  created() {
    this.activeForums();
    this.getForums();
  },
};
</script>

<style scoped>
h2 {
  text-align: center;
}
ul {
  list-style: none;
  text-align: center;
  font-family: monospace;
}
</style>
