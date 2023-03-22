package com.example.cafe.serv.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.cafe.dao.proddao;
import com.example.cafe.entities.Product;
import com.example.cafe.exceptions.resourcenotfoundexception;
import com.example.cafe.payload.prodresp;
import com.example.cafe.payload.productdt;
import com.example.cafe.serv.productserv;

@Service
public class prodimpl implements productserv{
	
	@Autowired
	private proddao pdao;
	
	@Autowired
	private ModelMapper mmaper;

	

	@Override
	public productdt getproduct(Integer pid) {
		// TODO Auto-generated method stub
		Product pd=this.pdao.findById(pid).orElseThrow(()-> new resourcenotfoundexception("product", "id", pid));
		
		return this.mmaper.map(pd, productdt.class);
	}

	@Override
	public productdt addproduct(productdt pdt) {
		// TODO Auto-generated method stub
		Product pd=this.mmaper.map(pdt, Product.class);
		Product createddt=this.pdao.save(pd);
		return this.mmaper.map(createddt, productdt.class);
	}

	@Override
	public productdt updateproduct(productdt pdt, Integer pid) {
		// TODO Auto-generated method stub
		Product pd=this.pdao.findById(pid).orElseThrow(()-> new resourcenotfoundexception("product", "id", pid));
		pd.setPname(pdt.getPname());
		pd.setPid(pdt.getPid());
		pd.setPrice(pdt.getPrice());
		pd.setCategory(pdt.getCategory());
		pd.setImage(pdt.getImage());
		Product updpd=this.pdao.save(pd);
		return this.mmaper.map(updpd, productdt.class);
	}

	@Override
	public void deleteprod(Integer pid) {
		// TODO Auto-generated method stub
		Product pd=this.pdao.findById(pid).orElseThrow(()-> new resourcenotfoundexception("product", "id", pid));
		this.pdao.delete(pd);
	}

	@Override
	public prodresp getallproducts(Integer pageno,Integer pagesize,String sortby) {
		// TODO Auto-generated method stub
		
		Pageable p=PageRequest.of(pageno, pagesize,Sort.by(sortby));
		
		Page<Product> pageprods= this.pdao.findAll(p);
		List<Product> prods=pageprods.getContent();
		List<productdt> proddt=prods.stream().map(pr-> this.mmaper.map(pr,productdt.class)).collect(Collectors.toList());
		prodresp presp=new prodresp();
		presp.setProds(proddt);
		presp.setPageno(pageprods.getNumber());
		presp.setPagesize(pageprods.getSize());
		presp.setTotalprods(pageprods.getTotalElements());
		presp.setTotalpages(pageprods.getTotalPages());
		presp.setLastpage(pageprods.isLast());
		return presp;
	}

	@Override
	public List<productdt> searchprods(String key) {
		// TODO Auto-generated method stub
		List<Product> prods=this.pdao.findByCategoryContaining(key);
		List<productdt> prodts=prods.stream().map(prod->this.mmaper.map(prod, productdt.class)).collect(Collectors.toList());
		return prodts;
	}

	
}
