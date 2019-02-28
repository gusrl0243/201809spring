package kr.or.ddit.ranger.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.ranger.model.RangerVo;
import kr.or.ddit.ranger.service.IRangerService;
import kr.or.ddit.user.model.UserVo;

@SessionAttributes({"boardGb","boardGb2"})
@RequestMapping("/ranger")
// @Controller���� ������ ����. -> STS 3.8.4�� ���� ����.
@Controller
public class RangerController {
	private Logger logger = LoggerFactory.getLogger(UserVo.class);
	
   @Resource(name="rangerService")
   private IRangerService rangerService;

   //@ModelAttribute �� ����� �޼ҵ��
   //@RequestMapping�� ����� �޼ҵ尡  ����Ǳ� ����
   //���� ����ǰ� �����ϴ� ��ü�� model��ü�� �Ӽ����� �־��ش�.
   
   @ModelAttribute("boardGb")
   public List<String> boardGb(){
	   logger.debug("boarGb");
	   
	   List<String> boardGbList = new ArrayList<String>();
	   boardGbList.add("��������");
	   boardGbList.add("Q&A");
	   boardGbList.add("������");
	   boardGbList.add("�����Խ���");
	   
	   return boardGbList;
	   
   }
   @ModelAttribute("boardGb2")
   public List<String> boardGb2(){
	   logger.debug("boarGb");
	   
	   List<String> boardGbList = new ArrayList<String>();
	   boardGbList.add("��������2");
	   boardGbList.add("Q&A2");
	   boardGbList.add("������2");
	   boardGbList.add("�����Խ���2");
	   
	   return boardGbList;
	   
   }
   
   /**
    * 
    * Method : getRangers
    * �ۼ��� : PC19
    * �����̷� :
    * @return
    * Method ���� : ��ü �������� ����Ʈ�� ��ȸ.
    */
   // localhost/ranger/getRangers ��� ��û�� ���� �޼ҵ忡�� ��û�� ó��
   @RequestMapping("/getRangers")
   public String getRangers(Model model) {
      List<String> rangers = rangerService.getRangers();
      
      // ���� : request.setAttribute("rangers", rangers);
      model.addAttribute("rangers", rangers);
      
      // servlet-context.xml -> prefix, suffix -> /WEB-INF/views/ + ranger/rangers + .jsp
      return "ranger/rangerList";
   }
   
   // localhost/ranger/getRanger?listIndex=2 ��û�� �Ʒ� �޼��忡�� ��û ó��.
//   @RequestMapping("/getRanger")
//   public String getRanger(HttpServletRequest req, Model model) {
//      int listIndex = Integer.valueOf(req.getParameter("listIndex"));
//      
//      String ranger = rangerService.getRanger(listIndex);
//      
//      model.addAttribute("ranger", ranger);
//      
//      return "ranger/ranger";
//   }
   
   /**
    * �������� ��ü ������ ModelAndView��ü�� ���� ����
    * @return
    */
   @RequestMapping(path="/getRangersMav")
   public ModelAndView getRangersMav(){
	   /*
	    List<String> rangers = rangerService.getRangers();
	    model.addAttribute("rangers", rangers);
	    return "ranger/rangerList";   */
	   
	   List<String> rangers = rangerService.getRangers();
	   ModelAndView mav = new ModelAndView();
	   
	   mav.addObject("rangers", rangers);
	   
	   //viewName
	   mav.setViewName("ranger/rangerList");
	   
	   return mav;
   }
   
   // localhost/ranger/getRanger?listIndex=2 ��û�� �Ʒ� �޼��忡�� ��û ó��.
   // vo��ü�� �Ķ���� ��� ������ �̸� �ʵ尡 �����ϸ� �Ķ���͸� �ش� �ʵ忡 ���ε� �����ش�.
   // ���� Ŀ�ǵ� ��ü(vo��ü)�� model�� �Ӽ����� �ڵ����� �߰� �ȴ�.
   @RequestMapping("/getRanger")
   public String getRanger(@ModelAttribute("rVo") RangerVo rangerVo, Model model) {
	   
      String ranger = rangerService.getRanger(rangerVo.getListIndex());
      
      model.addAttribute("ranger", ranger);
      //model.addAttribute("rangerVo", rangerVo);
      
      return "ranger/ranger";
   }
   
   //PathVariable : url�� �Ϻ� ���� ������ ġȯ�ؼ� 
   //�����ڰ� �ش� ���� ����ϱ� ���� ������ �ִ� @(������̼�)
   //localhost/ranger/pathVariable/brown
   //localhost/ranger/pathVariable/sally
   @RequestMapping(path="/pathVariable/{userId}")
   public String pathVariable(@PathVariable("userId")String userId){
	   
	   logger.debug("userId: {}", userId);
	   
	   return "ranger/ranger";
	   
   }
   
   //Ŭ���̾�Ʈ�� ��û�� ������ header������ ����
   //����� �� �ֵ��� ���� ���� �� �ִ�.
   //localhost/ranger/requestHeader
   @RequestMapping(path="/requestHeader")
   public String requestHeader(@RequestHeader("Accept")String acceptHeader){
	   logger.debug("acceptHeader :{}", acceptHeader);
	   
	   return "ranger/ranger";
   }
   
   
   @RequestMapping(path="/getRangerParam")
   public String getRangerParam(@RequestParam(name="listIndex", defaultValue="0")int listIndex, Model model){
	   
	   String ranger = rangerService.getRanger(listIndex);
	   model.addAttribute("ranger",ranger);
	   return "ranger/ranger";
   }
   
   
}













