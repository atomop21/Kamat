import { privateaxi } from "./helper";

export const getOrders=()=>{
    return privateaxi.get("/kamat/orders").then(resp=>resp.data)
}

export const userorders=(id)=>{
    return privateaxi.get(`/kamat/user/${id}/order`).then(resp=>resp.data)
}