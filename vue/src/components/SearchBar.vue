<template>
  <div id="searchBar" class="$info">
    
    <form @submit.prevent="search">
      <font-awesome-icon id="fa-icon" icon="fa-solid fa-magnifying-glass" pull="left" size="lg" />
      <select name="search" id="searchDropdown" v-model="searchOption">
        <option value="posts">posts</option>
        <option value="forums">forums</option>
        <option value="users">users</option>
      </select>
      <input type="text" name="q" v-model="searchTerm" />&nbsp;
      <button type="submit" class="button is-danger is-small is-outlined radius-rounded">Search</button>
    </form>
  </div>
</template>

<script>
import postService from "../services/PostService.js";
import forumService from "../services/ForumService.js";
export default {
  name: "search-bar",
  data() {
    return {
      searchTerm: "",
      searchOption: "posts"
    }
  },
  methods: {
    search() {
      this.$store.commit("SET_SEARCH_TERM", this.searchTerm)
      if (this.$route.path !== `/${this.searchOption}/search`) {
        this.$router.push(`/${this.searchOption}/search`);
      } else if (this.searchOption === "posts") {
        postService.searchForPosts(this.searchTerm).then((response) => {
          this.$store.commit("SET_SEARCHED_POSTS", response.data);
        });
      } else if (this.searchOption === "forums") {
        forumService.searchForums(this.searchTerm).then((response => {
          this.$store.commit("SET_SEARCHED_FORUMS", response.data)
        }))
      }
    }
    
  }
};
</script>

<style>
#fa-icon {
  margin-right: 7px;
}
</style>
