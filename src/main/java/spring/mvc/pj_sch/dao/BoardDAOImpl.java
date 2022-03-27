package spring.mvc.pj_sch.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.pj_sch.dto.BoardDTO;
import spring.mvc.pj_sch.dto.ReplyDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override // 게시판 조회
	public List<BoardDTO> getBoardList(int start, int end, String board_category) {
		System.out.println("getBoardList() - dao");
		// 조회할 때 사용할 데이터 맵
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("board_category", board_category);
		return sqlSession.selectList("spring.mvc.pj_sch.dao.BoardDAO.getBoardList", map);
	}

	@Override // 게시판 상세조회
	public BoardDTO getBoardDetail(int board_no) {
		System.out.println("getBoardDetail() - dao");
		return sqlSession.getMapper(BoardDAO.class).getBoardDetail(board_no);
	}

	@Override // 조회수 가져오기
	public int getBoardHits(int board_no) {
		System.out.println("getBoardHits() - dao");
		return sqlSession.getMapper(BoardDAO.class).getBoardHits(board_no);
	}
	
	@Override // 조회수 증가 처리
	public int updateBoardHits(int board_no, int board_hits) {
		System.out.println("updateBoardHits() - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("board_no", board_no);
		map.put("board_hits", board_hits);
		return sqlSession.update("spring.mvc.pj_sch.dao.BoardDAO.updateBoardHits", map);
	}
	
	@Override // 게시판 등록
	public int addBoard(BoardDTO dto) {
		System.out.println("addBoard() - dao");
		return sqlSession.getMapper(BoardDAO.class).addBoard(dto);
	}
	
	@Override // 게시판 간편 등록
	public int simpleAddBoard(BoardDTO dto) {
		System.out.println("simpleAddBoard - dao");
		return sqlSession.getMapper(BoardDAO.class).simpleAddBoard(dto);
	}

	@Override // 게시판 수정
	public int updateBoard(BoardDTO dto) {
		System.out.println("updateBoard() - dao");
		return sqlSession.getMapper(BoardDAO.class).updateBoard(dto);
	}

	@Override // 게시판 삭제
	public int deleteBoard(int board_no) {
		System.out.println("deleteBoard() - dao");
		return sqlSession.getMapper(BoardDAO.class).deleteBoard(board_no);
	}

	@Override // 게시판 총 개수
	public int getBoardTotal(String board_category) {
		System.out.println("getBoardTotal() - dao");
		return sqlSession.getMapper(BoardDAO.class).getBoardTotal(board_category);
	}
	
	@Override // 답변 조회
	public List<ReplyDTO> getReplyList(int board_no) {
		System.out.println("getReplyList() - dao");
		return sqlSession.getMapper(BoardDAO.class).getReplyList(board_no);
	}

	@Override // 답변 등록
	public int addReply(ReplyDTO dto) {
		System.out.println("insertReply() - dao");
		return sqlSession.getMapper(BoardDAO.class).addReply(dto);
	}
	
	@Override // 답변상태 수정
	public int updateBoardState(int board_no, String state) {
		System.out.println("updateBoardState() - dao");
		// 수정할 때 사용할 데이터 맵
		Map<String, Object> map = new HashMap<>();
		map.put("board_no", board_no);
		map.put("state", state);
		return sqlSession.update("spring.mvc.pj_sch.dao.BoardDAO.updateBoardState", map);
	}
	
	@Override // 답변 삭제
	public int deleteReply(int reply_no) {
		System.out.println("deleteReply() - dao");
		return sqlSession.getMapper(BoardDAO.class).deleteReply(reply_no);
	}
}