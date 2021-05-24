package com.alka.springboot.topic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<FileUploadDownloadEntity, Integer>{

}
