<template>
  <div id="header">
    <router-link v-bind:to="{ name: 'home' }"
      ><img
        id="pageLogo"
        src="https://mario.wiki.gallery/images/thumb/8/8a/New_Super_Mario_Bros._U_Deluxe_Super_Star.png/1200px-New_Super_Mario_Bros._U_Deluxe_Super_Star.png"
    /></router-link>
    <search-bar />
    <chat-logo />
  </div>
</template>

<script>
import ChatLogo from "./ChatLogo.vue";
import SearchBar from "./SearchBar.vue";
export default {
  components: { SearchBar, ChatLogo },
};
</script>

<style scoped>
#header {
  display: flex;
  justify-content: space-between;
}
#pageLogo {
  height: 40px;
}
</style>
