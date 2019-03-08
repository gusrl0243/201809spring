package kr.or.ddit.ranger.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("rangerDao")
public class RangerDaoImpl implements IRangerDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	private List<String> rangers;

	public RangerDaoImpl() {
		rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		rangers.add("moon");
		rangers.add("james");
	}

	/**
	 * 전체 레인저스 조회(임의의 값)
	 */
	@Override
	public List<String> getRangers() {
		
		return rangers;
	}

	@Override
	public String getRanger(int listIndex) {
		//0-4
		//0보다 작은 값 : 0 (첫번째 레인져)
		//4보다 큰 값 : 4 (마지막 레인져)
		if(listIndex < 0)
			return rangers.get(0);
		else if(listIndex >= rangers.size())
			return rangers.get(rangers.size()-1);
		else
			return rangers.get(listIndex);
		
	}

	@Override
	public List<Map<String, String>> getRangerDb() {
		return sqlSession.selectList("ranger.getRangersDb");
	}

	@Override
	public Map<String, String> getRanger(String id) {
		return sqlSession.selectOne("ranger.getRanger", id);
	}

	@Override
	public int insertRanger(Map<String, String> map) {
		return sqlSession.insert("ranger.insertRanger", map);
	}

	@Override
	public int deleteRanger(String id) {
		return sqlSession.delete("ranger.deleteRanger", id);
	}

	@Override
	public int deleteRangerDept(String id) {
		
		return sqlSession.delete("ranger.deleteRangerDept", id);
	}

}
