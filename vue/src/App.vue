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
#container {
  display: flex;
}
#app-header {
  display: flex;
  justify-content: space-between;
  background-color: pink;
  padding: 10px;
  align-items: center;
  height: 5vh;
}

#nav-bar {
  width: 200px;
  background-color: teal;
  padding: 10px;
  margin-top: 10px;
  margin-right: 10px;
  height: 100vh;
  border-radius: 5px;
}

#main-page {
  width: 100vw;
  margin: 10px 0px 10px 0px;
}
div {
  border-radius: 5px;
}
</style>
