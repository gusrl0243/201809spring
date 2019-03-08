package kr.or.ddit.ranger.dao;

import java.util.List;
import java.util.Map;

public interface IRangerDao {
	
	/**
	 * ��ü �������� ��ȸ
	 * @return
	 */
	List<String> getRangers();

	/**
	 * listIndex�� �ش��ϴ� ������ �̸��� ��ȯ
	 * @param listIndex
	 * @return
	 */
	String getRanger(int listIndex);

	/**
	 * db�� ���� �������� ��ü ��ȸ
	 * @return
	 */
	List<Map<String, String>> getRangerDb();
	
	/**
	 * ������ ���̵�� ������ ���� ��ȸ
	 * @param id
	 * @return
	 */
	Map<String, String> getRanger(String id);
	
	/**
	 * �ű� ������ ���
	 * @param map
	 * @return
	 */
	int insertRanger(Map<String, String> map);
	
	/**
	 * ������ ����
	 * @param id
	 * @return
	 */
	int deleteRanger(String id);
	
	/**
	 * ������ �Ҽ� ����
	 * @param id
	 * @return
	 */
	int deleteRangerDept(String id);
}
