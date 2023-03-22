import React, { useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import { Button, Card, CardBody, CardHeader, Form, FormGroup, Input, Label } from 'reactstrap'
import { addprod, loadprods, uploadprod } from '../services/prodservice'
import Navbr from './Navbr'


export default function Productform() {

    const [error,setError]=useState({
        errors:{},
        isError:""
    })

    useEffect(() => {
        loadprods().then((resp) => {

            console.log(resp)
        }).catch(err => {
            console.log(err)

        })
    }, [])

    const [prod, setProd] = useState({
        pname: "",
        category: "",
        price: ""
      
    })
    const[image,setImage]=useState("null")

    const handleImage=(e)=>{
        setImage(e.target.files[0])
    }

    const handleChange = (e, proper) => {
        
            setProd({ ...prod, [proper]: e.target.value })
    }

    const handleForm = (eve) => {
        eve.preventDefault();
        
        addprod(prod).then((resp)=>{
            console.log(resp)
           uploadprod(image,resp.pid).then((resp)=>{
                toast.success("product added successfully");
                console.log(resp)
            }).catch(err=>console.log(err))
            
        })
        console.log(prod.image)
        console.log(prod)
    }

    const handlereset=(e)=>{
        setProd({
            pname: "",
            category: "",
            price: ""
        })
        setImage("")
    }

    return (
        <div >
           <div className='pb-5'> <Navbr /></div>

            <Card className='shawdow-0 container mt-6 pt-3' color='dark'>
                <CardBody>
                    <CardHeader className='text-light fs-3 text-center'> PRODUCT DETAILS</CardHeader>
                    <Form onSubmit={(e) => handleForm(e)}>
                        <FormGroup>
                            <Label> Name</Label>
                            <Input type='text' name='pname' id='pname' placeholder='Enter name'
                                onChange={e => handleChange(e, "pname")} value={prod.pname} />
                        </FormGroup>
                        <FormGroup>
                            <Label> Category</Label>
                            <Input type='text' name='pcategory' id='pcategory' placeholder='Enter Category'
                                onChange={e => handleChange(e, "category")} value={prod.category} />
                        </FormGroup>
                        <FormGroup>
                            <Label>Price</Label>
                            <Input type='number' name='price' id='price' placeholder='Enter Price'
                                onChange={e => handleChange(e, "price")} value={prod.price} />
                        </FormGroup>
                        <FormGroup>
                            <Label>Image</Label>
                            <Input type='file' name='pimg' id='pimg' placeholder='select'
                                onChange={e => handleImage(e)} />
                        </FormGroup>
                        <FormGroup style={{ textAlign: "center" }}>
                            <Button color='success' className='btn-lg' style={{ color: "black" }}>Add Product</Button>
                            <Button color='danger' className='btn-lg ms-3' style={{ color: "black" }} onClick={(e)=>handlereset(e)}>Reset</Button>

                        </FormGroup>
                    </Form>

                </CardBody>
            </Card>

        </div>
    )
}
