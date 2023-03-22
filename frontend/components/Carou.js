import React from 'react'

export default function Carou() {
    return (
        <div className=' mt-5 ' style={{alignItems:'center'}} >

            <div className='pt-5 '  >
                <div id="carouselExampleFade" className="carousel slide carousel-fade" data-bs-ride="carousel">
                    <div className="carousel-inner " id='car-items'>
                        <div className="carousel-item active" data-bs-interval="5000">
                            <img src="./kamat/kamat1.png" className="d-block w-100"  alt="..."height={600} />
                        </div>
                        <div className="carousel-item">
                            <img src="./kamat/kamat2.png" className="d-block w-100" alt="..." height={600}/>
                        </div>
                        <div className="carousel-item">
                            <img src="./kamat/kamat3.png" className="d-block w-100" alt="..." height={600}/>
                        </div>
                        <div className="carousel-item">
                            <img src="./kamat/kamat4.png" className="d-block w-100" alt="..." height={600}/>
                        </div>
                        
                    </div>
                    <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span className="visually-hidden">Previous</span>
                    </button>
                    <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
                        <span className="carousel-control-next-icon" aria-hidden="true"></span>
                        <span className="visually-hidden">Next</span>
                    </button>
                </div>

            </div>

        </div>
    )
}
