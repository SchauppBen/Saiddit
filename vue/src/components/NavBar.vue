<template>
  <nav>
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
</style>
