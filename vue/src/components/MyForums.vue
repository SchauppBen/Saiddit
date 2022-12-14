<template>
  <div class="forumsTab pink-border scrollable">
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
  },
  computed: {
    forumUsers() {
      return this.$store.state.forumUsers;
    },
    myForums() {
      return this.forumUsers.filter((forumUser) => {
        return forumUser.userId == this.$store.state.user.id;
      });
    },
    currentUser() {
      return this.$store.state.user.username;
    },
  },
  created() {
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
