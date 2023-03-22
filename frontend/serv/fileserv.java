package com.example.cafe.serv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface fileserv {

	public String uploadfile(String path, MultipartFile file) throws IOException;
	InputStream getres(String path,String fname) throws FileNotFoundException;
	public String delpic(Path path) throws IOException;
}
