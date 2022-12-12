import axios from "axios";
axios.defaults.baseURL = "http://localhost:9000";

export default {
  getForums() {
    return axios.get("/forums/");
  },

  getActiveForums() {
    return axios.get("/forums/home/");
  },
  getForumUsers() {
    return axios.get("/forums/users/");
  },

  getForum(forumName) {
    return axios.get(`/${forumName}/`);
  },

  addForum(forum) {
    return axios.post("/forums/", forum);
  },

  searchForums(searchTerm) {
    return axios.get(`/forums/search/${searchTerm}`);
  },
};
