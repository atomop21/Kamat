import React, { useEffect, useState } from 'react'
import { Card, CardBody, CardHeader, Table } from 'reactstrap'
import { getOrders } from '../services/orderserv'
import Navbr from './Navbr'

export default function Admin_orders() {

const [orders,setOrders]=useState([{

}])

useEffect(()=>{
    getOrders().then(resp=>{
    setOrders(resp)
    })
},[])
    

  return (
    <div >
        <Navbr/>
        <div className='container pt-5'>
        <Card color='dark' >
            <CardHeader className='text-success fs-3 text-center' > ORDERS </CardHeader>
            <CardBody>
            <Table borderless hover className='table-success'>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Email</th>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>

               { orders.map((ord)=>{
                return (
               <tr>
                    <td>{ord?.oid}</td>
                    <td>{ord?.user?.email}</td>
                    <td>{ord.product?.pname}</td>
                    <td>{ord.quantity}</td>
                    <td>{ord.odate}</td>
                </tr> )
                })}
            </tbody>

            </Table>

            </CardBody>
        </Card>
        </div>
    </div>
  )
}
