import axios from "axios";
axios.defaults.baseURL = "http://localhost:9000";

export default {
  getPosts() {
    return axios.get("/");
  },

  addPost(forumName, post) {
    return axios.post(`/${forumName}/posts`, post);
  }
};
