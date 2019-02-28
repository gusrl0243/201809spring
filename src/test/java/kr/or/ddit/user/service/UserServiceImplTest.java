package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

import org.junit.Before;
import org.junit.Test;

public class UserServiceImplTest extends LogicTestConfig{
   
   @Resource(name="userService")
   private IUserService userService;
   
   @Before
   public void setup(){
      //userService = new UserServiceImpl();
      userService.deleteUser("test11");
   }

   // getAllUser 메서드를 테스트하는 메서드 작성.
   @Test
   public void testGetAllUser() {
      /***Given***/
      
      /***When***/
      List<UserVo> userList = userService.getAllUser();
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
      UserVo user = userService.selectUser("brown");
      System.out.println("user : " + user);
      
      /***Then***/
      assertNotNull(user);
      
   }
   
   // null인지 테스트하기
   @Test
   public void testSelectNotExistsUser(){
      /***Given***/
      
      /***When***/
      UserVo userVo = userService.selectUser("notExistsBrown");
      
      /***Then***/
      assertNull(userVo);
      
   }
   
   @Test
   public void testSelectUserPagingList(){
      /***Given***/
      PageVo pageVo = new PageVo(1, 10);
      
      /***When***/
      Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
            
      List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
      int userCnt = (Integer) resultMap.get("userCnt");
      
      for(UserVo user : userList){
         System.out.println("page : " + user);
      }

      System.out.println("userCnt : " + userCnt);
      
      /***Then***/
      // userList
      assertNotNull(userList);
      assertEquals(10, userList.size());
      
      // userCnt
      assertEquals(112, userCnt);
      
   }
   
   @Test
   public void testInsertUser(){
      /***Given***/
      UserVo user = new UserVo("test11", "테스트", "별명", "대전 중구", "영민빌딩 2층", "22332", 
            "testpass", new Date());
      
      /***When***/
      int insertCnt = userService.insertUser(user);

      /***Then***/
      assertEquals(insertCnt, 1);
      
   }
   
   @Test
   public void testUpdateUser(){
      /***Given***/
      // '1111' 유저 정보 수정
      UserVo userVo = new UserVo("1111", "2222", "2222", "2222", "2222", "2222", "2222");
      
      /***When***/
      int updateCnt = userService.updateUser(userVo);

      /***Then***/
      assertEquals(updateCnt, 1);

   }
   
   @Test
   public void testUpdateUserPass(){
      /***Given***/
      
      /***When***/
      int updateCnt = userService.encryptPass();
      
      /***Then***/
      assertEquals(112, updateCnt);

   }

}

















