<template>
  <div>
    <h1 class="title pink-border">Create A Forum</h1>
    <form class="create-forum" v-on:submit.prevent>
      <label for="name-input">Forum Name</label>
      <input id="name-input" type="text" v-model="forum.name" />
      <label for="description-input">Forum Description</label>
      <input id="description-input" type="text" v-model="forum.description" />
      <button type="submit" v-on:click="saveForum()">Create Forum</button>
    </form>
  </div>
</template>

<script>
import forumService from "../services/ForumService.js";

export default {
  name: "create-forum",
  data() {
    return {
      forum: {
        name: "",
        description: "",
      },
    };
  },
  computed: {
    newForumUser() {
      return this.createNewForumUser();
    }
  },
  created() {
    this.$store.commit("SET_ACTIVE_FORUM", "");
  },
  methods: {
    createNewForumUser() {
      const newForumUser = {};
      newForumUser.forumName = this.forum.name;
      newForumUser.moderator = true;
      newForumUser.userId = this.$store.state.user.id;
      newForumUser.username = this.$store.state.user.username;
      return newForumUser;
    },
    saveForum() {
      this.$store.commit("ADD_FORUM_USER", this.newForumUser);
      this.$store.commit("SAVE_FORUM", this.forum);
      forumService
        .addForum(this.forum)
        .then((response) => {
          if (response.status === 201) {
            // this.$router.push("/");
            this.$router.push(`/${this.forum.name}/`);
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
        });
    },
  },
};
</script>

<style></style>
