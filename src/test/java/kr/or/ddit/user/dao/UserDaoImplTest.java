package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoImplTest extends LogicTestConfig{

   @Resource(name="userDao")
   private IUserDao userDao;

   
   // @Before - @Test - @After
   
   @Before
   public void setup(){
      //userDao = new UserDaoImpl();
      
      userDao.deleteUser( "test11");
   }
   
   @After
   public void tearDown(){

   }
   
   
   // getAllUser 메서드를 테스트하는 메서드 작성.
   @Test
   public void testGetAllUser() {
      /***Given***/
      
      /***When***/
      List<UserVo> userList = userDao.getAllUser();
//      for(UserVo userVo : userList){
//         System.out.println(userVo);
//      }
      
      /***Then***/
      assertNotNull(userList);
      assertEquals(112, userList.size());

   }
   
   // selectUser 메서드를 테스트하는 메서드 작성.
   @Test
   public void testSelectUser() {
      /***Given***/
      
      /***When***/
      UserVo user = userDao.selectUser("brown");
      System.out.println("user : " + user);
      
      /***Then***/
      assertNotNull(user);
      
   }
   
   /**
    * 
    * Method : testSelectUserPagingList
    * 작성자 : PC19
    * 변경이력 :
    * Method 설명 : 사용자 페이징 리스트 조회 테스트
    */
   @Test
   public void testSelectUserPagingList(){
      /***Given***/
      PageVo pageVo = new PageVo(1, 10);
      
      /***When***/
      List<UserVo> userList = userDao.selectUserPagingList(pageVo);
//      for(UserVo user : userList){
//         System.out.println("page : " + user);
//      }
      
      /***Then***/
      assertNotNull(userList);
      assertEquals(10, userList.size());
      
   }
   
   /**
    * 
    * Method : testGetUserCnt
    * 작성자 : PC19
    * 변경이력 :
    * Method 설명 : 사용자 수 조회 테스트
    */
   @Test
   public void testGetUserCnt(){
      /***Given***/
      
      /***When***/
      int userCnt = userDao.getUserCnt();
      System.out.println("userCnt : " + userCnt);

      /***Then***/
      assertEquals(112, userCnt);
      
   }
   
   /**
    * 
    * Method : testPagination
    * 작성자 : PC19
    * 변경이력 :
    * Method 설명 : 페이지 수 구하기.
    */
   @Test
   public void testPagination(){
      /***Given***/
      int userCnt = 105;
      int pageSize = 10;
      
      /***When***/
      // ceil
      int lastPage = userCnt / pageSize + (userCnt%pageSize > 0 ? 1 : 0);

      /***Then***/
      assertEquals(11, lastPage);
      
   }
   
   @Test
   public void testPagination2(){
      /***Given***/
      int userCnt = 110;
      int pageSize = 10;
      
      /***When***/
      // ceil
      int lastPage = userCnt / pageSize + (userCnt%pageSize > 0 ? 1 : 0);
      
      /***Then***/
      assertEquals(11, lastPage);
      
   }
   
   @Test
   public void testInsertUser(){
      /***Given***/
      
      UserVo userVo = new UserVo("test11", "테스트", "별명", "대전 중구 대흥로 76", "2층 ddit", "34942",
            "testpass", new Date());
      
      
      
      /***When***/
      int insertCnt = userDao.insertUser(userVo); 
      System.out.println(insertCnt);
      
      /***Then***/
      assertEquals(1, insertCnt);

   }
   
   @Test
   public void testUpdateUser(){
      /***Given***/
      // '1111' 유저 정보 수정
      UserVo userVo = new UserVo("1111", "2222", "2222", "2222", "2222", "2222", "2222");
      
      /***When***/
      int updateCnt = userDao.updateUser(userVo);

      /***Then***/
      assertEquals(updateCnt, 1);

   }
   
}













