package kr.or.ddit.prod.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.prod.service.IProdService;

@RequestMapping("/prod")
@Controller
public class ProdListController {


	@Resource(name="prodService")
	private IProdService prodService;
	
	/**
	 * 제품  리스트 조회
	 */
	@RequestMapping("/prodList")
	public String prodList(@RequestParam("lgu")String lgu,Model model){
		
		List<ProdVo> prodList = prodService.getProdByLgu(lgu);
		
		model.addAttribute("prodList", prodList);
		
		return "prod/prodList";
	}
	
//		String lgu = request.getParameter("lgu");
//		
//		List<ProdVo> prodList = prodService.getProdByLgu(lgu);
//		
//		request.setAttribute("prodList", prodList);
//		
//		request.getRequestDispatcher("/prod/prodList.jsp").forward(request, response);
}
