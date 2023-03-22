import React, { useEffect, useState } from 'react'
import { Button, Card } from 'reactstrap'
import { getCurrrentUser } from '../auth'
import { BASE_URL } from '../services/helper'
import { userorders } from '../services/orderserv'
import Navbr from './Navbr'

export default function Order() {


  const [orders, setOrders] = useState([{}])
  const [user, setUser] = useState(getCurrrentUser())

  useEffect(() => {
    userorders(user?.id).then(resp => {
      setOrders(resp)
      console.log(resp)
    })
  }, [])

  return (
    <div>
      <Navbr />
      <div className='container pt-5'>
        <div className='row'>
          {orders.map((ord) => {
            return (
              <Card className="card mb-3 col-xs-12 col-sm-8 col-md-6 col-lg-6 ms-3" style={{ maxWidth: 540 }} color="black">
                <div className="row ">
                  <div className="col-md-4">
                    <img src={BASE_URL + "/kamat/prod/image/" + ord?.product?.image} className="img-fluid rounded-start" style={{ height: 150 }} />
                  </div>
                  <div className="col-md-8 text-light">
                    <div className="card-body">
                      <h5 className="card-title">OID{ord?.oid} <Button className='ms-4 btn-danger'>Cancel</Button></h5>
                      <p className="card-text"> {ord?.product?.pname}</p>
                      <p className="card-text"><small className="text-muted">Order Placed on {ord?.odate}</small></p>

                    </div>
                  </div>
                </div>
              </Card>)
          })}

        </div>
      </div>

    </div>
  )
}
