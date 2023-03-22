import React, { useState } from 'react'
import Cropper from 'react-easy-crop'
import { Button, Input } from 'reactstrap';

export default function ImageCropper({ image, oncropdone, oncropcancel }) {

    const [crop, setCrop] = useState({ x: 0, y: 0 })
    const [zoom, setZoom] = useState(1);
    const [croppedArea, setCroppedArea] = useState(null);
    const [aspectRatio, setAspectRatio] = useState(4 / 3)

    const oncomplete = (croppedAreaPercentage, croppedAreaPixels) => {
        setCroppedArea(croppedAreaPixels);
    }

    const onAspectRatioChange = (e) => {
        setAspectRatio(e.target.value)
    }

    return (
        <div className='cropper'>
            <div> <Cropper image={image} aspect={aspectRatio} crop={crop} zoom={zoom} 
            onZoomChange={setZoom}
            onCropChange={setCrop}
                onCropComplete={oncomplete}
                style={{
                    containerStyle: {
                        width: "100%",
                        height: "80%",
                        backgroundColor: "#fff"
                    }
                }} />
            </div>
            <div className='action-btns'>
                <div className='aspect-ratios' onChange={onAspectRatioChange}>
                    <Input type='radio' value={1/1} name="ratio" />1:1
                    <Input type='radio' value={5/4} name="ratio" />5:4
                    <Input type='radio' value={4/3} name="ratio" />4:3
                    <Input type='radio' value={3/2} name="ratio" />3:2
                    <Input type='radio' value={5/3} name="ratio" />5:3
                    <Input type='radio' value={16/9} name="ratio" />16:9
                    <Input type='radio' value={1/1} name="ratio" />3:1
                </div>
                <Button className=' btn-outline btn-danger' onClick={oncropcancel}>Cancel</Button>
                <Button className='btn-outline btn-success' onClick={()=>{
                    oncropdone(croppedArea);
                }}> Done</Button>

            </div>

        </div>
    )
}
