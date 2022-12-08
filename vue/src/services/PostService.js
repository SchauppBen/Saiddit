import axios from "axios";
axios.defaults.baseURL = "http://localhost:9000";

export default {
  getPosts() {
    return axios.get("/posts/");
  },

  getPost(forumId, postId) {
    return axios.get(`/${forumId}/posts/${postId}`);
  },

  addPost(post) {
    return axios.post(`/forums/posts/`, post);
  },
};
