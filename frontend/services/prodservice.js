import {axi, privateaxi } from "./helper";




export const loadprods = (pageno,pagesize) => {
    return axi.get(`/kamat/products?pageno=${pageno}&pagesize=${pagesize}`
    ).then((resp) => resp.data)
}

export const addprod=(prod)=>{
    return privateaxi.post('/kamat/product',prod).then((resp)=> resp.data)
}

export const uploadprod=(image,pid)=>{
    let formData=new FormData()
    formData.append("image",image);
    return privateaxi.post(`/kamat/upload/prod/${pid}`,formData,{
        headers:{
            'Content-Type':"multipart/form-data"
        }
    }).then(res =>res.data)
}

export const delprod=(pid)=>{
    return privateaxi.delete(`/kamat/product/${pid}`).then(resp=> resp.data)
}


export const getprodimg=(image)=>{
    return axi.get(`/kamat/prod/image/${image}`).then((resp) => resp.data)
}

export const getprodbyid=(pid)=>{
    return privateaxi.get( `/kamat/product/${pid}`).then(resp=>resp.data)
}

export const searchprod=(key)=>{
    return privateaxi.get(`/kamat/search/prods/${key}`).then(resp=>resp.data);
}