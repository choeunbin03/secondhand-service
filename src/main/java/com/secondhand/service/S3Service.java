package com.secondhand.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Service {
	
	@Value("${cloud.aws.s3.bucketName}")
	private String bucket;
	
	@Value("${cloud.aws.s3.profilebucketName}")
	private String prbucket;
	
	private final AmazonS3 amazonS3;
	private static final String CONTENT_TYPE = "multipart/formed-data";

	/* 1. 파일 업로드 */
	 public String upload(MultipartFile multipartFile, String s3FileName) throws IOException {
	    	// 메타데이터 생성
	        ObjectMetadata objMeta = new ObjectMetadata();

	        objMeta.setContentLength(multipartFile.getInputStream().available());
	        objMeta.setContentType(CONTENT_TYPE);
			// putObject(버킷명, 파일명, 파일데이터, 메타데이터)로 S3에 객체 등록
	        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);
	       // amazonS3.putObject(new PutObjectRequest(bucket, s3FileName, multipartFile.getInputStream(), objMeta).withCannedAcl(CannedAccessControlList.PublicRead));
			// 등록된 객체의 url 반환 (decoder: url 안의 한글or특수문자 깨짐 방지)
	        return URLDecoder.decode(amazonS3.getUrl(bucket, s3FileName).toString(), "utf-8");
	    }
	 
	    /* 1. 프로필 파일 업로드 */
	    public String prupload(MultipartFile multipartFile, String s3FileName) throws IOException {
	          // 메타데이터 생성
	           ObjectMetadata objMeta = new ObjectMetadata();

	           objMeta.setContentLength(multipartFile.getInputStream().available());
	           objMeta.setContentType(CONTENT_TYPE);
	         // putObject(버킷명, 파일명, 파일데이터, 메타데이터)로 S3에 객체 등록
	           amazonS3.putObject(prbucket, s3FileName, multipartFile.getInputStream(), objMeta);

	           return URLDecoder.decode(amazonS3.getUrl(prbucket, s3FileName).toString(), "utf-8");
	       }
	    
	    /* 2. 프로필파일 삭제 */
	       public void deletepr (String keyName) {
	           try {
	              // deleteObject(버킷명, 키값)으로 객체 삭제
	               amazonS3.deleteObject(prbucket, keyName);
	           } catch (AmazonServiceException e) {
	               log.error(e.toString());
	           }
	       }

	    /* 2. 파일 삭제 */
	    public void delete (String keyName) {
	        try {
	        	// deleteObject(버킷명, 키값)으로 객체 삭제
	            amazonS3.deleteObject(bucket, keyName);
	        } catch (AmazonServiceException e) {
	            log.error(e.toString());
	        }
	    }

	    /* 3. 파일의 presigned URL 반환 */
	    public String getPresignedURL (String keyName) {
	        String preSignedURL = "";
			// presigned URL이 유효하게 동작할 만료기한 설정 (2분)
	        Date expiration = new Date();
	        Long expTimeMillis = expiration.getTime();
	        expTimeMillis += 1000 * 60 * 2;
	        expiration.setTime(expTimeMillis);

	        try {
	        	// presigned URL 발급
	            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, keyName)
	                        .withMethod(HttpMethod.GET)
	                        .withExpiration(expiration);
	            URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
	            preSignedURL = url.toString();
	        } catch (Exception e) {
	            log.error(e.toString());
	        }

	        return preSignedURL;
	    }
	    
	    
	
}

