package com.alka.springboot.topic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	@Value("${file.storage.location}")
	private String filePath;
	
	private Path fileStoragePath;
	
	@Autowired
	FileRepository fileRepo;
	
	@PostConstruct
	public void postConstruct() {
		fileStoragePath=Paths.get(filePath).toAbsolutePath().normalize();
		try {
			Files.createDirectories(fileStoragePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String storefile(MultipartFile file) {
		String fileName=file.getOriginalFilename();
		Path path=Paths.get(fileStoragePath+"\\"+fileName);
		try {
			Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public Resource downloadFile(String fileName) {
		Path path=Paths.get(fileStoragePath+"\\"+fileName);
		try {
			Resource resource=new UrlResource(path.toUri());
			 return resource;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	public void save(MultipartFile file) throws IOException {
		FileUploadDownloadEntity entity=new FileUploadDownloadEntity(file.getOriginalFilename(), file.getContentType(),
				file.getBytes());
		fileRepo.save(entity);
	}

	public FileUploadDownloadEntity findById(Integer id) {
		return fileRepo.findById(id).get();
	}

}
