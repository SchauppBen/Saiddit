<template>
  <div id="header">
    <router-link v-bind:to="{ name: 'home' }" id="logo">
      <a>saiddit</a>
      <img id="pageLogo" src="../assets/duckie.png"
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
import ChatLogo from "../components/ChatLogo.vue";
import SearchBar from "../components/SearchBar.vue";
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
  height: 8vh;
}
div {
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
}
#saiddit {
  font-size: 20px;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}
a {
  font-size: 30px;
  font-family: Georgia, "Times New Roman", Times, serif;
  color: teal;
  font-style: italic;
}
</style>
