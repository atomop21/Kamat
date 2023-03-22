import React, { useState } from 'react'
import { json, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { Button, Card, CardBody } from 'reactstrap'
import { addtocart } from '../services/cartserv'
import { BASE_URL } from '../services/helper'
import { getprodbyid, getprodimg } from '../services/prodservice'
import Navbr from './Navbr'

export default function Product({ item }) {

    const navi =useNavigate();

    const atc=(e,pid)=>{
        
        
        getprodbyid(pid).then(res=>{
            addtocart(res).then((cart)=>{
                localStorage.setItem("cartitems",JSON.stringify(cart))
                window.dispatchEvent(new Event("storage"))
                toast.success("Item Added Successfully")
                navi("/cart")
            })
        })
    }

 
    return (

        <div className='container mt-5'>

            <Card >
                <img src={BASE_URL+"/kamat/prod/image/"+item.image}  width={200} height={250} className="card-img-top" alt="..." />
                <CardBody>
                    <h5> {item.pname} <img src='./kamat/vegan.png' height={20} width={20}/></h5>
                    <h6>Price:{item.price}</h6>
                    <h6>Category: {item.category}</h6>
                    
                        
                        <Button color='success' className='me-3' >Buy now</Button>
                        <Button color='dark' onClick={(e)=>atc(e,item.pid)}> Add to cart</Button>
                  
                </CardBody>
            </Card>


        </div>
    )
}
