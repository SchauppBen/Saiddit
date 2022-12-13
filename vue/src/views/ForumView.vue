<template>
  <div class="posts">
    <h1 class="title pink-border">Posts in {{ thisForum.name }} forum</h1>
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
      return this.$store.state.forumUsers;
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
      let joinedForum = {
        forumId: this.thisforum.forumId,
        userId: this.$store.state.user.userId,
      };
      ForumService.addUserToForum(joinedForum);

      joinedForum.forumId = "";
      joinedForum.userId = "";
    },

    isUser() {
      return this.forumUsers.contains(this.$store.state.user.username);
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
