<template>
  <nav>
    <div v-for="forum in this.$store.state.forums" v-bind:key="forum.forumId">
        <!-- <router-link v-bind:to="{ name: 'Messages', params: { id: topic.id } }">{{ topic.title }} -->
        <!-- </router-link> -->
    </div>
  </nav>
</template>

<script>
export default {

}
</script>

<style>

</style>