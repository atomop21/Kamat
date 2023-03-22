import { faCartShopping, faDeleteLeft, faIndianRupee, faTrash } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon, } from '@fortawesome/react-fontawesome'
import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { Button, Card, CardBody, CardHeader, Table } from 'reactstrap'
import { getCurrrentUser } from '../auth'
import { addtocart, carcheckout, deccart, increcart, ordercheck, remcart } from '../services/cartserv'
import Allproducts from './Allproducts'
import Navbr from './Navbr'
import { BASE_URL } from '../services/helper'
import { getprodbyid, loadprods } from '../services/prodservice'
import Lottie from 'lottie-react'
import cancel from '../anime/cancel.json'


export default function Cart() {

    const navi = useNavigate()
    const [user, setUser] = useState(getCurrrentUser())
    const [checkout, setCheckout] = useState()
    const [cart, setCart] = useState(JSON.parse(localStorage.getItem("cartitems")))
    const [products,setProducts]=useState([{}])

    useEffect(() => {
        const onStorage=()=>{
            setCart(JSON.parse(localStorage.getItem("cartitems")))
            window.scroll(0,0)
        }

        setCart(JSON.parse(localStorage.getItem("cartitems")))
        loadprods().then(resp=>{
            setProducts(resp)
        })
        window.addEventListener("storage",onStorage);
       

    }, [setCart])

    let cartitems = cart

    const handleinc = (e, incitem) => {
        increcart(incitem).then(resp => {
            localStorage.setItem("cartitems", JSON.stringify(resp))
            window.dispatchEvent(new Event("storage"))
            setCart(resp)
        })

    }

    const handledec=(e,decitem)=>{
        deccart(decitem).then(resp=>{
            localStorage.setItem("cartitems",JSON.stringify(resp))
            window.dispatchEvent(new Event("storage"))
            setCart(resp)
        })
    }

    const handleremcart = (e, pid) => {
        remcart(pid).then(resp => {
            localStorage.setItem("cartitems", JSON.stringify(resp))
            window.dispatchEvent(new Event("storage"))
            setCart(resp)
        })
    }

    console.log(cartitems)

    carcheckout(cartitems).then((resp) => {
        setCheckout(resp)
    })

    const handleordcheckout = (e, cartitems, id) => {
        ordercheck(cartitems, id).then(resp => {
            console.log(resp)
            localStorage.removeItem("cartitems");
            window.dispatchEvent(new Event("storage"));
            toast.success("Order Placed!!")

            setCart([{}])
        })
    }

    const atc = (e, pid) => {
        getprodbyid(pid).then(resp => {
            addtocart(resp).then(cart => {
                localStorage.setItem("cartitems", JSON.stringify(cart))
                setCart(cart)
                window.dispatchEvent(new Event("storage"))
                toast.success("Added Successfully")
            })
        })
    }

    return (
        <div>
            <Navbr />

            <div className='container pt-5'>
                {cartitems!=""?<><Card color='dark' className='mb-3'>
                    <CardHeader className='text-center fw-bold fs-3' style={{color:"#FBFACD"}}>CART ITEMS</CardHeader>

                    <CardBody>
                        <Table borderless style={{backgroundColor:"#F8FFDB"}} hover>
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Category</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Order</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            {cartitems?.map((cartitm) => {
                                return (
                                    <tbody>
                                        <tr>
                                            <td>{cartitm.pname}</td>
                                            <td>{cartitm.category}</td>
                                            <td>{cartitm.price}</td>
                                            <td><Button className='btn-sm btn-dark me-2 round-0' onClick={(e)=>handledec(e,cartitm)}>-</Button>{cartitm.quantity}<Button className='btn-sm ms-2 btn-dark round-0' style={{ width: 23, textAlign: "center" }} onClick={(e) => handleinc(e, cartitm)}>+</Button></td>
                                            <td><Button color='success' className='btn-sm'>Order</Button></td>
                                            <td><Button color='warning' className='btn-sm' onClick={(e) => handleremcart(e, cartitm.pid)}><FontAwesomeIcon icon={faTrash} className="me-1"/>Remove</Button></td>
                                        </tr>
                                    </tbody>)
                            })}

                        </Table>
                    </CardBody>
                </Card>

                <nav className="navbar navbar-expand-lg navbar-light bg-dark ">
                    <div className="container-fluid">
                        <h3 className='text-info'> <FontAwesomeIcon icon={faCartShopping} className="me-2 " />
                            Total Price </h3> <h3 className='ms-auto text-light'>{checkout} <FontAwesomeIcon icon={faIndianRupee} /></h3> <Button className='btn-success btn-sm ms-3 pt-2' onClick={(e) => handleordcheckout(e, cartitems, user?.id)} >Checkout</Button>

                    </div>
                </nav></>:<>
                <Card  color='dark' className='d-flex text-center'>
                    <Lottie animationData={cancel} style={{ height:"400px",width:"400px",marginLeft:350}}/>
                    <h1 style={{color:"whitesmoke"}}> Your Cart Is Empty!! </h1>
                    <Button className='btn btn-lg btn-success mb-5' style={{height:40,width:180,marginLeft:460}}><Link to="/" style={{textDecoration:"none",color:"beige"}}>Order Now</Link></Button>
                </Card>
                </>

}


            </div>
           <Allproducts/>
        </div>
    )
}
