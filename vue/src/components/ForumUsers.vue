<template>
  <div class="forumsTab pink-border scrollable">
    <h2>
      <em>{{ forum }} Users</em>
    </h2>
    <ul>
      <li v-for="forum in forumUsers" :key="forum.username">
        <router-link
          class="highlighted"
          :to="{ name: 'user-posts', params: { username: forum.username } }"
        >
          <br />{{ forum.username }}</router-link
        ><img v-if="forum.moderator" id="mod-logo" src="../assets/spider.png" />
      </li>
    </ul>
  </div>
</template>

<script>
import forumService from "../services/ForumService.js";
export default {
  name: "forum-users",

  methods: {
    getForumUsers() {
      forumService.getForumUsers().then((response) => {
        this.$store.commit("SET_FORUM_USERS", response.data);
      });
    },
  },
  computed: {
    forums() {
      return this.$store.state.forumUsers;
    },
    forum() {
      return this.$store.state.activeForumName;
    },
    forumUsers() {
      return this.forums.filter((forum) => {
        return forum.forumName == this.forum;
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
#mod-logo {
  height: 20px;
}
</style>
