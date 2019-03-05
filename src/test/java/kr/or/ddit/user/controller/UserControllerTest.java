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
	 * ����� ��ü ��ȸ �׽�Ʈ
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
	 * ����¡ó�� ��ȸ
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
	 * ����� �� ��ȸ �׽�Ʈ
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
		assertEquals("����", userVo.getUserNm());
		
	}
	
	//@Test
	//public void testProfileImg(){}
	
	
	/**
	 * ����� ��� �� ��û �׽�Ʈ
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
	 * ����� ��� ��û �׽�Ʈ
	 */
	@Test
	public void testUserForm_post_success() throws Exception{
		
		File profileFile = new File("D:\\web\\������������\\moon.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "moon.png", "image/png", fis);
		
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
						.param("userId", USER_INSERT_TEST_ID)
						.param("userNm", "�����׽�Ʈ")
						.param("alias", "���Ƹ�")
						.param("addr1", "������ �߰� ����� 76")
						.param("addr2", "2�� DDIT")
						.param("zipcode", "34942")
						.param("pass", "testpass"))
						.andExpect(view().name("redirect:/user/userPagingList"))
						.andReturn();
		
		HttpSession session = mvcResult.getRequest().getSession();
		
		assertEquals("���� ��� �Ǿ����ϴ�.", session.getAttribute("msg"));
	}
	
	
	/**
	 * ����� ��Ͽ�û(�ߺ� ����ڷ� ���� ��� ���� ���̽�)�׽�Ʈ
	 * @throws Exception
	 */
	@Test
	public void testUserForm_post_fail_duplicateUser() throws Exception{
		
		File profileFile = new File("D:\\web\\������������\\moon.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "moon.png", "image/png", fis);
		
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
						.param("userId", "brown")
						.param("userNm", "�����׽�Ʈ")
						.param("alias", "���Ƹ�")
						.param("addr1", "������ �߰� ����� 76")
						.param("addr2", "2�� DDIT")
						.param("zipcode", "34942")
						.param("pass", "testpass"))
						.andExpect(view().name("user/userForm"))
						.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String msg = (String)mav.getModel().get("msg");
		
		assertEquals("�ߺ�üũ�� ���� �߽��ϴ�.", msg);
	}
	
	
	/**
	 * ����� ���(zipcode ������ ���� sql����) �׽�Ʈ
	 * @throws Exception
	 */
	@Test
	public void testUserForm_post_fail_insertError() throws Exception{
		
		File profileFile = new File("D:\\web\\������������\\moon.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "moon.png", "image/png", fis);
		
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
						.param("userId", USER_INSERT_TEST_ID)
						.param("userNm", "�����׽�Ʈ")
						.param("alias", "���Ƹ�")
						.param("addr1", "������ �߰� ����� 76")
						.param("addr2", "2�� DDIT")
						.param("zipcode", "3494234942")
						.param("pass", "testpass"))
						.andExpect(view().name("user/userForm"))
						.andReturn();
		
	
	}
}
