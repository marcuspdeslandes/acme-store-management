import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8082/acme/store/v1/persist/",
  headers: {
    "Content-type": "application/json"
  }
});
