import axios from "axios";
axios.defaults.baseURL = "http://localhost:9000";

export default {
  getRepliesByPost(postId) {
    return axios.get(`/posts/replies/${postId}`);
  }
};