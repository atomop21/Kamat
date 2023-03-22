package com.example.cafe.serv;


import java.util.List;

import com.example.cafe.payload.prodresp;
import com.example.cafe.payload.productdt;


public interface productserv {
	
	 prodresp getallproducts(Integer pageno,Integer pagesize,String sortby);
	 productdt getproduct(Integer pid);
	 productdt addproduct(productdt pdt);
	 productdt updateproduct(productdt pdt,Integer pid);
	 void deleteprod(Integer pid); 
	 List<productdt> searchprods(String key);
}
