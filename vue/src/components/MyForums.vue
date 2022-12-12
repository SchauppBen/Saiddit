<template>
  <div class="forumsTab pink-border">
    <h2><em>My Forums</em></h2>
    <ul>
      <li v-for="forum in myForums" :key="forum.forumName">
        <router-link
          class="highlighted"
          :to="{ name: 'forum-view', params: { forumName: forum.forumName } }"
        >
          <br />{{ forum.forumName }}</router-link
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
    getForumUsers() {
      forumService.getForumUsers().then((response) => {
        this.$store.commit("SET_FORUM_USERS", response.data);
      });
    },
    myForums() {
      return this.$store.state.forumUsers.filter((forum) => {
        forum.username === this.$store.state.user.username;
      });
    },
  },
  computed: {
    forums() {
      return this.$store.state.forumUsers;
    },
  },
  created() {
    this.activeForums();
    this.getForumUsers();
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
