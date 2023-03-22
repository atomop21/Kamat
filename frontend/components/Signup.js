import React, { useEffect, useState } from 'react'
import { Card, CardBody, CardHeader, Col, Container, FormGroup, Input, Label, Row, Form, Button, FormFeedback } from 'reactstrap'
import Navbr from './Navbr'
import { uploadprofpic, userotp, usersign } from '../services/userservice'
import { toast } from 'react-toastify'
import '../App.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCamera, faUpload } from '@fortawesome/free-solid-svg-icons'
import { useNavigate } from 'react-router-dom'
import { doLogin } from '../auth'

export default function Signup() {

    const navi = useNavigate();
    const [uotp, setUotp] = useState("")

    const [userdata, setUserData] = useState({
        name: "",
        email: "",
        pass: "",
        phone: "",
        gender: "",
        profilepic: ""
    })


    const handleChange = (event, proper) => {
        setUserData({ ...userdata, [proper]: event.target.value })

    }
    const resetData = () => {
        setUserData({
            name: "",
            email: "",
            pass: "",
            phone: "",
            gender: "",
        })
    }
{/** 
    const [profilepic, setProfilePic] = useState("")


    const handleprofpic = (e) => {
        setProfilePic(e.target.files[0]);

    }
**/}

    const [error, setError] = useState({
        errors: {},
        isError: "",
    })

    const submitForm = (eve) => {
        eve.preventDefault();

        if (error.isError) {
            toast.error("Fill all the mandatory feilds")
            setError({ ...error, isError: false })
            return;
        }

        userotp(userdata.email).then(resp => {
            let otp = resp;
            console.log(otp)
            sessionStorage.setItem("otp",JSON.stringify(otp));
            localStorage.setItem("data",JSON.stringify(userdata))
            navi("/user/verifyotp")
           
                   {/**  usersign(userdata).then((resp) => {
                        console.log(resp)
                        doLogin(resp, () => {
                            console.log("data 1")
                        })**/}

                       {/**  uploadprofpic(profilepic, resp?.user?.id).then(data => {
                            console.log(data);

                            const userobj = JSON.parse(localStorage.getItem("data"))
                            userobj.user.profilepic = data?.img

                            localStorage.setItem("data", JSON.stringify(userobj))

                            toast.success("User registered successfully")
                            navi("/")

                        })**/}

{/** 
                        setUserData({
                            name: "",
                            email: "",
                            pass: "",
                            phone: "",
                            gender: ""
                        })

                    }).catch((error) => {
                        console.log(error)
                        console.log("error")

                        setError({

                            errors: error,
                            isError: true
                        })
                    })**/}
            
                
        })

    }

    return (

        <div >
            <Navbr className='mb-5' />

            <Container className='pt-5'>
                <Row>
                    <Col sm={{ size: 6, offset: 3 }}>
                        <Card color='black' >
                            <CardHeader className='text-light'>
                                <h3>Register</h3>
                            </CardHeader>
                            <CardBody>
                                <Form onSubmit={submitForm}>
                                   {/**  <FormGroup floating className='upload' style={{ textAlign: "center" }}>
                                        <img src='./kamat/noprofile.webp' height={100} width={100} />
                                        <div className='round'>
                                            <Button>
                                                <FontAwesomeIcon icon={faCamera} style={{ color: "#fff" }} />
                                                <Input type='file' onChange={(e) => handleprofpic(e)} />
                                            </Button>
                                        </div>

                                        <FormFeedback></FormFeedback></FormGroup>
                                    

                                    <h5 className='text-light text-center' >Profile Photo</h5>**/}

                                    <FormGroup floating>

                                        <Input type='text' placeholder='Name' id='name' name='name1'
                                            onChange={(e) => handleChange(e, "name")} value={userdata.name} invalid={error.errors?.response?.data?.name ? true : false} />
                                        <Label for='name1'>Name</Label>
                                        <FormFeedback>{error.errors?.response?.data?.name}</FormFeedback>
                                    </FormGroup>
                                    <FormGroup floating>

                                        <Input type='email' placeholder='Email' id='email' name='email'
                                            onChange={(e) => handleChange(e, "email")} value={userdata.email} invalid={error.errors?.response?.data?.email ? true : false} />
                                        <Label for='email'>Email</Label>
                                        <FormFeedback>{error.errors?.response?.data?.email}</FormFeedback>

                                    </FormGroup>
                                    <FormGroup floating>

                                        <Input type='password' placeholder='password' id='pass' name='pass'
                                            onChange={(e) => handleChange(e, "pass")} value={userdata.pass} invalid={error.errors?.response?.data?.pass ? true : false} />
                                        <Label for='pass'>Password</Label>
                                        <FormFeedback>{error.errors?.response?.data?.pass}</FormFeedback>

                                    </FormGroup>
                                    <FormGroup floating>

                                        <Input type='phone' placeholder='Phone' id='phone' name='phone'
                                            onChange={(e) => handleChange(e, "phone")} value={userdata.phone} />
                                        <Label for='phone'>Phone</Label>
                                    </FormGroup>
                                    <FormGroup floating>

                                        <Input type="text" placeholder='Gender' name='gender'
                                            onChange={(e) => handleChange(e, "gender")} value={userdata.bday} />
                                        <Label for='gender'>Gender</Label>
                                    </FormGroup>
                                    <Container>
                                        <Button type='submit' color='info'>Register</Button>
                                        <Button type='reset' onClick={resetData} color='danger' className='ms-2'>Reset</Button>
                                    </Container>

                                </Form>
                            </CardBody>
                        </Card>
                    </Col>
                </Row>
            </Container>


        </div>
    )
}
