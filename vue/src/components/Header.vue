<template>
  <div id="header">
    <router-link v-bind:to="{ name: 'home' }"
      ><img id="pageLogo" src="../assets/duck_logo.jpg"
    /></router-link>
    <search-bar />
    <chat-logo />
    <div v-if="$store.state.token != ''">
      logged in as {{ $store.state.user.username }}
    </div>
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">
      Logout
    </router-link>
    <router-link v-bind:to="{ name: 'login' }" v-else> Login </router-link>
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
  width: 100vw;
}
#pageLogo {
  height: 5vh;
}
</style>
