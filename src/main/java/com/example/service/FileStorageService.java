package com.example.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import com.example.entities.FileUploadEntity;
import com.example.exceptionHandling.MyFileNotFoundException;
import com.example.exceptionHandling.ResourceNotFoundException;

public interface FileStorageService {

	public FileUploadEntity storeFile(MultipartFile file,String type,HttpServletRequest request);
	
	public Resource loadFileAsResource(String fileName) throws MyFileNotFoundException;

}
