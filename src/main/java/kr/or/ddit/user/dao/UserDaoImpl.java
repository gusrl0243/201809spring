package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Repository("userDao")
public class UserDaoImpl implements IUserDao{
   
   /**
    * 
    * Method : getAllUser
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : ��ü ����� ��ȸ.
    */
   public List<UserVo> getAllUser(SqlSession sqlSession){
      List<UserVo> userList = sqlSession.selectList("user.getAllUser");
      
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
   public UserVo selectUser(SqlSession sqlSession, String userId) {
      UserVo userVo = sqlSession.selectOne("user.selectUser", userId);
      
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
   public List<UserVo> selectUserPagingList(SqlSession sqlSession, PageVo pageVo) {
      List<UserVo> userList = sqlSession.selectList("user.selectUserPagingList", pageVo);
      
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
   public int getUserCnt(SqlSession sqlSession) {
      int userCnt = sqlSession.selectOne("user.getUserCnt");
      
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
   public int insertUser(SqlSession sqlSession, UserVo userVo) {
      int insertCnt = sqlSession.insert("user.insertUser", userVo);
      
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
   public int deleteUser(SqlSession sqlSession, String userId) {
      int deleteCnt = sqlSession.delete("user.deleteUser", userId);
      
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
   public int updateUser(SqlSession sqlSession, UserVo userVo) {
      int updateCnt = sqlSession.update("user.updateUser", userVo);
            
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
   public int updateUserPass(SqlSession sqlSession, UserVo userVo) {
      int updateCnt = sqlSession.update("user.updateUserPass", userVo);
      
      return updateCnt;
   }
}

















