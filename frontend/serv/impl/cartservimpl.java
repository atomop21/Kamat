package com.example.cafe.serv.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cafe.entities.Cart;
import com.example.cafe.payload.productdt;
import com.example.cafe.serv.cartserv;
@Service
public class cartservimpl implements cartserv {
	
	List<Cart> cartitem= new ArrayList<>(); 

	@Override
	public List<Cart> addprod(productdt pdto) {
		Cart oldprod=null;
		
		if(cartitem.isEmpty()==true) {
			
			Cart car=new Cart();
			car.setPid(pdto.getPid());
			car.setPname(pdto.getPname());
			car.setPrice(pdto.getPrice());
			car.setCategory(pdto.getCategory());
			car.setQuantity(1);
			cartitem.add(car);
		}
		else {
				for(Cart c:cartitem) {
					if(c.getPid()==pdto.getPid())
						oldprod=c;
				}
				
				if(oldprod != null) {
					
					oldprod.setQuantity(oldprod.getQuantity()+1);
					
				}
				else {
					
					Cart newcar=new Cart();
					newcar.setPid(pdto.getPid());
					newcar.setPname(pdto.getPname());
					newcar.setPrice(pdto.getPrice());
					newcar.setCategory(pdto.getCategory());
					newcar.setQuantity(1);
					cartitem.add(newcar);
				}
			}
			
		return cartitem;
	}

	@Override
	public List<Cart> deleteprod(Integer pid) {
		// TODO Auto-generated method stub
		for(Cart c:cartitem) {
			if(c.getPid()==pid) {
				cartitem.remove(c);
				break;
			}
		}
		return cartitem;
	}

	@Override
	public List<Cart> increment(Cart c) {
		for(Cart inc :cartitem) {
			if(c.getPid()==inc.getPid()) {
				inc.setQuantity(inc.getQuantity()+1);
				
			}
		}
		return cartitem;
		
	}
	
	@Override
	public List<Cart> decrement(Cart c){
		for(Cart dec:cartitem) {
			if(c.getPid()==dec.getPid() && dec.getQuantity()>1) {
				dec.setQuantity(dec.getQuantity()-1);
			}
			else if(c.getPid()==dec.getPid()&& dec.getQuantity()==1) {
				cartitem.remove(dec);
			}
		}
		return cartitem;
	}
	

	@Override
	public float checkout(List<Cart> cartitem) {
		// TODO Auto-generated method stub
		float totalprice=0;
		for(Cart c:cartitem) {
			float price=0;
			if(c.getQuantity()>1) {
				price=c.getQuantity()*c.getPrice();
				totalprice=totalprice+price;
			}
			else {
				price=c.getPrice();
				totalprice=totalprice+price;
			}
			
		}
		
		return totalprice;
	}
	
	
	
	

}
