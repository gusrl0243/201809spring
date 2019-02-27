package kr.or.ddit.ranger.service;

import java.util.List;

import kr.or.ddit.ranger.dao.IRangerDao;

public interface IRangerService {

	/**
	  *��ü �������� ��ȸ
	 * @return
	 */
	List<String> getRangers();
	
	IRangerDao getRangerDao();

	String getRanger(int index);
}
