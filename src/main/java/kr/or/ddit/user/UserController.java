package kr.or.ddit.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.PageVo;

@RequestMapping("/user")
@Controller
public class UserController {

	@Resource(name = "userService")
	private IUserService userService;

	/**
	 * ����� ��ü ����Ʈ ��ȸ
	 * 
	 * @return
	 */
	@RequestMapping("/userAllList")
	public String userAllList(Model model) {

		List<UserVo> userList = userService.getAllUser();

		model.addAttribute("userList", userList);

		return "user/userAllList";
	}

	
	/**
	 * ����� ����¡ ����Ʈ ��ȸ
	 * @param pageVo
	 * @param model
	 * @return
	 */
	@RequestMapping("/userPagingList")
	public String userPagingList(PageVo pageVo, Model model) {

		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);

		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize",pageVo.getPageSize());
		model.addAttribute("page",pageVo.getPage());

		return "user/userPagingList";
	}
	
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public String user(@RequestParam("userId")String userId,Model model){
		
		UserVo userVo = userService.selectUser(userId);
		model.addAttribute("userVo",userVo);
		return "user/user";
	}
	
	@RequestMapping("/profileImg")
	public void profileImg(HttpServletResponse resp, 
							HttpServletRequest req,
							@RequestParam("userId") String userId) throws IOException {
		//���Ϸ� �ٿ�ε�
		resp.setHeader("Content-Disposition", "attachment; filename=profile.png"); 
		
		//���Ϸ� �ٿ�ε� and �̹��� ��µ���
		resp.setContentType("application/octet-stream");
		
		//OS�� �������� ���� ȭ���� �̻��ϰ� ��� �ɼ� �����Ƿ� 
		resp.setContentType("image");
		
		
		//1.����� ���̵� �Ķ���� Ȯ��
		//String userId = req.getParameter("userId");
		
		//2.�ش� ����� ���̵�� ����� ���� ��ȸ(realFileName)
		UserVo userVO = userService.selectUser(userId);
		
		//3-1.realFileName�� ������ ���
		//3-1-1.�ش� ����� ������ FileInputStream���� �д´�
		FileInputStream fis;
		if(userVO != null && userVO.getRealFileName() != null) {
			fis = new FileInputStream(new File(userVO.getRealFileName()));
		}
		
		//3-2.realFileName�� �������� ���� ���
		//3-2-1. /upload/noimg.png (application.getRealPath())
		else {
			//application
			//ServletContext application = getServletContext();
			
			// servlet ������� ����� �ƴ϶� getServletContext() = appliction�� ������ �����ü� ����
			
			ServletContext application = req.getServletContext();
			
			String noimgPath = application.getRealPath("/upload/noimg2.png");
			fis = new FileInputStream(new File(noimgPath));
		}
		
		//4.FileInputStream�� response ��ü�� outputStrea ��ü�� write
		ServletOutputStream sos = resp.getOutputStream();
		
		byte[] buff = new byte[512];
		int len = 0; 
		while((len = fis.read(buff)) > -1) {
			sos.write(buff);
		}
		sos.close();
		fis.close();
	}
}
