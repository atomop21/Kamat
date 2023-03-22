import axios from "axios";

export const BASE_URL='http://localhost:9090';

export const axi=axios.create({
    baseURL:BASE_URL
})


export const privateaxi=axios.create({
    baseURL:BASE_URL,
    
})



 privateaxi.interceptors.request.use((config)=>{
    config.headers['Authorization']=`Bearer ${JSON.parse(localStorage.getItem("data"))?.token}`;
    return config;
}, error=>{
    return Promise.reject(error);
})