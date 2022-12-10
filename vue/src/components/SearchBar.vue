<template>
  <div id="searchBar" class="$info">
    <form>
      <label>Search: </label>
      <input @submit.prevent="search" type="text" name="search" v-model="searchTerm" />&nbsp;
      <button type="submit" class="button is-danger is-small is-outlined radius-rounded">Submit</button>
    </form>
  </div>
</template>

<script>
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
      this.$router.push("/")
    }
  }
};
</script>

<style></style>
