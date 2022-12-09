<template>
  <div id="app">
    <div id="div-header">
      <Header id="app-header" />
    </div>
    <div id="container">
      <nav-bar class="sidebar" />
      <router-view class="main-content" />
      <active-forums class="sidebar" />
    </div>
  </div>
</template>

<script>
import ActiveForums from "./components/ActiveForums.vue";
import Header from "./components/Header.vue";
import NavBar from "./components/NavBar.vue";

export default {
  components: { Header, NavBar, ActiveForums },
};
</script>
<style>
.highlighted:hover {
  color: #ffc20c;
}
#container {
  display: flex;
  justify-content: space-between;
  border-radius: 10px;
}
#app-header {
  display: flex;
  justify-content: space-between;
  background-color: #ffd3d7;
  padding: 5px;
  align-items: center;
  border-radius: 0px;
}

.sidebar {
  background-image: linear-gradient(#ffd3d7, #fff3e0);
  width: 200px;
  padding: 10px;
  margin-top: 10px;
  margin-right: 10px;
  margin-left: 10px;
  margin-bottom: 10px;
  border-radius: 10px;
}

.main-content {
  flex: 1;
  margin: 10px 0px 10px 0px;
  border-radius: 10px;
}

#app {
  background-color: #ffc20c;
  height: 100%;
}
.posts {
  padding: 10px;
  border-radius: 10px;
}
</style>
