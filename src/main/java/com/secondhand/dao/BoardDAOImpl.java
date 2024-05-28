package com.secondhand.dao;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.secondhand.domain.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Inject
	private SqlSession sqlSession;
	@Inject
	private MemberDAO memberDao;
	
	private static String namespace = "com.secondhand.mappers.board";
	
	@Override
	public List<BoardDTO> getBbsList() {
		List<BoardDTO> bbsList = sqlSession.selectList(namespace + ".getBbsList");
		return bbsList;
	}

	@Override
	public List<BoardDTO> getBbsList(Map<String, Object> param) {
		List<BoardDTO> bbsList = sqlSession.selectList(namespace + ".getBbsListByCtgry", param);
		return bbsList;
	}

	@Override
	public BoardDTO getBbsView(Map<String, Object> param) {
		BoardDTO bbsList = (BoardDTO) sqlSession.selectList(namespace + ".getBbsView", param).get(0);
		return bbsList;
	}

<<<<<<< Updated upstream
=======
	@Override
	public void bbsRegi(BoardDTO board) {
		sqlSession.insert(namespace+".bbsRegi",board);
	}

	@Override
    public List<BoardDTO> getBbsListByKeyword(String keyword) {
        return sqlSession.selectList(namespace + ".searchBbsList", keyword);
    }
	
	@Override
    public List<BoardDTO> getBbsListByBMK(String mbrId) {
		HashMap<String, String> param = new HashMap<String, String>();
		List<String> BMKList = memberDao.getBMK(mbrId);
		List<BoardDTO> bbsLists = new ArrayList<BoardDTO>();
		String bbsIdList = "("+String.join(",",BMKList)+")";
		if(!bbsIdList.equals("()")) {
			param.put("bbsIdList",bbsIdList);
			bbsLists = sqlSession.selectList(namespace + ".searchBbsListbyBbsIdList", param);
		}
		return bbsLists;
    }
	
	@Override
    public List<BoardDTO> getBbsListByRecentViewed(String mbrId){
		HashMap<String, String> param = new HashMap<String, String>();
		List<String> RecentViewedList = memberDao.getRecentViewed(mbrId);
		List<BoardDTO> bbsLists = new ArrayList<BoardDTO>();
		String bbsIdList = "("+String.join(",",RecentViewedList)+")";
		if(!bbsIdList.equals("()")) {
			param.put("bbsIdList",bbsIdList);
			bbsLists = sqlSession.selectList(namespace + ".searchBbsListbyBbsIdList", param);
		}
		return bbsLists;
    	
    }
	
	@Override
	public void deleteBoard(int bbsId) {
		sqlSession.delete("deleteBoard",bbsId);
	}
>>>>>>> Stashed changes
}
