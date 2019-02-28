package kr.or.ddit.ranger.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;

/*
 * 1. ������ �����̳� ���� �ʿ�
 *  �׽�Ʈ ����� RangerController
 *  RangerController�� servlet-context.xml component scan ������ �Ǿ�����.
 *  RangerController�� RangerService ��ü�� ���Թ���.
 *  RangerService�� RangerDao ��ü�� ���Թ���.
 *  
 *  ** RangerController�� ����� ���ؼ��� RangerService, RangerDao ������ ���� �ʿ�.
 *  �׷��� ������ RangerController�� ��ĵ�ϴ� servlet-context.xml �Ӹ� �ƴ϶� RangerService,
 *  RangerDao�� ��ĵ�ϴ� application-context.xml�� �ʿ��ϴ�. 
 */

public class RangerControllerTest extends WebTestConfig{
   
   /**
    * 
    * Method : testGetRangers
    * �ۼ��� : PC19
    * �����̷� :
    * @throws Exception
    * Method ���� : ��ü �������� ��ȸ �׽�Ʈ.
    */
   @Test
   public void testGetRangers() throws Exception {
      /***Given***/
      
      /***When***/
      MvcResult mvcResult = mockMvc.perform(get("/ranger/getRangers")).andReturn(); // or post("")
      
      ModelAndView mav = mvcResult.getModelAndView();
      String viewName = mav.getViewName();
      
      Map<String, Object> model = mav.getModel();
      List<String> rangers = (List<String>) model.get("rangers");
      List<String> boardGbList = (List<String>) model.get("boardGbList");

      /***Then***/
      assertEquals("ranger/rangerList", viewName);
      
      assertNotNull(rangers);
      assertEquals(5, rangers.size());
      
   }
   
   /**
    * 
    * Method : testGetRanger
    * �ۼ��� : PC19
    * �����̷� :
    * @throws Exception
    * Method ���� : listIndex�� �ش��ϴ� ������ �̸� ��ȸ.
    */
   @Test
   public void testGetRanger() throws Exception{
      /***Given***/
      
      /***When***/
      MvcResult mvcResult = mockMvc.perform(get("/ranger/getRanger").param("listIndex", "2"))
            .andReturn();
      ModelAndView mav = mvcResult.getModelAndView();
      String viewName = mav.getViewName();
      
      ModelMap modelMap = mav.getModelMap();
      String ranger = (String) modelMap.get("ranger");
     List<String> boardGb = (List<String>)modelMap.get("boardGb");
      
      /***Then***/
      assertEquals("ranger/ranger", viewName);
      assertEquals("sally", ranger);
      assertNotNull(boardGb);
      assertEquals(4, boardGb.size());
   }

   @Test
   public void testGetRangersMav() throws Exception{
	   MvcResult mvcResult=
			   mockMvc.perform(get("/ranger/getRangersMav")).andReturn();
	   
	   ModelAndView mav = mvcResult.getModelAndView();
	   
	   assertEquals("ranger/rangerList", mav.getViewName());
	   assertEquals(5, ((List<String>)mav.getModel().get("rangers")).size());
	   
	   
   }
   
}













