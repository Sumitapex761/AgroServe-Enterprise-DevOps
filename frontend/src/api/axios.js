

import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080", // <-- Your backend base URL
});

// Add Authorization token if needed later
API.interceptors.request.use((req) => {
  const token = localStorage.getItem("token");
  if (token) req.headers.Authorization = `Bearer ${token}`;
  return req;
});

// --- API Methods ---
export const getServices = () => API.get(`/services`);
export const getBookings = (userId) => API.get(`/bookings/${userId}`);
export const createBooking = (booking) => API.post("/bookings", booking);
export const signup = (userData) => API.post("/users/signup", userData);
export const login = (credentials) => API.post("/users/signin", credentials);

export default API;
