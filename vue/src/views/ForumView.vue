<template>
  <div class="posts">
    <h1 class="title pink-border">Posts in {{ thisForum.name }} forum</h1>
    <h3 v-if="isAMember">You are a member</h3>
    <button type="submit" v-on:click="joinForum()" v-else>Join Forum!</button>
    <h3 @click="sortPosts">{{this.$store.state.sortByMostRecent ? "Sort by most popular posts." : "Sort by most recent posts"}}</h3>
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
      return this.$store.state.forumUsers.filter((forumUser) => {
        return forumUser.forumId === this.thisForum.forumId;
      });
    },
    thisUser() {
      return this.$store.state.user.username;
    },
    isAMember() {
      return this.forumUsers.some((element) => {
        return element.userId === this.$store.state.user.id;
      });
    },
    thisForumUser() {
      const forumUser = {
        forumId: this.thisForum.forumId,
        forumName: this.thisForum.name,
        userId: this.$store.state.user.id,
        username: this.$store.state.user.username,
        isModerator: false,
      };
      return forumUser;
    },
  },
  watch: {
    '$route.params.forumName': { 
      handler: function(newForumName) {
        this.$store.commit("SET_ACTIVE_FORUM", newForumName);
        this.getForumUsers();
        this.getForums();
      },
      deep: true,
      immediate: true
    }
  },
  created() {
    this.$store.commit("SET_ACTIVE_FORUM", this.$route.params.forumName);
    this.getForumUsers();
    this.getForums();
  },
  methods: {
    getForumUsers() {
      ForumService.getForumUsers().then((response) => {
        this.$store.commit("SET_FORUM_USERS", response.data);
      });
    },
    getForums() {
      console.log("triggered get forums method");
      ForumService.getForums().then((response) => {
        this.$store.commit("SET_FORUMS", response.data);
      });
    },
    joinForum() {
      this.$store.commit("ADD_FORUM_USER", this.thisForumUser);
      ForumService.addUserToForum(this.thisForumUser);
    },
    leaveForum() {
      this.$store.commit("REMOVE_FORUM_USER", this.thisForumUser);
    },
    sortPosts() {
      if (this.$store.state.sortByMostRecent) {
        this.sortByMostPopular();
      } else {
        this.sortByMostRecent();
      }
    },
    sortByMostRecent() {
      const currentPosts = this.$store.state.posts;
      currentPosts.sort((post1, post2) => {
        if (post1.timeInMillis > post2.timeInMillis) {
          return -1;
        } else if (post1.timeInMillis < post2.timeInMillis) {
          return 1;
        } else {
          return 0;
        }
      });
      this.$store.commit("TOGGLE_SORTED_POSTS");
    },
    sortByMostPopular() {
      let currentPosts = this.$store.state.posts;
      currentPosts = currentPosts.sort((post1, post2) => {
        if (post1.votes > post2.votes) {
          return -1;
        } else if (post1.votes < post2.votes) {
          return 1;
        } else {
          return 0;
        }
      });
      this.$store.commit("SET_POSTS", currentPosts);
      this.$store.commit("TOGGLE_SORTED_POSTS");
    }
  },

};
</script>

<style scoped>
.inline {
  display: inline-block;
}
#forum-name {
  font-style: italic;
}
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
