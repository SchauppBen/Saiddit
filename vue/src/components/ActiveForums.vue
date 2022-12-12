<template>
  <div class="forumsTab">
    <h2><em>Active Forums</em></h2>
    <ul>
      <li v-for="forum in forums" :key="forum.name">
        <router-link
          class="highlighted"
          :to="{ name: 'forum-view', params: { forumName: forum.name } }"
        >
          {{ forum.name }}</router-link
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
    activeForums() {
      forumService.getActiveForums().then((response) => {
        this.$store.commit("SET_ACTIVE_FORUMS", response.data);
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
  display: flex;
  flex: 1;
  flex-direction: column;
  justify-content: space-evenly;
  list-style: none;
  text-align: center;
  font-family: monospace;
  height: calc(100% - 20px);
}
</style>
