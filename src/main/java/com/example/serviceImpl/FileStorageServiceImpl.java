package com.example.serviceImpl;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.entities.FileUploadEntity;
import com.example.exceptionHandling.FileStorageException;
import com.example.exceptionHandling.MyFileNotFoundException;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.properties.FileStorageProperties;
import com.example.repository.FileStorageRepository;
import com.example.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService{
	
	private final Path fileStorageLocation;
	
	
	@Autowired
	public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {

		this.fileStorageLocation=Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
			
		
		try {

			Files.createDirectories(this.fileStorageLocation);

		} catch (Exception ex) {

			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);

		}

	}
	
	
	@Autowired
	private FileStorageRepository fileStorageRepository;

	//upload file 
	@Override
	public FileUploadEntity storeFile(MultipartFile file, String type, HttpServletRequest request) {
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		
	try {
		
		if (fileName.contains("..")) {

			throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);

		}
		File pathAsFile = new File(this.fileStorageLocation + "/" + type);

		if (!Files.exists(Paths.get(this.fileStorageLocation + "/" + type))) {

			pathAsFile.mkdir();

		}
		Path targetLocation = this.fileStorageLocation.resolve(type + "/" + fileName);
		Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		FileUploadEntity fileUploadEntity=new FileUploadEntity();
		
		fileUploadEntity.setFileName(fileName);
		fileUploadEntity.setOriginalName(fileName);
		fileUploadEntity.setSize(file.getSize());
		fileUploadEntity.setType(type);
		
		FileUploadEntity entity=fileStorageRepository.save(fileUploadEntity);
		return entity;
	}catch (IOException e) {
		throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
	}
	
}

	
	@Override
	public Resource loadFileAsResource(String fileName) throws MyFileNotFoundException {

		try {

			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());

			if (resource.exists()) {

				return resource;

			} else {

				throw new MyFileNotFoundException("File not found ");

			}

		} catch (MalformedURLException ex) {

			throw new MyFileNotFoundException("File not found");

		}

	}
	
	@Override
	public String getFolderName(String type) throws ResourceNotFoundException {

		String folderPath = "";

		switch (type) {

		case "images":
			folderPath = "images";
			break;

		case "test":
			folderPath = "test";
			break;

		case "products":
			folderPath = "products";
			break;

		default:
			throw new ResourceNotFoundException("Invalid Upload Type");

		}

		return folderPath;

	}

	
}
