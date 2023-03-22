import React, { useState } from 'react'
import { Card, CardBody, CardHeader, Form, FormGroup, Input, Label, Row, Col, Button } from 'reactstrap'
import Navbr from './Navbr'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFacebookSquare } from '@fortawesome/free-brands-svg-icons'
import "../App.css"
import { Link, Outlet, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { login } from '../services/userservice'
import { doLogin, isLogedin } from '../auth'

export default function Login() {

    const navi =useNavigate()

    const [loginDetails, setLoginDetails] = useState({
        username: "",
        pass: ""
    })

    const handleChange = (eve, proper) => {
        let actual = eve.target.value
        setLoginDetails({
            ...loginDetails, [proper]: actual
        })
    }

    const handleForm = (e) => {
        e.preventDefault();
        console.log(loginDetails)

        if (loginDetails.pass.trim() == '' || loginDetails.username.trim() == '') {
            toast.error("Enter username and password");
            return;
        }
        login(loginDetails).then((data) => {
            console.log(data);
            doLogin(data, () => {
                console.log("data is saved")
            })
            if(data.userole.name==="ROLE_ADMIN"){
                navi("/user/Admin")
            }
            else{
                navi("/")
            }


            toast.success("Login Success!")
        }).catch(err => {
            console.log(err);
            if (err.response.status == 401) {
                toast.error("invalid password")
            }
            if (err.response.status == 404) {
                toast.error("User not found! Please check your email")
            }
        })

    }

    const handleReset = () => {
        setLoginDetails({
            username: "",
            pass: ""
        })
    }
    return (
        <div>
            <div className='mb-5'><Navbr /></div>
            <div className='container '>
                <Row>
                    <Col sm={{ size: 6, offset: 3 }}>
                        <Card color='black' className='mt-5'>
                            <CardHeader className='text-center text-light  ' style={{ background: "#97DECE" }}> <h3>Login</h3></CardHeader>
                            <CardBody>
                                <Form onSubmit={(e) => handleForm(e)}>

                                    
                                    <FormGroup floating className='mt-3'>

                                        <Input type='email' placeholder='Email' id='email' name='email'
                                            value={loginDetails.username} onChange={(e) => handleChange(e, "username")} />
                                        <Label for='email'>Email</Label>
                                    </FormGroup>
                                    <FormGroup floating>

                                        <Input type='password' placeholder='password' id='pass' name='pass'
                                            value={loginDetails.pass} onChange={(e) => handleChange(e, "pass")} />
                                        <Label for='pass'>Password</Label>
                                    </FormGroup>
                                    <FormGroup className='text-center'>
                                        <Button color='info' className='btn-lg'>Submit</Button>
                                    </FormGroup>
                                    <hr style={{ color: "white" }} />
                                    <FormGroup className='text-center log-icon d-flex justify-content-center'>

                                        <Button color='info' className='btn-lg ' outline>Continue with Google</Button>
                                    </FormGroup>
                                    <FormGroup className='text-center '>
                                        <h6 className='text-light'> Don't have an account? <Link to="/signup" style={{ textDecoration: "none", color: "gray" }}>Sign up</Link></h6>

                                    </FormGroup>
                                    <hr style={{ color: "white" }} />

                                </Form>
                            </CardBody>
                        </Card>
                    </Col>
                </Row>
            </div>
        </div>
    )
}
