package spring.mvc.pj_sch.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.pj_sch.dto.ReviewDTO;

@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override // 전체 리뷰 조회
	public List<ReviewDTO> getReviewList(int start, int end) {
		System.out.println("getReviewList() 전체조회 - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("spring.mvc.pj_sch.dao.ReviewDAO.getReviewList", map);
	}
	
	@Override // 특정 상품 리뷰 DB 조회
	public List<ReviewDTO> getReviewList2(int start, int end, int product_no) {
		System.out.println("selectReview() 일부조회 - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("product_no", product_no);
		return sqlSession.selectList("spring.mvc.pj_sch.dao.ReviewDAO.getReviewList2", map);
	}

	@Override // 특정 상품 리뷰 등록
	public int addReview(ReviewDTO dto) {
		System.out.println("addReview - dao");
		return sqlSession.getMapper(ReviewDAO.class).addReview(dto);
	}
	
	@Override // 특정 상품 리뷰 DB 삭제
	public int deleteReview(int review_no) {
		System.out.println("deleteReview() - dao");
		return sqlSession.getMapper(ReviewDAO.class).deleteReview(review_no);
	}
	
	@Override // 리뷰 총 개수
	public int getReviewAllTotal() {
		System.out.println("getReviewAllTotal() - dao");
		return sqlSession.getMapper(ReviewDAO.class).getReviewAllTotal();
	}

	@Override // 특정상품 리뷰 총 개수
	public int getReviewTotal(int product_no) {
		System.out.println("getReviewTotal() - dao");
		return sqlSession.getMapper(ReviewDAO.class).getReviewTotal(product_no);
	}
}