<template>
  <div>
    <h2><em>Forums that match your search</em></h2>
    <ul>
      <li v-for="forum in searchedForums" :key="forum.name">
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
import forumService from '../services/ForumService';
export default {
    name: "searched-forums",
    methods: {
        getForums(searchTerm) {
            forumService.searchForums(searchTerm).then((response) => {
                this.$store.commit("SET_SEARCHED_FORUMS", response.data);
            })
        }
    },
    computed: {
        searchedForums() {
        return this.$store.state.searchedForums;
    },
  },

  created() {
      this.getForums(this.$store.state.searchTerm);
  }

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
