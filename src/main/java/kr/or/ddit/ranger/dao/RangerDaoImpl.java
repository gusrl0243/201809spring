package kr.or.ddit.ranger.dao;

import java.util.ArrayList;
import java.util.List;

public class RangerDaoImpl implements IRangerDao{
	
	public RangerDaoImpl() {
		
	}

	/**
	 * ��ü �������� ��ȸ(������ ��)
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
