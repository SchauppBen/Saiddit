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

    posts: [
      {
        id: 1,
        title: "Check out this duck",
        text: "lol",
        media:
          "https://a57.foxnews.com/static.foxnews.com/foxnews.com/content/uploads/2022/09/720/405/ducks-1.png?ve=1&tl=1",
        datetime: "2022-12-05 8:00:00",
        forumId: 1,
        username: "ThomASS",
      },
      {
        id: 2,
        title: "Example2",
        text: "HAHAHA",
        media:
          "https://www.thedrive.com/uploads/2022/05/27/Ram-Heavy-Hero.jpg?auto=webp&auto=webp&optimize=high&quality=70&width=1920",
        datetime: "2022-12-05 9:00:00",
        forumId: 1,
        username: "Kevbitch",
      },
      {
        id: 3,
        title: "ThomASS is a fraud",
        text: "User ThomASS tried to scam me yesterday, don't interact with him, we should get a moderator to ban him.",
        media: "",
        datetime: "2022-12-06 10:31:21",
        forumId: 1,
        username: "Ben Schitt"
      },
    ],
    post: {
      id: "",
      title: "",
      text: "",
      media: "",
      datetime: "",
      forumId: "",
      userId: "",
    },
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
    SET_ACTIVE_POST(state, postId) {
      state.activePostId = postId;
    }
  },
});
