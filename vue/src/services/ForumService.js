import axios from "axios";
axios.defaults.baseURL = "http://localhost:9000";

export default {
  getForums() {
    return axios.get("/forums/");
  },

  getActiveForums() {
    return axios.get("/forums/home/");
  },

  getForum(forumName) {
    return axios.get(`/${forumName}/`);
  },

  addForum(forum) {
    return axios.post("/forums/", forum);
  },
};
