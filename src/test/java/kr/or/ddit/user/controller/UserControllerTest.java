package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

public class UserControllerTest extends WebTestConfig{

	private static final String USER_INSERT_TEST_ID = "sallyTest2";

	@Resource(name="userService")
	private IUserService userService;
	
	@Before
	public void initData(){
		userService.deleteUser(USER_INSERT_TEST_ID);
	}
	
	
	/**
	 * 사용자 전체 조회 테스트
	 * @throws Exception 
	 */
	@Test
	public void testUserAllList() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/user/userAllList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		
		List<UserVo> userList = (List<UserVo>)mav.getModel().get("userList");
		
		assertEquals("user/userAllList", viewName);
		assertNotNull(userList);
		assertTrue(userList.size() == 1);
	}

	/**
	 * 페이징처리 조회
	 * @throws Exception
	 */
	public void testUserPagingList() throws Exception{
		
		MvcResult mvcResult = mockMvc.perform(get("/user/userPaginglsti")).andReturn();
			
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		ModelMap modelMap = mav.getModelMap();
		
		List<UserVo> userList = (List<UserVo>)modelMap.get("userList");
		int userCnt = (Integer)modelMap.get("userCnt");
		int page = (Integer)modelMap.get("page");
		int userSize = (Integer)modelMap.get("pageSize");
		
		assertEquals("user/userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals("user/userPagingList", viewName);
	
	}
	
	/**
	 * 사용자 상세 조회 테스트
	 */
	@Test
	public void testUser() throws Exception{
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", "brown")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo)mav.getModel().get("userVo");
		
		assertEquals("user/user", viewName);
		assertNotNull(userVo);
		assertEquals("brown", userVo.getUserId());
		assertEquals("브라운", userVo.getUserNm());
		
	}
	
	//@Test
	//public void testProfileImg(){}
	
	
	/**
	 * 사용자 등록 폼 요청 테스트
	 * @throws Exception
	 */
	@Test
	public void testUserForm() throws Exception{
		MvcResult mvcResult = mockMvc.perform(get("/user/userForm")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();


		assertEquals("user/userForm", viewName);
	}
	
	/**
	 * 사용자 등록 요청 테스트
	 */
	@Test
	public void testUserForm_post_success() throws Exception{
		
		File profileFile = new File("D:\\web\\레인저스사진\\moon.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "moon.png", "image/png", fis);
		
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
						.param("userId", USER_INSERT_TEST_ID)
						.param("userNm", "샐리테스트")
						.param("alias", "병아리")
						.param("addr1", "대전시 중고 대흥로 76")
						.param("addr2", "2층 DDIT")
						.param("zipcode", "34942")
						.param("pass", "testpass"))
						.andExpect(view().name("redirect:/user/userPagingList"))
						.andReturn();
		
		HttpSession session = mvcResult.getRequest().getSession();
		
		assertEquals("정상 등록 되었습니다.", session.getAttribute("msg"));
	}
	
	
	/**
	 * 사용자 등록요청(중복 사용자로 인한 등록 실패 케이스)테스트
	 * @throws Exception
	 */
	@Test
	public void testUserForm_post_fail_duplicateUser() throws Exception{
		
		File profileFile = new File("D:\\web\\레인저스사진\\moon.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "moon.png", "image/png", fis);
		
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
						.param("userId", "brown")
						.param("userNm", "샐리테스트")
						.param("alias", "병아리")
						.param("addr1", "대전시 중고 대흥로 76")
						.param("addr2", "2층 DDIT")
						.param("zipcode", "34942")
						.param("pass", "testpass"))
						.andExpect(view().name("user/userForm"))
						.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String msg = (String)mav.getModel().get("msg");
		
		assertEquals("중복체크에 실패 했습니다.", msg);
	}
	
	
	/**
	 * 사용자 등록(zipcode 사이즈 에러 sql에러) 테스트
	 * @throws Exception
	 */
	@Test
	public void testUserForm_post_fail_insertError() throws Exception{
		
		File profileFile = new File("D:\\web\\레인저스사진\\moon.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "moon.png", "image/png", fis);
		
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
						.param("userId", USER_INSERT_TEST_ID)
						.param("userNm", "샐리테스트")
						.param("alias", "병아리")
						.param("addr1", "대전시 중고 대흥로 76")
						.param("addr2", "2층 DDIT")
						.param("zipcode", "3494234942")
						.param("pass", "testpass"))
						.andExpect(view().name("user/userForm"))
						.andReturn();
		
	
	}
}
