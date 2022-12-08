import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

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
  state: {
    token: currentToken || "",
    user: currentUser || {},

    // The post that the user is currently viewing
    activePostId: 0,
    // The replies of the post that the user is currently viewing
    activeReplies: [],

    posts: [],
    forums: []
  },
  mutations: {
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
    SET_POSTS(state, data) {
      state.posts = data;
    },
    SAVE_POST(state, post) {
      state.posts.push(post);
    },
    SET_ACTIVE_POST(state, postId) {
      state.activePostId = postId;
    },
    SET_ACTIVE_REPLIES(state, data) {
      state.activeReplies = data;
    },
    SET_FORUMS(state, data) {
      state.forums = data;
    }
  }
});
