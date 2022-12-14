<template>
  <div class="posts">
    <h1 class="title pink-border">Posts in {{ thisForum.name }} forum</h1>
    <h3 v-if="isAMember">You are a member</h3>
    <button type="submit" v-on:click="joinForum()" v-else>Join Forum!</button>
    <post
      class="allPosts"
      v-for="post in forumPosts"
      :key="post.postId"
      :post="post"
    />
  </div>
</template>

<script>
import Post from "../components/Post.vue";
import ForumService from "../services/ForumService";

export default {
  components: { Post },
  name: "forum-view",

  computed: {
    thisForum() {
      return this.$store.state.forums.find((forum) => {
        return forum.name == this.$store.state.activeForumName;
      });
    },

    forumPosts() {
      return this.$store.state.posts.filter((post) => {
        return post.forumName == this.thisForum.name;
      });
    },
    forumUsers() {
      return this.$store.state.forumUsers.filter((forum) => {
        return forum.forumName === this.thisForum.name;
      });
    },
    thisUser() {
      return this.$store.state.user.username;
    },
    isAMember() {
      return this.forumUsers.some((element) => {
        return element.username === this.$store.state.user.username;
      });
    },
    thisForumUser() {
      const forumUser = {
        forumId: this.thisForum.forumId,
        userId: this.$store.state.user.id,
        isModerator: false,
      };
      return forumUser;
    },
  },
  created() {
    this.$store.commit("SET_ACTIVE_FORUM", this.$route.params.forumName);
    this.getForumUsers;
  },
  methods: {
    getForumUsers() {
      ForumService.getForumUsers().then((response) => {
        this.$store.commit("SET_FORUM_USERS", response.data);
      });
    },
    joinForum() {
      this.$store.commit("ADD_FORUM_USER", this.thisForumUser);
      ForumService.addUserToForum(this.thisForumUser);
      this.getForumUsers();
    },
  },
};
</script>

<style scoped>
#forum-posts {
  align-content: center;
}
.title {
  margin-top: -10px;
  margin-right: 0px;
  margin-bottom: 34px;
  margin-left: 0px;
}
</style>
