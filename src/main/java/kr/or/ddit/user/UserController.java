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
	 * 사용자 전체 리스트 조회
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
	 * 사용자 페이징 리스트 조회
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
		//파일로 다운로드
		resp.setHeader("Content-Disposition", "attachment; filename=profile.png"); 
		
		//파일로 다운로드 and 이미지 출력도됨
		resp.setContentType("application/octet-stream");
		
		//OS나 브라우저에 따라 화면이 이상하게 출력 될수 있으므로 
		resp.setContentType("image");
		
		
		//1.사용자 아이디 파라미터 확인
		//String userId = req.getParameter("userId");
		
		//2.해당 사용자 아이디로 사용자 정보 조회(realFileName)
		UserVo userVO = userService.selectUser(userId);
		
		//3-1.realFileName이 존재할 경우
		//3-1-1.해당 경로의 파일을 FileInputStream으로 읽는다
		FileInputStream fis;
		if(userVO != null && userVO.getRealFileName() != null) {
			fis = new FileInputStream(new File(userVO.getRealFileName()));
		}
		
		//3-2.realFileName이 존재하지 않을 경우
		//3-2-1. /upload/noimg.png (application.getRealPath())
		else {
			//application
			//ServletContext application = getServletContext();
			
			// servlet 기반으로 만든게 아니라 getServletContext() = appliction의 정보를 가져올수 없음
			
			ServletContext application = req.getServletContext();
			
			String noimgPath = application.getRealPath("/upload/noimg2.png");
			fis = new FileInputStream(new File(noimgPath));
		}
		
		//4.FileInputStream을 response 객체의 outputStrea 객체에 write
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
