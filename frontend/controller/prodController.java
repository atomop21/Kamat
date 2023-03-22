package com.example.cafe.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import com.example.cafe.payload.Fileresp;
import com.example.cafe.payload.prodresp;
import com.example.cafe.payload.productdt;
import com.example.cafe.payload.response;
import com.example.cafe.serv.fileserv;
import com.example.cafe.serv.productserv;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/kamat")
@CrossOrigin(origins = "http://localhost:3000")
public class prodController {
	
	@Autowired
	private productserv prodser;
	
	@Autowired
	private fileserv fserv ;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/product")
	public ResponseEntity<productdt> addprod(@Valid @RequestBody productdt pdt){
		productdt proddt=this.prodser.addproduct(pdt);
		return new ResponseEntity<productdt>(proddt,HttpStatus.CREATED);
	}
	
	@PutMapping("/product/{pid}")
	public ResponseEntity<productdt> updateprod(@Valid @RequestBody productdt pdt,@PathVariable Integer pid){
		productdt proddt=this.prodser.updateproduct(pdt, pid);
		return new  ResponseEntity<productdt>(proddt,HttpStatus.OK);
	}
	
	@DeleteMapping("/product/{pid}")
	public ResponseEntity<response> deletrprod(@PathVariable Integer pid){
		this.prodser.deleteprod(pid);
		return new ResponseEntity<response>(new response("product deleted successfully ",true),HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<prodresp> getallproducts(
			@RequestParam(value = "pageno" ,defaultValue = "0", required = false)Integer pageno,
			@RequestParam(value = "pagesize" ,defaultValue = "12",required = false) Integer pagesize,
			@RequestParam(value = "sortby" ,defaultValue = "category" ,required = false) String sortBy){
			prodresp presp=this.prodser.getallproducts(pageno,pagesize,sortBy);
			return new ResponseEntity<prodresp>(presp,HttpStatus.OK);
	}
	
	@GetMapping("/product/{pid}")
	public ResponseEntity<productdt> getproduct(@PathVariable Integer pid){
		productdt pdt=this.prodser.getproduct(pid);
		return new ResponseEntity<productdt>(pdt,HttpStatus.OK);
	}

	@PostMapping("/upload/prod/{pid}")
	public ResponseEntity<Fileresp> uploadprod(@RequestParam("image") MultipartFile image,@PathVariable Integer pid) throws IOException{
		productdt pdt=this.prodser.getproduct(pid);
		String fname=this.fserv.uploadfile(path, image);
		pdt.setImage(fname);
		productdt npdt=this.prodser.updateproduct(pdt, pid);
		return new ResponseEntity<Fileresp>(new Fileresp(npdt.getImage(),"added successfully",npdt),HttpStatus.OK);
		
	}
	
	@GetMapping(value="/prod/image/{imgname}",produces =MediaType.IMAGE_JPEG_VALUE )
	public void servimage(@PathVariable("imgname") String imgname, HttpServletResponse rep) throws IOException {
		InputStream is=this.fserv.getres(path, imgname);
		rep.setContentType(MediaType.IMAGE_JPEG_VALUE);
	  StreamUtils.copy(is, rep.getOutputStream());
	}
	
	@GetMapping("/search/prods/{key}")
	public ResponseEntity<List<productdt>> searchprods(@PathVariable String key){
		List<productdt> pdt=this.prodser.searchprods(key);
		return new ResponseEntity<List<productdt>>(pdt,HttpStatus.OK);
	}
}
