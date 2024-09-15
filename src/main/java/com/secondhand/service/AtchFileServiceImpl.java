package com.secondhand.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.secondhand.dao.AtchFileDAO;
import com.secondhand.domain.AtchFileDTO;

@Service
public class AtchFileServiceImpl implements AtchFileService{

	@Inject
	private AtchFileDAO atchFileDao;
	@Inject
	private S3Service s3Service;

	@Override
	public int getMaxAtchFileNo() {
		int result = atchFileDao.getMaxAtchFileNo();
		return result;
	}
	
	@Override
	public List<Map<String, Object>> submitFiles(List<MultipartFile> multipartFileList) throws IOException {
		
		List<Map<String, Object>> filesInfo = new ArrayList<>();

		for (MultipartFile multipartFile : multipartFileList) {
			//첨부파일 하나의 정보 담을 객체 생성.
			Map<String, Object> fileObj = new HashMap<>();			
			//파일 원본 명 가져오기
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			fileObj.put("orgFileNm", uploadFileName);
            // 파일명 지정 (겹치면 안되고, 확장자 빼먹지 않도록 조심!)
			String fileName = UUID.randomUUID() + multipartFile.getOriginalFilename();
            // 파일데이터와 파일명 넘겨서 S3에 저장
			String fileUrl = s3Service.upload(multipartFile, fileName);
            // DB에는 전체 url말고 파일명으로 저장할 것임	((혹시라도 문제 생기면 fileName 말고 fileUrl put하기)
			fileObj.put("fileNm", fileName);
			fileObj.put("fileUrl", fileUrl);
			filesInfo.add(fileObj);
		}

		return filesInfo;
      
	}

	@Override
	public void saveInfo(Map<String, Object> params) {
		String mbrId = (String)params.get("mbrId");
		int atchFileNo = (int)params.get("atchFileNo");
		List<Map<String, Object>> filesInfo = (List<Map<String, Object>>)params.get("filesInfo");
		
		for(Map<String, Object> fileObj: filesInfo) {
			int atchFileSeq = getMaxAtchFileSeq(atchFileNo) + 1;
			
			fileObj.put("mbrId", mbrId);
			fileObj.put("atchFileNo", atchFileNo);
			fileObj.put("atchFileSeq", atchFileSeq);
			atchFileDao.saveInfo(fileObj);
			
		}
		
	}

	@Override
	public int getMaxAtchFileSeq(int atchFileNo) {
		int result = atchFileDao.getMaxAtchFileSeq(atchFileNo);
		return result;
	}

	@Override
	public List<AtchFileDTO> getFiles(Map<String, Object> params) {
		 List<AtchFileDTO> files = atchFileDao.getFiles(params);		
		 return files;
	}

	@Override
	public List<AtchFileDTO> getFileThumbNail() {
		List<AtchFileDTO> files = atchFileDao.getFileThumbNail();
		return files;
	}

	@Override
	public List<AtchFileDTO> getFileThumbNail(Map<String, Object> params) {
		List<AtchFileDTO> files = atchFileDao.getFileThumbNail(params);
		return files;
	}
	
	@Override
	public List<AtchFileDTO> getFileThumbNailSrch(String keyword) {
	    List<AtchFileDTO> files = atchFileDao.getFileThumbNailSrch(keyword);
	    return files;
	}
	
	@Override
	public List<AtchFileDTO> getFileThumbNailByIdlist(Map<String, Object> params) {
		List<AtchFileDTO> files = atchFileDao.getFileThumbNailByIdlist(params);
		return files;
	}
}
