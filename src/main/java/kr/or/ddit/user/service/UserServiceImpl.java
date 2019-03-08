package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource(name = "userDao")
	private IUserDao userDao;

	public UserServiceImpl() {
		// ���� : userDao = new UserDaoImpl();
	}

	/**
	 * 
	 * Method : getAllUser �ۼ��� : PC19 �����̷� :
	 * 
	 * @return Method ���� : ��ü ����� ���� ��ȸ.
	 */
	@Override
	public List<UserVo> getAllUser() {

		List<UserVo> userList = userDao.getAllUser();

		return userList;
	}

	/**
	 * 
	 * Method : selectUser �ۼ��� : PC19 �����̷� :
	 * 
	 * @return Method ���� : Ư�� ����� ��ȸ.
	 */
	@Override
	public UserVo selectUser(String userId) {

		UserVo userVo = userDao.selectUser(userId);

		return userVo;
	}

	/**
	 * 
	 * Method : selectUserPagingList �ۼ��� : PC19 �����̷� :
	 * 
	 * @param pageVo
	 * @return Method ���� : ����� ����¡ ����Ʈ ��ȸ.
	 */
	@Override
	public Map<String, Object> selectUserPagingList(PageVo pageVo) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("userList", userDao.selectUserPagingList(pageVo));
		resultMap.put("userCnt", userDao.getUserCnt());
		// lastPage�� �˱����� userCnt�� ����.

		return resultMap;
	}

	/**
	 * 
	 * Method : insertUser �ۼ��� : PC19 �����̷� :
	 * 
	 * @return Method ���� : ����� ���.
	 */
	@Override
	public int insertUser(UserVo userVo) {
		return userDao.insertUser(userVo);
	}

	/**
	 * 
	 * Method : deleteUser �ۼ��� : PC19 �����̷� :
	 * 
	 * @param userId
	 * @return Method ���� : ����� ����.
	 */
	@Override
	public int deleteUser(String userId) {

		int deleteCnt = userDao.deleteUser(userId);

		return deleteCnt;
	}

	/**
	 * 
	 * Method : updateUser �ۼ��� : PC19 �����̷� :
	 * 
	 * @param sqlSession
	 * @param userVo
	 * @return Method ���� : ����� ���� ����.
	 */
	@Override
	public int updateUser(UserVo userVo) {

		int updateCnt = userDao.updateUser(userVo);

		return updateCnt;
	}

	/**
	 * 
	 * Method : encryptPass �ۼ��� : PC19 �����̷� :
	 * 
	 * @return Method ���� : ��ü ����� ��й�ȣ ��ȣȭ.
	 */
	@Override
	public int encryptPass() { // �����ϱ�

		List<UserVo> userList = userDao.getAllUser();

		int updateCnt = 0;

		for (UserVo userVo : userList) {
			String encryptText = KISA_SHA256.encrypt(userVo.getPass());
			userVo.setPass(encryptText);

			updateCnt += userDao.updateUserPass(userVo);
		}

		return updateCnt;
	}

}
