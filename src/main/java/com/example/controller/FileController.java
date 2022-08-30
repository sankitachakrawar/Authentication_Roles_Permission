package com.example.controller;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.dto.ErrorResponseDto;
import com.example.dto.SuccessResponseDto;
import com.example.dto.UploadFileResponse;
import com.example.entities.FileUploadEntity;
import com.example.exceptionHandling.MyFileNotFoundException;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.service.FileStorageService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/uploadFile")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(defaultValue = "") String type, HttpServletRequest request) {

		FileUploadEntity fileDetail=new FileUploadEntity();
		
		try {
			
		fileStorageService.storeFile(file, type, request);
	} catch (ResourceNotFoundException e) {

		return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "invalidUploadType"), HttpStatus.BAD_REQUEST);

	}

	return new ResponseEntity<>(new SuccessResponseDto("File Uploaded Successfully", "fileUploadSuccessfully",new UploadFileResponse(fileDetail.getId(), fileDetail.getFileName(), type)),HttpStatus.CREATED);
	
		
	}	
	
	
	@GetMapping("/downloadFile/{path}/{fileName:.+}")
	public ResponseEntity<?> downloadFile(@PathVariable String path, @PathVariable String fileName, HttpServletRequest request) {

		Resource resource = null;

		// Load file as Resource
		try {

			resource = fileStorageService.loadFileAsResource(path + "/" + fileName);

		} catch (MyFileNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "fileNotFound"), HttpStatus.NOT_FOUND);

		}

		// Try to determine file's content type
		String contentType = null;

		try {

			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

		} catch (IOException ex) {

			System.out.print("Could not determine file type.");

		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {

			contentType = "application/octet-stream";

		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"").body(resource);

	}
	
}
