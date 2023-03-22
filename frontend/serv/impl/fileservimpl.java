package com.example.cafe.serv.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.cafe.serv.fileserv;

@Service
public class fileservimpl implements fileserv {
	
	

	@Override
	public String uploadfile(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		String name =file.getOriginalFilename();
		String randuuid=UUID.randomUUID().toString();
		String newname=randuuid.concat(name.substring(name.lastIndexOf(".")));
		
		String fullpath=path+File.separator+newname;
		
		File fil=new File(path);
		
		
		
		if(!fil.exists()) {
			fil.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(fullpath));
		
		
		return newname;
	}

	@Override
	public InputStream getres(String path, String fname) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fullpath=path+File.separator+fname;
		InputStream is=new FileInputStream(fullpath);
		
		return is;
	}

	@Override
	public String delpic(Path path) throws IOException {
		// TODO Auto-generated method stub
		Files.deleteIfExists(path);
		
		return "deleted successfully";
	}

}
