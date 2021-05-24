package com.alka.springboot.topic;



import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileServiceController {
	
	@Autowired
	FileService fileService;
	
	@PostMapping("/fileUpload")
	//We can take either @requestBody or @RequestParam
	public FileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName=fileService.storefile(file);
		String contentType=file.getContentType();
		//http://localhost:8080/download/abc.jpg
		//http://localhost:8080:- context path
		String url=ServletUriComponentsBuilder.fromCurrentContextPath().path("/Download/").path(fileName).toUriString();
		//String Url="C:\DHL\GIT\GIT DEV\ENV\java_2017_R3"+fileName;
		FileResponse response=new FileResponse(fileName, url, contentType);
		return response;
	}
	
	@GetMapping("/Download/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {
		
		Resource resource=fileService.downloadFile(fileName);
		String contentType=request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType(contentType)).body(resource);
		//return resource;
		
	}
	
	@PostMapping("/uploadFileToDB")
	public FileResponseToDB uploadFileToDB(@RequestParam MultipartFile file) throws IOException {
		fileService.save(file);
		 return new FileResponseToDB(file.getOriginalFilename(), file.getContentType());
	}
	
	@GetMapping("/downloadFileFromDB")
	public Resource downloadFileFromDB(@PathVariable Integer id) {
		FileUploadDownloadEntity entity=fileService.findById(id);
		Resource resource=new ByteArrayResource(entity.getContent());
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType(entity.getContentType())).body(resource).getBody();
	}
	

}
