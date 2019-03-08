package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.user.model.UserVo;

import kr.or.ddit.util.model.PageVo;

@Repository("userDao")
public class UserDaoImpl implements IUserDao{
	
	   @Resource(name="sqlSessionTemplate")
	   private SqlSessionTemplate sqlSessionTemplate;
   
   /**
    * 
    * Method : getAllUser
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : ��ü ����� ��ȸ.
    */
   public List<UserVo> getAllUser(){
      List<UserVo> userList = sqlSessionTemplate.selectList("user.getAllUser");
      
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
      UserVo userVo = sqlSessionTemplate.selectOne("user.selectUser", userId);
      
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
   public List<UserVo> selectUserPagingList(PageVo pageVo) {
      List<UserVo> userList = sqlSessionTemplate.selectList("user.selectUserPagingList", pageVo);
      
      return userList;
   }

   /**
    * 
    * Method : getUserCnt
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : ��ü ����� ���� ��ȸ.
    */
   @Override
   public int getUserCnt() {
      int userCnt = sqlSessionTemplate.selectOne("user.getUserCnt");
      
      return userCnt;
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
      int insertCnt = sqlSessionTemplate.insert("user.insertUser", userVo);
      
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
      int deleteCnt = sqlSessionTemplate.delete("user.deleteUser", userId);
      
      return deleteCnt;
   }
   //dfdfdfdfasdfasdfaweraweraweraweropiauwerpowaroiwearawerwaerawelkrjlkasdfndsfasdfasdfasdfapdfosdfksdlfsdfnmnsadfahsdbfiasbdfiasbdfiusdbfiubauiehfauiebfaurbvaubcuhabsdcuhasbdckuahsbdfkhasgdfhuasgdf

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
      int updateCnt = sqlSessionTemplate.update("user.updateUser", userVo);
            
      return updateCnt;
   }

   /**
    * 
    * Method : updateUserPass
    * �ۼ��� : PC19
    * �����̷� :
    * @param sqlSession
    * @param userVo
    * @return
    * Method ���� : ����� ��й�ȣ ����.
    */
   @Override
   public int updateUserPass(UserVo userVo) {
      int updateCnt = sqlSessionTemplate.update("user.updateUserPass", userVo);
      
      return updateCnt;
   }
}

















