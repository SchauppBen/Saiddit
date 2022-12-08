<template>
  <nav>
    <h1 id="nav-title"><em>Navigation</em></h1>
    <ul>
      <li><router-link :to="{ name: 'create-post' }"> + </router-link></li>
      <li>
        <router-link :to="{ name: 'create-forum' }"> Create Forum </router-link>
      </li>
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
h1 {
  font-family: Georgia, "Times New Roman", Times, serif;
  color: teal;
}
</style>
