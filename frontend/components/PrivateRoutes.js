import React from 'react'
import { Navigate, Outlet } from 'react-router-dom'
import { isLogedin } from '../auth'

export const PrivateRoutes =()=>{

    return isLogedin()? <Outlet/>:<Navigate to={"/login"}/>
}