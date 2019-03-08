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
    * 작성자 : PC19
    * 변경이력 :
    * @return
    * Method 설명 : 전체 사용자 조회.
    */
   public List<UserVo> getAllUser(){
      List<UserVo> userList = sqlSessionTemplate.selectList("user.getAllUser");
      
      return userList;
   }

   /**
    * 
    * Method : selectUser
    * 작성자 : PC19
    * 변경이력 :
    * @return
    * Method 설명 : 특정 사용자 조회.
    */
   @Override
   public UserVo selectUser(String userId) {
      UserVo userVo = sqlSessionTemplate.selectOne("user.selectUser", userId);
      
      return userVo;
   }

   /**
    * 
    * Method : selectUserPagingList
    * 작성자 : PC19
    * 변경이력 :
    * @param pageVo
    * @return
    * Method 설명 : 사용자 페이징 리스트 조회.
    */
   @Override
   public List<UserVo> selectUserPagingList(PageVo pageVo) {
      List<UserVo> userList = sqlSessionTemplate.selectList("user.selectUserPagingList", pageVo);
      
      return userList;
   }

   /**
    * 
    * Method : getUserCnt
    * 작성자 : PC19
    * 변경이력 :
    * @return
    * Method 설명 : 전체 사용자 수를 조회.
    */
   @Override
   public int getUserCnt() {
      int userCnt = sqlSessionTemplate.selectOne("user.getUserCnt");
      
      return userCnt;
   }

   /**
    * 
    * Method : insertUser
    * 작성자 : PC19
    * 변경이력 :
    * @return
    * Method 설명 : 사용자 등록.
    */
   @Override
   public int insertUser(UserVo userVo) {
      int insertCnt = sqlSessionTemplate.insert("user.insertUser", userVo);
      
      return insertCnt;
   }

   /**
    * 
    * Method : deleteUser
    * 작성자 : PC19
    * 변경이력 :
    * @param userId
    * @return
    * Method 설명 : 사용자 삭제.
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
    * 작성자 : PC19
    * 변경이력 :
    * @param sqlSession
    * @param userVo
    * @return
    * Method 설명 : 사용자 정보 수정.
    */
   @Override
   public int updateUser(UserVo userVo) {
      int updateCnt = sqlSessionTemplate.update("user.updateUser", userVo);
            
      return updateCnt;
   }

   /**
    * 
    * Method : updateUserPass
    * 작성자 : PC19
    * 변경이력 :
    * @param sqlSession
    * @param userVo
    * @return
    * Method 설명 : 사용자 비밀번호 수정.
    */
   @Override
   public int updateUserPass(UserVo userVo) {
      int updateCnt = sqlSessionTemplate.update("user.updateUserPass", userVo);
      
      return updateCnt;
   }
}

















