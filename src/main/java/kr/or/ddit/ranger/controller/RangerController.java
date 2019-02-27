package kr.or.ddit.ranger.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ranger.model.RangerVo;
import kr.or.ddit.ranger.service.IRangerService;


@RequestMapping("/ranger")
@Controller
public class RangerController {
	
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	/**
	 * ��ü �������� ����Ʈ�� ��ȸ
	 * @return
	 */
	//localhost/ranger/getRangers ��� ��û��
	//���� �޼ҵ忡�� ��û�� ó��
	@RequestMapping("/getRangers")
	public String getRangers(Model model) {
		
		List<String> rangers = rangerService.getRangers();
		
		//request.setAttribute("rangers",rangers);
		model.addAttribute("rangers", rangers);
		
		return "ranger/rangerList";
	}
	
	//localhost/ranger/getRanger?listIndex=2��û��
	//�Ʒ� �޼ҵ忡�� ��û ó��
	//vo��ü�� �Ķ���� ��� ������ �̸� �ʵ尡 �����ϸ�
	//�Ķ���͸� �ش� �ʵ忡 ���ε� �����ش�
	@RequestMapping("/getRanger")
	public String getRanger(RangerVo rangerVo, Model model) {
		
		String ranger = rangerService.getRanger(rangerVo.getListIndex());
		
		model.addAttribute("ranger", ranger);
		
		return "ranger/ranger";
	}
	
//	@RequestMapping("/getRanger")
//	public String getRanger(HttpServletRequest req, Model model) {
//		//
//		int listIndex = Integer.parseInt(req.getParameter("listIndex"));
//		String ranger = rangerService.getRanger(listIndex);
//		
//		model.addAttribute("ranger", ranger);
//		
//		return "ranger/ranger";
//	}
	
	@RequestMapping
	public void test() {
		
	}
}
