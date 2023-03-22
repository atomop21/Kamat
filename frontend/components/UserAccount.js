import { faUserEdit } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import React, { useEffect, useState } from 'react'
import { Button, Card, CardBody, CardHeader, CardText, Input, Label, Table } from 'reactstrap'
import { getCurrrentUser } from '../auth'
import { BASE_URL } from '../services/helper'
import ImageCropper from './ImageCropper'
import Navbr from './Navbr'

export default function UserAccount() {

    const [image,setImage]=useState()
    useEffect(()=>{
        console.log(image)

    },[setImage])

    
    const [imgAfterCrop,setImgAfterCrop]=useState("")
    const [user,setUser]=useState(getCurrrentUser())

    const oncropdone=(imgcroppedarea)=>{
        const canele=document.createElement("canvas");
        canele.width=imgcroppedarea.width;
        canele.height=imgcroppedarea.height;

        const con=canele.getContext("2d")
        let imob=new Image();
        imob.src=image;
        imob.onload=function (){
            con.drawImage(
                imob,
                imgcroppedarea.x,
                imgcroppedarea.y,
                imgcroppedarea.width,
                imgcroppedarea.height,
                0,
                0,
                imgcroppedarea.width,
                imgcroppedarea.height
            )
            const dataim=canele.toDataURL("image/jpeg")
            setImgAfterCrop(dataim)
        }
    }

    const oncropcancel=()=>{
        setImage("")
    }

    return (
    <>
    <Navbr/>
    <div className='container text-light pt-5 text-center'>
         <Card color='dark' >
            <CardHeader className='fs-1 text-info' style={{backgroundColor:"#495579"}}> Profile</CardHeader>
            <CardBody>
            {user?.profilepic===""?<FontAwesomeIcon icon={faUserEdit} style={{borderRadius:"50%",color:"whitesmoke",height:"200px",width:"200px",marginLeft:"50px"}} />
            :<img src={`${BASE_URL}/kamat/user/profile/${user.profilepic}`} height={200} width={200} style={{borderRadius:"50%"}}/>}<br/>
            <Input type='file' id="actbtn" onChange={(e)=>setImage(e.target.files[0])} hidden/>
            <Label htmlFor='actbtn' className='hover mt-1' style={{cursor:'pointer'}}>{ user?.profilepic===''?"Add Picture":"Edit Picture"}</Label>
            {/**<ImageCropper image={image} oncropcancel={oncropcancel} oncropdone={oncropdone}/>**/}
            
            <CardText className='fs-4 mt-3'>{user?.name}</CardText>
           

            <Table borderless hover striped className='mt-5 table-success'>
                <tbody style={{textAlign:"left"}}> 
                    
                    <tr className='mt-3'><td> Email</td> <td> {user?.email}</td></tr>
                    <tr className='mt-3'> <td> Password`</td><td> <Button color='info'>Change Password</Button></td></tr>
                    <tr className='mt-3'> <td> Phone`</td><td> {user?.phone}</td></tr>
                    <tr className='mt-3'> <td> Gender</td><td> {user?.gender}</td></tr>
                </tbody>
            </Table>
            <Button className='mt-1 btn-info mb-3'> Edit Profile</Button>

            </CardBody>

         </Card>

    </div>
    </>
  )
}
