package kr.or.ddit.mvc;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.ranger.model.RangerVo;
import kr.or.ddit.ranger.service.IRangerService;
import kr.or.ddit.ranger.service.RangerServiceImpl;
import kr.or.ddit.user.model.UserVo;

@RequestMapping("/ajax")
@Controller
public class AjaxController {
	private Logger logger = LoggerFactory.getLogger(UserVo.class);
	
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	@ModelAttribute("rangerList")
	public List<String> getRangers(){
		return rangerService.getRangers();
	}

	//beanNameViewResolver
	//1.viewName:jsonView
	//2.ModelAndView("jsonView")
	
	@RequestMapping("/ajaxView")
	public String ajaxView(){
		return "mvc/ajaxView";
	}
	
	@RequestMapping("/jsonView")
	public String jsonView(){
		return "jsonView";
	}
	
	@RequestMapping("/modelAndView")
	public ModelAndView modelAndView(){
		return new ModelAndView("jsonView");
	}
	
	@RequestMapping(path="/responseBody", produces={"application/json"})
	@ResponseBody
	public List<String> responseBody(Model model){
		return rangerService.getRangers();
	}
	
	@RequestMapping(path="/requestBody")
	@ResponseBody
	public RangerVo requestBody(@RequestBody RangerVo rangerVo){
		logger.debug("rangerVo : {}", rangerVo);
		
		rangerVo.setUserId(rangerVo.getUserId()+"_server");
		rangerVo.setBirth(new Date());
		
//		return rangerService.getRangers();
		return rangerVo;
	}
}
