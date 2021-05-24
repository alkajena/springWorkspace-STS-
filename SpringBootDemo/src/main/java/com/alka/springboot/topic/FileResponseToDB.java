package com.alka.springboot.topic;

public class FileResponseToDB {
	
	private String FileName;
	private String contentType;
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public FileResponseToDB(String fileName, String contentType) {
		super();
		FileName = fileName;
		this.contentType = contentType;
	}
	
	

}
