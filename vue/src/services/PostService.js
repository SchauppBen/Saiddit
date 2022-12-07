import axios from "axios";
axios.defaults.baseURL = "http://localhost:9000";

export default {
  getPosts() {
    return axios.get("/");
  },

  getPost(postID) {
    return axios.get(`/posts/${postID}`);
  },

  addPost(post) {
    return axios.post("/posts", post);
  },

  updatePost(post, postID) {
    return axios.put(`/posts/${postID}`, post);
  },

  deletePost(postID) {
    return axios.get(`/posts/${postID}`);
  },
};
