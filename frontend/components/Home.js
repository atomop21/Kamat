import React, { useEffect, useState } from 'react'
import { Card, Container, Navbar, NavbarBrand, NavItem, Pagination, PaginationItem, PaginationLink } from 'reactstrap'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUserPlus } from '@fortawesome/free-solid-svg-icons'
import { faInstagramSquare, faFacebookSquare, faYoutubeSquare, faTwitterSquare, faSquareTwitter } from '@fortawesome/free-brands-svg-icons'
import Carou from './Carou'
import { Link, useNavigate } from 'react-router-dom'
import "../App.css"
import { getCurrrentUser, getUserRole } from '../auth'
import Navbr from './Navbr'
import Allproducts from './Allproducts'
import Productform from './Productform'
import Searchres from './Searchres'



export default function Home() {

  const [userrole, setUserrole] = useState(getUserRole())
  const [user, setUser] = useState(getCurrrentUser())
  const navi = useNavigate();
  const [search,setSearch]=useState(JSON.parse(sessionStorage.getItem("search")))
  useEffect(()=>{
    const onsearch=()=>{
      setSearch(JSON.parse(sessionStorage.getItem("search")))
    }
    window.addEventListener("search",onsearch);
  },[setSearch])
  



  return (
    <div style={{alignContent:"center"}} >
      <Navbr  />
      |<Carou/>
      {search?.length>0?<Searchres />:""}
      {userrole === 'ROLE_ADMIN' ? <Productform/> : <Allproducts />}

     
      <div className='container'>
        <hr style={{ border: "none", height: 5, color: "#FDE8CB", backgroundColor: "#FDE8CB" }} /></div>

      <div className=' icons-big mt-3 ' style={{ textAlign: "center" }}>
        <a style={{ color: "#D7E9B9" }} className="me-4" href='https://twitter.com/ATOM08549251'>
          <FontAwesomeIcon icon={faSquareTwitter} />
        </a>
        <a style={{ color: "#D7E9B9" }} className="me-4" href="https://www.instagram.com/localhost_3_0_0_0/" rel='noopener'>
          <FontAwesomeIcon icon={faInstagramSquare} />
        </a>
        <a style={{ color: "#D7E9B9" }} className="me-4" href='https://youtube.com/@atomgaming1432'>
          <FontAwesomeIcon icon={faYoutubeSquare} />
        </a>
        <a style={{ color: "#D7E9B9" }} className="me-4" href="https://www.facebook.com/kitty.kittu.319247" rel='noopener'>
          <FontAwesomeIcon icon={faFacebookSquare} />
        </a>
      </div>
      <div className='container'>
        <hr className='mt-4' style={{ height: 5, color: "#FDE8CB", border: "none", backgroundColor: "#FDE8CB" }} />
      </div>
      <Card className='container mt-2' color='dark'>
        <div className='row mt-5' style={{ color: "#FBFACD" }}>
          <div className='col-xs-12 col-sm-8 col-md-6 col-lg-4 mb-5 '>
            <h6 >About Us</h6>
            <Link to="/cafemenu" style={{ color: "#D7E9B9", textDecoration: "none" }}>Menu</Link><br />
            <Link to="/customercare" style={{ color: "#D7E9B9", textDecoration: "none" }}>Customer Care</Link>
            <Link to="/starters" style={{ color: "#D7E9B9", textDecoration: "none" }}>Awards</Link><br />
            <Link to="/starters" style={{ color: "#D7E9B9", textDecoration: "none" }}>Contact Us</Link><br />
            <Link to="/starters" style={{ color: "#D7E9B9", textDecoration: "none" }}>News</Link><br />

          </div>
          <div className='col-xs-12 col-sm-8 col-md-6 col-lg-4 mb-5 '>
            <h6>Cafe Menu</h6>
            <Link to="/starters" style={{ color: "#D7E9B9", textDecoration: "none" }}>Starters</Link><br />
            <Link to="/starters" style={{ color: "#D7E9B9", textDecoration: "none" }}>Meals</Link><br />
            <Link to="/starters" style={{ color: "#D7E9B9", textDecoration: "none" }}>Tiffins</Link><br />
            <Link to="/starters" style={{ color: "#D7E9B9", textDecoration: "none" }}>Chillers</Link><br />
            <Link to="/starters" style={{ color: "#D7E9B9", textDecoration: "none" }}>Chats</Link><br />


          </div>
          <div className='col-xs-12 col-sm-8 col-md-6 col-lg-4 mb-5 '>
            <h6>Buisness</h6>
            <Link to="/starters" style={{ color: "#D7E9B9", textDecoration: "none" }}>Advertise With Us</Link><br />
            <Link to="/starters" style={{ color: "#D7E9B9", textDecoration: "none" }}>Enquiry</Link><br />
            <Link to="/starters" style={{ color: "#D7E9B9", textDecoration: "none" }}>Outdoor Catering Service</Link><br />
          </div>

        </div>

      </Card>
      <div className='container' style={{ color: "#E8F3D6" }}>
        <hr className='mt-4' style={{ height: 5, color: "#FDE8CB", border: "none", backgroundColor: "#FDE8CB" }} />

        <Card className='mb-3' color='dark'>&copy; kamat Tiffins all rights reserved</Card>
      </div>

    </div>
  )
}
