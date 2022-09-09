package com.example.dto;

public class UploadFileResponse {

	public UploadFileResponse() {

		super();

		// TODO Auto-generated constructor stub
	}

	public UploadFileResponse(Long id, String fileName, String path) {

		super();
		this.id = id;
		this.fileName = fileName;
		this.path = path;

	}

	private Long id;

	private String fileName;

	private String path;

	public Long getId() {

		return id;

	}

	public void setId(Long id) {

		this.id = id;

	}

	public String getFileName() {

		return fileName;

	}

	public void setFileName(String fileName) {

		this.fileName = fileName;

	}

	public String getPath() {

		return path;

	}

	public void setPath(String path) {

		this.path = path;

	}

}
