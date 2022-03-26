package spring.mvc.pj_sch.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.pj_sch.dto.SalesDTO;
import spring.mvc.pj_sch.dto.TotalDTO;

@Repository
public class SalesDAOImpl implements SalesDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override // 결산 조회
	public List<SalesDTO> getSalesList(int start, int end) {
		System.out.println("getSalesList() - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("spring.mvc.pj_sch.dao.SalesDAO.getSalesList", map);
	}
	
	@Override // 결산 내역 개수 조회
	public int getSalesTotal() {
		System.out.println("getSalesTotal() - dao");
		return sqlSession.getMapper(SalesDAO.class).getSalesTotal();
	}
	
	@Override // 카테고리 총 합계 조회
	public List<TotalDTO> getSalesCategory() {
		System.out.println("getSalesCategory() - dao");
		return sqlSession.selectList("spring.mvc.pj_sch.dao.SalesDAO.getSalesCategory");
	}
}