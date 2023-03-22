package com.example.cafe.serv;

import java.util.List;

import com.example.cafe.entities.Cart;
import com.example.cafe.payload.orderdto;
import com.example.cafe.payload.productdt;

public interface orderserv {
	
	orderdto createord(orderdto odt,Integer prod_id, Integer usr_id);
	orderdto updateord(orderdto odt,Integer oid);
	void deleteord(Integer oid);
	List<orderdto> getallorders(Integer pageno ,Integer pagesize,String s);
	orderdto getorder(Integer oid);
	List<orderdto> getordbyusr(Integer uid);
	List<orderdto> getordbyprod(Integer pid);
	List<orderdto> searchord(String key);
	List<orderdto> checkord(List<Cart> pdt,Integer uid);
	
}
