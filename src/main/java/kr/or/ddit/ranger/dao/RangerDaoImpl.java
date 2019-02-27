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
	 * ��ü �������� ��ȸ(������ ��)
	 */
	@Override
	public List<String> getRangers() {
		
		return rangers;
	}

	@Override
	public String getRanger(int listIndex) {
		//0-4
		//0���� ���� �� : 0 (ù��° ������)
		//4���� ū �� : 4 (������ ������)
		if(listIndex < 0)
			return rangers.get(0);
		else if(listIndex >= rangers.size())
			return rangers.get(rangers.size()-1);
		else
			return rangers.get(listIndex);
		
	}

}
