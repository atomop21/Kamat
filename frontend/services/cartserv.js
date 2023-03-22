import { faExplosion } from "@fortawesome/free-solid-svg-icons"
import { privateaxi } from "./helper"

export const addtocart=(product)=>{
    return privateaxi.post("/kamat/addtocart",product).then(resp=> resp.data)
}


export const increcart=(item)=>{
    return privateaxi.post("/kamat/cart/inc",item).then(resp=>resp.data)
}

export const remcart=(pid)=>{
    return privateaxi.delete(`/kamat/remcart/${pid}`).then(resp=>resp.data)
}

export const carcheckout=(cart)=>{
    return privateaxi.post('/kamat/cart/checkout',cart).then(resp=>resp.data)
}

export const ordercheck=(cart,id)=>{
    return privateaxi.post(`/kamat/checkout/order/${id}`,cart).then(resp=>resp.data)
}

export const deccart=(cart)=>{
    return privateaxi.post('/kamat/cart/dec',cart).then(resp=>resp.data)
}