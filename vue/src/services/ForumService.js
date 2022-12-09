import axios from "axios";
axios.defaults.baseURL = "http://localhost:9000";

export default {
  getForums() {
    return axios.get("/forums/");
  },

  getForum(forumName) {
    return axios.get(`/${forumName}/`);
  },

  addForum(forum) {
    return axios.post("/forums/", forum);
  }
};
