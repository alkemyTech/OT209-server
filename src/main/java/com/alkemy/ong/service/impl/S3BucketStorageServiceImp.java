package com.alkemy.ong.service.impl;

import com.alkemy.ong.service.S3BucketStorageService;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.devicefarm.model.ArgumentException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3BucketStorageServiceImp implements S3BucketStorageService {

	private Logger logger = LoggerFactory.getLogger(S3BucketStorageService.class);

	@Autowired
	private AmazonS3 amazonS3Client;

	@Value("${amazonProperties.bucketName}")
	private String bucketName;
	
	@Value("${multipartfile.maxsize}")
	private String imageMaxSize;

	/**
	 * Upload file into AWS S3
	 *
	 * @param file name
	 * @param file to upload on S3 Bucket
	 * @return String URL for the image uploaded
	 * 
	 */
	public String uploadFile(String keyName, MultipartFile file) {
		if (verifyFileSize(file)) {
			throw new MaxUploadSizeExceededException(Integer.parseInt(imageMaxSize));
		}
		if (verifyFileExtension(file)) {
			throw new ArgumentException("File extension must be '.png' .'jpg' '.jpeg'");
		}
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(file.getSize());
			amazonS3Client.putObject(bucketName, keyName, file.getInputStream(), metadata);
			return amazonS3Client.getUrl(bucketName, file.getName()).toString();
		} catch (IOException ioe) {
			logger.error("IOException: " + ioe.getMessage());
		} catch (AmazonServiceException serviceException) {
			logger.info("AmazonServiceException: " + serviceException.getMessage());
			throw serviceException;
		} catch (AmazonClientException clientException) {
			logger.info("AmazonClientException Message: " + clientException.getMessage());
			throw clientException;
		}
		return "File not uploaded: " + keyName;
	}

	private boolean verifyFileSize(MultipartFile file) {
		return !(file.getContentType().equals("image/jpeg") || 
				file.getContentType().equals("image/png") || 
				file.getContentType().equals("image/jpg"));
	}

	private boolean verifyFileExtension(MultipartFile file) {
		return file.getSize() > Integer.parseInt(imageMaxSize);
	}
}
