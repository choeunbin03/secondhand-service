package com.secondhand.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.secondhand.domain.AtchFileDTO;

@Repository
public class AtchFileDAOImpl implements AtchFileDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.secondhand.mappers.atchFile";

	@Override
	public int getMaxAtchFileNo() {
		int result = sqlSession.selectOne(namespace + ".getMaxAtchFileNo");
		return result;
	}
	
	@Override
	public int getMaxAtchFileSeq(int atchFileNo) {
		int result = sqlSession.selectOne(namespace + ".getMaxAtchFileSeq", atchFileNo);
		return result;
	}

	@Override
	public void saveInfo(Map<String, Object> fileObj) {
		sqlSession.insert(namespace + ".saveInfo", fileObj);
	}

	@Override
	public List<AtchFileDTO> getFileList() {
		List<AtchFileDTO> result = sqlSession.selectList(namespace + ".getFileList");
		return result;
	}

	@Override
	public List<AtchFileDTO> getFileThumbNail() {
		List<AtchFileDTO> result = sqlSession.selectList(namespace + ".getFileThumbNail");
		return result;
	}

	@Override
	public List<AtchFileDTO> getFileThumbNail(Map<String, Object> params) {
		List<AtchFileDTO> result = sqlSession.selectList(namespace + ".getFileThumbNailByCtgry", params);
		return result;
	}
	
}
