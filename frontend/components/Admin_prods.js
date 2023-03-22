import React, { useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import { Card, CardBody, Table, Button, CardHeader } from 'reactstrap'
import { delprod, loadprods } from '../services/prodservice'
import Navbr from './Navbr'


export default function Admin_prods() {

    const [prods, setProds] = useState([{

    }])

    useEffect(() => {
        loadprods().then((resp) => {
            console.log(resp)
            setProds(resp)
        })

    }, [setProds])

    const deleteprod=(pid)=>{
        delprod(pid).then((resp)=>{
            loadprods().then((data)=>{
                setProds(data)
            })
            toast.success("product deleted successfully")
        })
    }

    const products = prods

    return ( <>
        <Navbr />
        <div className='container pt-5'>
            <Card color='dark'>
                <CardHeader className='bg-dark text-success fw-bold fs-3 text-center'>ALL PRODUCTS</CardHeader>
                <CardBody>
                    <Table borderless hover striped className='table-success'>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Category</th>
                                <th>Price</th>
                                <th>Delete</th>
                            </tr>
                            </thead>

                            {products.map(({ pname, category, price,pid }) => {
                                return (
                                    <tbody>
                                    <tr>
                                        <td>{pname}</td>
                                        <td>{category}</td>
                                        <td>{price}</td>
                                        <td><Button className='btn-sm btn-danger' onClick={(e)=> deleteprod(pid)}>Delete</Button></td>


                                    </tr> </tbody>)
                            })}
                        
                    </Table>
                </CardBody>
            </Card>

        </div>
        </>
    )
}
