package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserControllerTest extends WebTestConfig{


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
	
}
