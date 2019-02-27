package kr.or.ddit.ranger.service;

import java.util.List;

import kr.or.ddit.ranger.dao.IRangerDao;

public interface IRangerService {

	/**
	  *전체 레인저스 조회
	 * @return
	 */
	List<String> getRangers();
	
	IRangerDao getRangerDao();

	String getRanger(int index);
}
