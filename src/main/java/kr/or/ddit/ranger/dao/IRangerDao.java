package kr.or.ddit.ranger.dao;

import java.util.List;
import java.util.Map;

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

	/**
	 * db를 통한 레인저스 전체 조회
	 * @return
	 */
	List<Map<String, String>> getRangerDb();
	
	/**
	 * 레인저 아이디로 레인저 정보 조회
	 * @param id
	 * @return
	 */
	Map<String, String> getRanger(String id);
	
	/**
	 * 신규 레인저 등록
	 * @param map
	 * @return
	 */
	int insertRanger(Map<String, String> map);
	
	/**
	 * 레인저 삭제
	 * @param id
	 * @return
	 */
	int deleteRanger(String id);
	
	/**
	 * 레인저 소속 삭제
	 * @param id
	 * @return
	 */
	int deleteRangerDept(String id);
}
