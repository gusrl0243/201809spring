package kr.or.ddit.lprod;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.util.model.PageVo;

@RequestMapping("/lprod")
@Controller
public class LprodController {

	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	/**
	 * 제품 그룹 리스트 조회
	 */
	@RequestMapping("/lprodList")
	public String lprodList(Model model){
		
		List<LprodVo> lprodList = lprodService.getAllLprod();
		
		model.addAttribute("lprodList", lprodList);
		
		return "lprod/lprodList";
	}
	
	@RequestMapping("/lprodPagingList")
	public String lprodPagingList(PageVo pageVo, Model model){
		
		Map<String, Object> resultMap = lprodService.selectLprodPagingList(pageVo);
		
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize",pageVo.getPageSize());
		model.addAttribute("page",pageVo.getPage());
		return "lprod/lprodPagingList";
	}
}
