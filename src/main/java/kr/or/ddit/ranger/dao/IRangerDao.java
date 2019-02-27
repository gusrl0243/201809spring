package kr.or.ddit.ranger.dao;

import java.util.List;

public interface IRangerDao {
	
	/**
	 * 전체 레인저스 조회
	 * @return
	 */
	List<String> getRangers();

	/**
	 * listIndex에 해당하는 레인저 이름을 반환
	 * @param listIndex
	 * @return
	 */
	String getRanger(int listIndex);
}
