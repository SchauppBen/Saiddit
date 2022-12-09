<template>
  <div id="app">
    <div id="div-header">
      <Header id="app-header" />
    </div>
    <div id="container">
      <div>
        <nav-bar id="nav-bar" class="full-height" />
      </div>

      <router-view id="main-page" />
    </div>
  </div>
</template>

<script>
import Header from "./components/Header.vue";
import NavBar from "./components/NavBar.vue";

export default {
  components: { Header, NavBar },
};
</script>
<style>
.highlighted:hover {
  color: orange;
}
#container {
  display: flex;
}
#app-header {
  display: flex;
  justify-content: space-between;
  background-color: pink;
  padding: 10px;
  align-items: center;
  height: 10vh;
  margin: 10px 5px 0 5px;
}

#nav-bar {
  width: 200px;
  background-color: teal;
  padding: 10px;
  margin-top: 10px;
  margin-right: 10px;
  margin-left: 5px;
  height: 187vh;
  border-radius: 5px;
  background-image: linear-gradient(aquamarine, pink);
}

#main-page {
  width: 100vw;
  margin: 10px 0px 10px 0px;
}
div {
  border-radius: 5px;
}
#app {
  background-color: teal;
}
</style>
