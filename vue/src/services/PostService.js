import axios from "axios";
axios.defaults.baseURL = "http://localhost:9000";

export default {
  getPosts() {
    return axios.get("/");
  },

  getPost(forumId, postId) {
    return axios.get(`/${forumId}/posts/${postId}`);
  },

  addPost(forumName, post) {
    return axios.post(`/${forumName}/posts`, post);
  }
};
