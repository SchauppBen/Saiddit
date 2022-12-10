<template>
  <div id="searchBar" class="$info">
    
    <form @submit.prevent="search">
      <font-awesome-icon id="fa-icon" icon="fa-solid fa-magnifying-glass" pull="left" size="lg" />
      <input type="text" name="q" v-model="searchTerm" />&nbsp;
      <button type="submit" class="button is-danger is-small is-outlined radius-rounded">Search</button>
    </form>
  </div>
</template>

<script>
import postService from "../services/PostService.js";
export default {
  name: "search-bar",
  data() {
    return {
      searchTerm: ""
    }
  },
  methods: {
    search() {
      this.$store.commit("SET_SEARCH_TERM", this.searchTerm)
      if (this.$route.path !== "/search") {
        this.$router.push("/search");
      } else {
        postService.searchForPosts(this.searchTerm).then((response) => {
          this.$store.commit("SET_SEARCHED_POSTS", response.data);
        });
      
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
