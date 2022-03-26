package spring.mvc.pj_sch.dao;

import java.util.List;

import spring.mvc.pj_sch.dto.ReviewDTO;

public interface ReviewDAO {
	
	// 전체 리뷰 조회
	List<ReviewDTO> getReviewList(int start, int end);
	
	// 특정 상품 리뷰 DB 조회
	List<ReviewDTO> getReviewList2(int start, int end, int product_no);

	// 특정 상품 리뷰 등록
	int addReview(ReviewDTO dto);

	// 특정 상품 리뷰 DB 삭제
	int deleteReview(int review_no);
	
	// 리뷰 총 개수
	int getReviewAllTotal();

	// 특정상품 리뷰 총 개수
	int getReviewTotal(int product_no);
}