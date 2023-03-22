import React, { useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import { Button, Card, CardBody, CardHeader, Table } from 'reactstrap'
import { deluser, getusers } from '../services/userservice'
import Navbr from './Navbr'

export default function Admin_users() {

    const [prods, setProds] = useState([{

    }])
    let Users = prods;


    const deleteUser = (e, id) => {
        deluser(id).then((resp) => {
            toast.success("user deleted successfully")
            getusers().then(data=>{
                console.log(data)
                setProds(data)
            })
            
            
        })
        
    }

    useEffect(() => {
        getusers().then((data) => {
            console.log(data)
            setProds(data)

        })

    }, [setProds])


    
    


    return (
        <>
        <Navbr />
        <div className='container pt-5'>
            <Card color='dark'>
                <CardHeader className='bg-dark text-success fw-bold fs-3 text-center'>Registered Users</CardHeader>
                <CardBody >
                    <Table striped hover borderless className='table-success'>
                        
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            {Users.map(({ name, email, phone, id }) => {
                                return (
                                    <tbody>
                                    <tr>
                                        <td>{name}</td>
                                        <td>{email}</td>
                                        <td>{phone}</td>
                                        <td><Button className='btn-sm btn-danger' onClick={(e) => deleteUser(e, id)}>Delete</Button></td>


                                    </tr></tbody>)
                            })}
                        
                        
                    </Table>
                </CardBody>
            </Card>
        </div>
        </>
    )
}
