import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { Button, Card, Form, FormGroup, Input, Label } from 'reactstrap'
import { doLogin } from '../auth';
import { usersign } from '../services/userservice';

export default function Verifyotp() {

    const navi = useNavigate();

    const [uotp, setUotp] = useState("")

    const handleChange = (e) => {
        setUotp(e.target.value);
    }

    const handleverifyotp = () => {
        let otp = sessionStorage.getItem("otp")
        if (otp === uotp) {
            let user = JSON.parse(localStorage.getItem("data"));
            usersign(user).then(resp => {
                doLogin(resp, () => {
                    navi('/')
                    toast.success("User Registered Successfully")
                })

            })
        }
    }

    return (
        <div className=' container pt-5  textver' >
            <Card color='dark' className='text-light' style={{width:400 }}>
                <Form onSubmit={handleverifyotp()}>
                    <div className="alert mt-3 alert-success" role="alert">
                        Please Chack your Gmail
                    </div>
                    <FormGroup>
                        <Label for='otp'> Enter Otp </Label>
                        <Input type='number' name="otp" id='otp' onChange={(e) => handleChange(e)} />
                    </FormGroup>
                    <FormGroup>
                        <Button className='btn-success' type='submit'> Verify</Button>
                    </FormGroup>
                </Form>
            </Card>
        </div>
    )
}
