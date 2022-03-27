package spring.mvc.pj_sch.dao;

import java.util.List;

import spring.mvc.pj_sch.dto.BoardDTO;
import spring.mvc.pj_sch.dto.ReplyDTO;

public interface BoardDAO {

	// 게시판 총 개수
	int getBoardTotal(String board_category);
	
	// 게시판 조회
	List<BoardDTO> getBoardList(int start, int end, String board_category);
	
	// 게시판 상세조회
	BoardDTO getBoardDetail(int board_no);

	// 조회수 가져오기
	int getBoardHits(int board_no);
	
	// 조회수 증가
	int updateBoardHits(int board_no, int board_hits);
	
	// 게시글 답변 조회
	List<ReplyDTO> getReplyList(int board_no);
	
	// 게시글 등록
	int addBoard(BoardDTO dto);
	
	// 게시글 간편 등록
	int simpleAddBoard(BoardDTO dto);

	// 게시판 수정
	int updateBoard(BoardDTO dto);
	
	// 게시판 삭제
	int deleteBoard(int board_no);

	// 게시글 답변 등록
	int addReply(ReplyDTO dto);

	// 게시글 답변상태 수정
	int updateBoardState(int board_no, String state);
	
	// 게시글 답변 삭제
	int deleteReply(int reply_no);

}