package kr.or.ddit.ranger.dao;

import java.util.ArrayList;
import java.util.List;

public class RangerDaoImpl implements IRangerDao{
	
	public RangerDaoImpl() {
		
	}

	/**
	 * 전체 레인저스 조회(임의의 값)
	 */
	@Override
	public List<String> getRangers() {
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		rangers.add("moon");
		rangers.add("james");
		return rangers;
	}

}
