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

   @Resource(name="userDao")
   private IUserDao userDao;
   
   public UserServiceImpl(){
      // ���� : userDao = new UserDaoImpl();
   }
   
   /**
    * 
    * Method : getAllUser
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : ��ü ����� ���� ��ȸ.
    */
   @Override
   public List<UserVo> getAllUser() {
      SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
      SqlSession sqlSession = sqlSessionFactory.openSession();
      
      List<UserVo> userList = userDao.getAllUser(sqlSession);
      sqlSession.close();
      
      return userList;
   }

   /**
    * 
    * Method : selectUser
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : Ư�� ����� ��ȸ.
    */
   @Override
   public UserVo selectUser(String userId) {
      SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
      SqlSession sqlSession = sqlSessionFactory.openSession();
      
      UserVo userVo = userDao.selectUser(sqlSession, userId);
      sqlSession.close();
      
      return userVo;
   }

   /**
    * 
    * Method : selectUserPagingList
    * �ۼ��� : PC19
    * �����̷� :
    * @param pageVo
    * @return
    * Method ���� : ����� ����¡ ����Ʈ ��ȸ.
    */
   @Override
   public Map<String, Object> selectUserPagingList(PageVo pageVo) {
      SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
      SqlSession sqlSession = sqlSessionFactory.openSession();
      
      Map<String, Object> resultMap = new HashMap<String, Object>();
      
      resultMap.put("userList", userDao.selectUserPagingList(sqlSession, pageVo));
      resultMap.put("userCnt", userDao.getUserCnt(sqlSession));
      // lastPage�� �˱����� userCnt�� ����.
      
      sqlSession.close();
      
      return resultMap;
   }

   /**
    * 
    * Method : insertUser
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : ����� ���.
    */
   @Override
   public int insertUser(UserVo userVo) {
      SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
      SqlSession sqlSession = sqlSessionFactory.openSession();
      
      int insertCnt = userDao.insertUser(sqlSession, userVo);
      
      sqlSession.commit();
      sqlSession.close();
      
      return insertCnt;
   }

   /**
    * 
    * Method : deleteUser
    * �ۼ��� : PC19
    * �����̷� :
    * @param userId
    * @return
    * Method ���� : ����� ����.
    */
   @Override
   public int deleteUser(String userId) {
      SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
      SqlSession sqlSession = sqlSessionFactory.openSession();
      
      int deleteCnt = userDao.deleteUser(sqlSession, userId);
      
      sqlSession.commit();
      sqlSession.close();
      
      return deleteCnt;
   }

   /**
    * 
    * Method : updateUser
    * �ۼ��� : PC19
    * �����̷� :
    * @param sqlSession
    * @param userVo
    * @return
    * Method ���� : ����� ���� ����.
    */
   @Override
   public int updateUser(UserVo userVo) {
      SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
      SqlSession sqlSession = sqlSessionFactory.openSession();   
      
      int updateCnt = userDao.updateUser(sqlSession, userVo);
      
      sqlSession.commit();
      sqlSession.close();
      
      return updateCnt;
   }

   /**
    * 
    * Method : encryptPass
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : ��ü ����� ��й�ȣ ��ȣȭ.
    */
   @Override
   public int encryptPass() { // �����ϱ�
      SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
      SqlSession sqlSession = sqlSessionFactory.openSession();   
      
      List<UserVo> userList = userDao.getAllUser(sqlSession);
      
      int updateCnt = 0; 
      
      for(UserVo userVo : userList){
         String encryptText = KISA_SHA256.encrypt(userVo.getPass());
         userVo.setPass(encryptText);
         
         updateCnt += userDao.updateUserPass(sqlSession, userVo);      
      }
      
      sqlSession.commit();
      sqlSession.close();
      
      return updateCnt;
   }
   
   

}
















