import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem("token");
const currentUser = JSON.parse(localStorage.getItem("user"));

if (currentToken != null) {
  axios.defaults.headers.common["Authorization"] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
    }),
  ],
  state: {
    token: currentToken || "",
    user: currentUser || {},

    // The post that the user is currently viewing
    activePostId: 0,
    // The replies of the post that the user is currently viewing
    activeReplies: [],
    activeForumName: "",
    activeForums: [],
    forumUsers: [],
    searchedForums: [],
    posts: [],
    forums: [],
    searchTerm: "",
    searchedPosts: [],
    sortByMostRecent: true,
  },
  mutations: {
    // Authentication Mutations
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem("token", token);
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem("user", JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      state.token = "";
      state.user = {};
      axios.defaults.headers.common = {};
    },

    // Forum Mutations
    SET_FORUMS(state, data) {
      state.forums = data;
    },
    SET_ACTIVE_FORUM(state, forumName) {
      state.activeForumName = forumName;
    },
    SET_ACTIVE_FORUMS(state, data) {
      state.activeForums = data;
    },
    SET_FORUM_USERS(state, data) {
      state.forumUsers = data;
    },
    SAVE_FORUM(state, forum) {
      state.forums.push(forum);
    },
    ADD_FORUM_USER(state, forumUser) {
      state.forumUsers.push(forumUser);
    },
    SET_SEARCHED_FORUMS(state, data) {
      state.searchedForums = data;
    },

    // Post Mutations
    SET_POSTS(state, data) {
      state.posts = data;
    },
    SAVE_POST(state, post) {
      state.posts.push(post);
    },
    SET_ACTIVE_POST(state, postId) {
      state.activePostId = parseInt(postId);
    },
    SET_SEARCH_TERM(state, searchTerm) {
      state.searchTerm = searchTerm;
    },
    SET_SEARCHED_POSTS(state, posts) {
      state.searchedPosts = posts;
    },
    DELETE_POST(state, postToDelete) {
      state.posts = state.posts.filter((post) => {
        return post.postId != postToDelete.postId;
      });
    },
    TOGGLE_SORTED_POSTS(state) {
      state.sortByMostRecent = !state.sortByMostRecent;
    },

    // Reply Mutations
    SET_ACTIVE_REPLIES(state, data) {
      state.activeReplies = data;
    },
    SAVE_REPLY(state, reply) {
      state.activeReplies.push(reply);
    },
    SET_ACTIVE_NESTED_REPLIES(state, data) {
      state.activeNestedReplies = data;
    },
    DELETE_REPLY(state, replyToDelete) {
      state.activeReplies.forEach((reply) => {
        if (reply.replyId == replyToDelete.replyId) {
          reply.deleted = true;
        }
      });
    },
  },
});
