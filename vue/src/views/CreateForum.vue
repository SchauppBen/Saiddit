<template>
  <div>
    <h1 class="title">Create A Forum</h1>
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
  methods: {
    saveForum() {
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
