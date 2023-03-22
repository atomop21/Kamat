import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { Dropdown, Nav, Navbar, NavbarBrand, NavItem, NavLink, DropdownItem, DropdownToggle, UncontrolledDropdown, DropdownMenu, Button, Form, Input } from 'reactstrap'
import { getCurrrentUser, getUserRole, isLogedin, logOut } from '../auth'
import "../App.css"
import { BASE_URL } from '../services/helper'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBoxOpen, faBurger, faCartPlus, faCircleXmark, faGifts, faHouse, faHouseChimneyWindow, faIdBadge, faPlus, faPlusSquare, faSearch, faSquarePlus, faUserSecret, faUserXmark, faXmarksLines } from '@fortawesome/free-solid-svg-icons'
import { searchprod } from '../services/prodservice'

export default function Navbr() {

    const navi = useNavigate();

    const [isOpen, setIsOpen] = useState(false)

    const [login, setLogin] = useState(false)
    const [user, setUser] = useState(undefined)
    const [userole, setUseRole] = useState(getUserRole())
    const [cart, setCart] = useState(JSON.parse(localStorage.getItem("cartitems")))

    const onStorage = () => {
        setCart(JSON.parse(localStorage.getItem("cartitems")))
    }

    useEffect(() => {
        setLogin(isLogedin())
        setUser(getCurrrentUser())
        window.addEventListener("storage", onStorage)
        return () => {
            window.removeEventListener('storage', onStorage)
        }

    }, [])

    const logout = (e) => {
        e.preventDefault();
        logOut(() => {
            setLogin(false);
            navi("/")
        })
    }

    const [key, setkey] = useState("")

    const handlesearch = (e, key) => {
        e.preventDefault()
        searchprod(key).then(resp=>{
           sessionStorage.setItem("search",JSON.stringify(resp))
           console.log(resp)
           window.dispatchEvent(new Event("search"))
           window.scroll(0,600)
        })

    }

    const handlesearchch = (e) => {
        setkey(e.target.value)

        
    }


    return (
        <div className='mb-5 '>
            <Navbar className='mb-5 grad ' fixed='top' >
                <NavbarBrand href="/" className='navicon d-flex  ' style={{ color: "#FF7000" ,borderRadius:"80px"}}> <img src={BASE_URL + "/kamat/logo/6d7c72d2-7d30-4250-aa61-fe62cdf6bdb0.png"} height={40} width={40}  className="mb-"/>
                    Kamat
                    <Link className=' ms-4 mt-1' to="/" style={{ textDecoration: "none", color: "#FF7000" }}>
                        <FontAwesomeIcon icon={faHouse} style={{height:"25px",width:"25px"}}/>
                    </Link>
                    {userole === "ROLE_NORMAL" && (
                        <Link className=' ms-4 mt-1' to="/cart" style={{ textDecoration: "none", color: "#FF7000" }}>
                          <FontAwesomeIcon icon={faCartPlus} style={{height:"25px",width:"25px"}}/>
                            {cart?.length > 0 ? <span className="position-absolute top-0 badge rounded-pill bg-success">{cart?.length}</span> : ""}
                            
                        </Link>)}
                    <div className="ms-3 " style={{ position: "relative" }}>
                        {
                            userole === "ROLE_ADMIN" && (
                                <UncontrolledDropdown nav inNavbar style={{ listStyle: "none" }}>
                                    <DropdownToggle nav caret style={{ textDecoration: "none", color: "#FF7000" }} >
                                        Admin
                                    </DropdownToggle>
                                    <DropdownMenu style={{ backgroundColor: "#FF7000" }} end>
                                        <DropdownItem  ><Link style={{ textDecoration: "none", color: "black" }} to="/user/users"><FontAwesomeIcon icon={faUserSecret} className="me-1" />Users</Link></DropdownItem>
                                        <DropdownItem><Link style={{ textDecoration: "none", color: "black" }} to="/user/products"><FontAwesomeIcon icon={faBurger} className="me-1" />Products</Link></DropdownItem>
                                        <DropdownItem><Link style={{ textDecoration: "none", color: "black" }} to="/user/orders"><FontAwesomeIcon icon={faBoxOpen} className="me-1" />Orders </Link></DropdownItem>
                                        <DropdownItem divider />
                                        <DropdownItem><Link style={{ textDecoration: "none", color: "black" }} to="/user/addprod"><FontAwesomeIcon icon={faPlus} className="me-1" />Add Products</Link></DropdownItem>
                                    </DropdownMenu>
                                </UncontrolledDropdown>

                            )
                        }</div>
                    
                </NavbarBrand>

                <Form className='form-inline d-flex ' onSubmit={(e)=>handlesearch(e,key)} style={{ marginLeft: 10 }} >
                        <Input className="form-control me-2" type="search" placeholder="Search By Category" onChange={(e)=>handlesearchch(e)}/>
                        <Button className="btn btn-outline-success" color='light'><FontAwesomeIcon icon={faSearch} type="submit"/></Button>
                    </Form>
                <Nav >
                    {login && (

                        <UncontrolledDropdown nav inNavbar style={{backgroundColor:"#1A120B"}} className="rounded-pill">
                            <DropdownToggle nav caret style={{ textDecoration: "none", color: "#FF7000" }} >
                                {user?.profilepic == "" ? <img src='./kamat/noprofile.webp' height={40} width={40} style={{ borderRadius: "50%" }} /> : <img src={BASE_URL + "/kamat/user/profile/" + user.profilepic} height={40} width={40} style={{ borderRadius: "50%" }} />} <b >{login ? user.name : "login"}</b>
                            </DropdownToggle>
                            <DropdownMenu style={{ backgroundColor: "#D7E9B9" }} end>
                                <DropdownItem><Link style={{ textDecoration: "none", color: "black" }} to="/user/account"><FontAwesomeIcon icon={faUserSecret} className="fs-5" /> Account</Link></DropdownItem>
                                <DropdownItem><Link style={{ textDecoration: "none", color: "black" }} to="/user/userorders"> <FontAwesomeIcon icon={faBoxOpen} className="fs-6 me-1" />Orders</Link></DropdownItem>
                                <DropdownItem divider />
                                <DropdownItem onClick={(e) => logout(e)}><FontAwesomeIcon icon={faCircleXmark} className="fs-5 me-1" />Logout</DropdownItem>
                            </DropdownMenu>
                        </UncontrolledDropdown>

                    )}
                    {!login && (

                        <>

                            <Link to="/signup" style={{ textDecoration: "none", color: "#FF7000" }}
                                className="p-2 me-2"><h5>Signup </h5></Link>

                            <Button className='btn-lg btn-dark'>
                                <Link to="/login" style={{ textDecoration: "none", color: "#FF7000" }}>Login</Link>
                            </Button>
                        </>
                    )}

                </Nav>
            </Navbar>
        </div>
    )
}
