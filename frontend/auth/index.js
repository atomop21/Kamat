

export const doLogin=(data,next)=>{
    localStorage.setItem("data",JSON.stringify(data));
    next();
}


export const isLogedin=()=>{
    let data=localStorage.getItem("data")
    if(data==null){
        return false;
    }
    else{ return true;}
}

export const logOut=(next)=>{
    localStorage.removeItem("data");
    localStorage.removeItem("cartitems");
    sessionStorage.removeItem("search")
    next();
}

export const getCurrrentUser=()=>{
    if(isLogedin()){
        return JSON.parse(localStorage.getItem("data")).user;

    }else return undefined;
}

export const getTok=()=>{
    if(isLogedin){
      return  JSON.parse(localStorage.getItem("data")).token
    }else{
      return  null;
    }
}

export const getUserRole=()=>{
    if(isLogedin()){
        return JSON.parse(localStorage.getItem("data"))?.userole?.name;
    }
    else{
        return "empty";
    }
}