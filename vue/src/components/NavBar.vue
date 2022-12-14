<template>
  <nav class="forumsTab pink-border">
    <h1 id="nav-title"><em>Navigation</em></h1>
    <ul>
      <li>
        <br />
        <router-link class="highlighted" :to="{ name: 'create-post' }">
          Create Post
        </router-link>
      </li>
      <br />
      <li>
        <router-link class="highlighted" :to="{ name: 'create-forum' }">
          Create Forum
        </router-link>
      </li>
      <br />
      <li>
        <router-link class="highlighted" :to="{ name: 'search-forums' }">
          Browse All Forums
        </router-link>
      </li>
      <br />
    </ul>
  </nav>
</template>

<script>
export default {};
</script>

<style scoped>
ul {
  list-style: none;
}
ul li {
  text-align: left;
}
#nav-title {
  font-family: Georgia, "Times New Roman", Times, serif;
  color: #7c9eb7;
}
</style>
