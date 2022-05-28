package com.alkemy.ong.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3BucketStorageService {

	public String uploadFile(String keyName,MultipartFile multipartFile);
}
