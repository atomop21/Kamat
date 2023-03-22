import { faHandMiddleFinger } from '@fortawesome/free-solid-svg-icons'
import { hover } from '@testing-library/user-event/dist/hover'
import React, { useEffect, useState } from 'react'
import {PaginationItem,Pagination,PaginationLink, Container } from 'reactstrap'
import { loadprods } from '../services/prodservice'
import Product from './Product'


export default function Allproducts() {

    const [products, setProducts] = useState({
        prods:[],
        totalpages:"",
        totalprods:"",
        pagesize:"",
        lastpage:false,
        pageno:""
    })

    useEffect(() => {
        loadprods(0,12).then((resp) => {
            setProducts(resp)
            console.log(resp)
        })
    }, [setProducts])


    const handlechange=(pageno,pagesize=12)=>{
        
        loadprods(pageno,pagesize).then(resp=>{
            setProducts(resp)
            window.scroll(0,700)
        })
    }


    return (
        <>
            <div className='pb-2' >

                <div className='container mt-2'  >
                    <div className='row mt-3 text-center' >
                        {products?.prods?.length > 0 ? products?.prods.map((prod) => <div className='col-xs-12 col-sm-8 col-md-6 col-lg-4'><Product item={prod} key={prod.pid} /></div>) : "No Products"}

                    </div>
                </div>
            </div>
            <div className='container textc mt-5' >
                <Pagination size='lg' color='dark'>
                    <PaginationItem onClick={()=>handlechange(products?.pageno-1)} disabled={products.pageno==0} >
                        <PaginationLink previous style={{backgroundColor:"black" , color:"#FBFACD",border:"none"}} >
                            Prev
                        </PaginationLink>
                    </PaginationItem>
                  {
                    [...Array(products?.totalpages)].map((item,index)=>(

                    <PaginationItem onClick={()=>handlechange(index)} active={index==products.pageno} >
                        <PaginationLink style={{backgroundColor:"black" , color:"#FBFACD",border:"none"}}>
                            {index+1}
                        </PaginationLink>
                    </PaginationItem>
                    ))
                    }

                    <PaginationItem onClick={()=> handlechange(1+products?.pageno)} disabled={products.lastpage==true}>
                        <PaginationLink next style={{backgroundColor:"black" , color:"#FBFACD",border:"none"}}>
                            Next
                        </PaginationLink>
                    </PaginationItem>
                </Pagination>
            </div>
        </>
    )
}
