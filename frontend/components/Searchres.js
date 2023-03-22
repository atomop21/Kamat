import { faSearch } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import React, { useEffect, useState } from 'react'
import Product from './Product'

export default function Searchres() {

    const [search, setSearch] = useState(JSON.parse(sessionStorage.getItem("search")))

    useEffect(() => {
        const onsearch=()=>{
            setSearch(JSON.parse(sessionStorage.getItem("search")))
        }
        setSearch(JSON.parse(sessionStorage.getItem("search")))
        
        window.addEventListener("search",onsearch);

    }, [setSearch])

    return (
        <div className='container mt-4'>
           { search?.length>0? <h3 style={{backgroundColor:"bisque"}} className="pb-3 pt-3 text-center" >Search Results<FontAwesomeIcon icon={faSearch} className="ms-2"/></h3>:""}
            <div className='row'>
                {search!=""?search?.map((prod)=><div className='col-xs-12 col-sm-8 col-md-6 col-lg-4'><Product item={prod} key={prod.pid} /></div>):""}
            </div>
        </div>
    )
}
