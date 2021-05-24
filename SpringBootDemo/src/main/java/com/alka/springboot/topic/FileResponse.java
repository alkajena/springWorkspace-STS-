package com.alka.springboot.topic;


public class FileResponse {
	 private String fileName;
	 private String Url;
	 private String contentType;
	 
	 
	 
	public FileResponse(String fileName, String url, String contentType) {
		this.fileName = fileName;
		this.Url = url;
		this.contentType = contentType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	 
	 
}
