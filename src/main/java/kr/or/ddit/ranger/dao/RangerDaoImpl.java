package kr.or.ddit.ranger.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("rangerDao")
public class RangerDaoImpl implements IRangerDao{
	
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

}
