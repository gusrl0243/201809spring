package kr.or.ddit.ranger.dao;

import java.util.List;

public interface IRangerDao {
	
	/**
	 * 전체 레인저스 조회
	 * @return
	 */
	List<String> getRangers();
}
