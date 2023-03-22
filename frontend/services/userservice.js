import {axi, privateaxi} from "./helper"



export const usersign=(user)=>{
    return axi.post("/kamat/register",user).then((resp) => resp.data);
}

export const login=(logindet)=>{
    return axi.post('/kamat/auth/login',logindet).then((res)=> res.data)
}

export const getusers=()=>{
    return privateaxi.get('/kamat/users').then((resp)=>resp.data)
}

export const deluser=(id)=>{
    return privateaxi.delete(`/kamat/${id}`).then(resp=>resp.data)
}

export const uploadprofpic=(image,id)=>{
    let formdata=new FormData()
    formdata.append("profimage",image);
    return privateaxi.post(`/kamat/user/profilepic/${id}`,formdata,{
        headers:{
            "Content-type":"multipart/form-data"
        }
    }).then(resp=>resp.data)
}


export const userotp=(email)=>{
    return axi.post(`/kamat/sendotp?email=${email}`).then(resp=>resp.data)
}