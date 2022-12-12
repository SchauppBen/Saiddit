<template>
  <div id="app">
    <div id="div-header">
      <Header id="app-header" />
    </div>
    <div id="container">
      <nav-bar class="sidebar" id="nav-bar" />
      <router-view class="main-content" />
      <forums-sidebar id="active-forums" />
    </div>
  </div>
</template>

<script>
import ForumsSidebar from "./components/ForumsSidebar.vue";
import Header from "./components/Header.vue";
import NavBar from "./components/NavBar.vue";

export default {
  components: { Header, NavBar, ForumsSidebar },
};
</script>
<style>
.title {
  background-color: #ffd3d7;
  text-align: center;
  font-size: 50px;
  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
    "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
  color: #7c9eb7;
  margin: 0 10px 0 10px;
  border-radius: 10px;
  border: groove;
  border-width: 5px;
  border-color: #ffaeb5;
}

.highlighted:hover {
  color: #000000;
}
#container {
  display: flex;
  justify-content: center;
  top: 100px;
  border-radius: 10px;
  padding-top: 90px;
}
#app-header {
  display: flex;
  justify-content: space-between;
  background-color: #ffd3d7;
  padding: 5px;
  align-items: center;
  border-radius: 0px;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
}
.forumsTab {
  background-color: #ffd3d7;
  width: 150px;
  padding: 10px;
  margin-top: 10px;
  margin-right: 10px;
  margin-left: 10px;
  margin-bottom: 20px;
  border-radius: 10px;
  height: calc(50vh - 70px);
}

.sidebar {
  background-color: #ffd3d7;
  width: 150px;
  padding: 10px;
  margin-top: 10px;
  margin-right: 10px;
  margin-left: 10px;
  margin-bottom: 20px;
  border-radius: 10px;
  height: calc(100vh - 100px);
}
#nav-bar {
  position: fixed;
  top: 90px;
  left: 0;
  height: calc(100vh - 120px);
}
#active-forums {
  position: fixed;
  top: 90px;
  right: 0;
  height: calc(100vh - 120px);
}

.main-content {
  margin: 10px 0 10px 0;
  border-radius: 10px;
  width: calc(100vw - 400px);
  min-height: calc(100vh - 110px);
}

#app {
  background-color: #ffc20c;
  height: 100%;
}
.posts {
  padding: 10px;
  border-radius: 10px;
}
.scrollable {
  overflow-y: auto;
}
</style>
