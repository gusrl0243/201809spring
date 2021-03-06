package kr.or.ddit.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
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

		//return "user/userAllList";
		return "userAllListTiles";
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

		return "userPagingListTiles";
	}
	
	/**
	 * 사용자 페이징 리스트 view
	 * @return
	 */
	@RequestMapping("/userPagingListAjaxView")
	public String userPagingListAjaxView() {
		return "userPagingListAjaxTiles";
	}
	
	/**
	 * 사용자 페이지 리스트 ajax 요청처리
	 * @param pageVo
	 * @param model
	 * @return
	 */
	@RequestMapping("/userPagingListAjax")
	public String userPagingListAjax(PageVo pageVo, Model model) {

		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);

		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize",pageVo.getPageSize());
		model.addAttribute("page",pageVo.getPage());
		//userList, userCnt, pageSize, page
		//{
		//  userList : [{userId : 'brown', userNm : '브라운'}......{userId : 'sally', userNm : '샐리'}
		//  userCnt : "110",
		//  pageSize : "10";
		//  page : "2"
		//}

		return "jsonView";
	}
	
	@RequestMapping("/userPagingListAjaxHtml")
	public String userPagingListAjaxHtml(PageVo pageVo, Model model) {

		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);

		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize",pageVo.getPageSize());
		model.addAttribute("page",pageVo.getPage());

		return "user/userPagingListAjaxHtml";
	}
	
	/**
	 * 사용자 상세정보
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public String user(@RequestParam("userId")String userId,Model model){
		
		UserVo userVo = userService.selectUser(userId);
		model.addAttribute("userVo",userVo);
		return "userTiles";
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
	
	/**
	 * 사용자 등록폼 요청
	 * @return
	 */
	@RequestMapping(path="/userForm", method=RequestMethod.GET)
	public String userForm(){
		
		
		return "user/userForm";
	}
	
	@RequestMapping(path="/userForm", method=RequestMethod.POST)
	public String userForm(UserVo userVo, 
						   @RequestPart("profile")MultipartFile profile,
						   HttpSession session,
						   Model model) throws IllegalStateException, IOException{
		
		UserVo duplicateUserVo = userService.selectUser(userVo.getUserId());
		
		//중복체크 통과(신규등록)
		if(duplicateUserVo == null){
			//사용자 프로파일을 업로드 한경우
			
			String filename = "";
			String realFilename = "";
			if(profile.getSize() > 0){
				filename = profile.getOriginalFilename();
				realFilename = "d:\\picture\\" + UUID.randomUUID().toString();
				profile.transferTo(new File(realFilename));
			}
			userVo.setFileName(filename);
			userVo.setRealFileName(realFilename);
			
			//사용자 비밀번호 암호화로직
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			
			int insertCnt = 0;
			try{
				insertCnt = userService.insertUser(userVo);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			//정상입력(성공)
			if(insertCnt == 1){
				session.setAttribute("msg", "정상 등록 되었습니다.");
				return "redirect:/user/userPagingList"; //contextPath 작업 필요
			}
			else{
				return "user/userForm";
			}
		}
		//중복체크 실패
		else{
			model.addAttribute("msg", "중복체크에 실패 했습니다.");
			
			
			return "user/userForm";
		}

	}
	
	@RequestMapping(path="/userModifyForm", method=RequestMethod.GET)
	public String userModifyForm(@RequestParam("userId") String userId, Model model) {
		
		 UserVo userVo = userService.selectUser(userId);
	      
	      model.addAttribute("userVo", userVo);
		
		return "user/userModifyForm";
	}
	
    @RequestMapping(path="/userModifyForm", method=RequestMethod.POST)
    public String userModifyForm(UserVo userVo, Model model,RedirectAttributes ra,HttpServletRequest req,@RequestPart("profile") MultipartFile profilePart,
          HttpSession session) throws IllegalStateException, IOException{
       
       userVo = new UserVo(userVo.getUserId(), userVo.getUserNm(), userVo.getAlias(), userVo.getAddr1(), 
             userVo.getAddr2(), userVo.getZipcode(), userVo.getPass());
       
       String fileName = "";
       String realFileName = "";
       
       if(profilePart.getSize() > 0){
          fileName = profilePart.getOriginalFilename();
          realFileName = "d:\\picture" + UUID.randomUUID().toString();
          
          profilePart.transferTo(new File(realFileName));
          
       }
       
       userVo.setFileName(fileName);
       userVo.setRealFileName(realFileName);
       
       //비밀번호 수정 요청 여부
       //사용자가 값을 입력하지 않은 경우 == 기존 비밀번호 유지
       if(userVo.getPass().equals("")){
    	   UserVo userVoForPass = userService.selectUser(userVo.getUserId());
    	   userVo.setPass(userVoForPass.getPass());
    	   
       }else{//사용자가 비밀번호를 신규 등록한 경우
    	   userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
       }
       
       
       
       int updateCnt = userService.updateUser(userVo);
       
       //사용자 정보 수정 정상 : 사용자 상세 조회 페이지 이동
       if(updateCnt == 1){
    	   //redirect 파라미터를 보내는 방법
    	   //1.url로 작성
    	   //  "redirect:/user/user?userId=" + userVo.getUserId();
    	   //2.model객체의 속성 : 저장된 속성을 자동을 파라미터 변환
    	   //  model.addAttribute("userId", userVo.getUserId());
    	   //  return "redirect:/user/user";
    	   //3. RedirectAtrributes(ra) 객체를 이용
    	   //  ra.addAttribute("userId", userVo.getUserId());
    	   //  return "redirect:/user/user";
//1.          return "redirect:/user/user?userId=" + userVo.getUserId();
//2.   	   model.addAttribute("userId", userVo.getUserId());
    	   ra.addAttribute("userId", userVo.getUserId());
    	   ra.addFlashAttribute("msg", "정상 등록 되었습니다");
    	   //model.addAttribute("msg_model", "정상 등록 되었습니다._model");
    	   
    	   return "redirect:/user/user" + req.getContextPath() + "/user/user";
          //사용자 정보 수정 실패 : 사용자 등록 페이지 이동
       }else{
          return "user/userModify";
       }
       
//		//사용자 정보 확인
//		
//		String userNm = req.getParameter("userNm");
//		String alias = req.getParameter("alias");
//		String addr1 = req.getParameter("addr1");
//		String addr2 = req.getParameter("addr2");
//		String zipcode = req.getParameter("zipcode");
//		String pass = req.getParameter("pass");
//				
//		UserVo userVo = new UserVo(userId, userNm, alias, addr1, addr2, zipcode, pass);
//		
//		//사용자 사진
//		Part profilePart = req.getPart("profile");
//		
//		//사용자가 사진을 올린 경우
//		String filename = "";
//		String realFilename = "";
//		
//		if(profilePart.getSize() > 0){
//			//사용자 테이블 파일명을 저장
//			//(실제 업로드한 파일명-filename,
//			// 파일 충돌을 방지하기 위해 사용한 uuid = realFilename)
//			String contentDisposition 
//					= profilePart.getHeader("Content-Disposition");
//			
//			filename = PartUtil.getFileNameFromPart(contentDisposition);
//			realFilename = "d:\\picture\\" + UUID.randomUUID().toString();
//			
//			//디스크에 기록 ( d:\picture\ + realFileName)
//			profilePart.write(realFilename);
//		}
//		//사용자가 사진을 올리지 않은 경우
//		
//		userVo.setFilename(filename);
//		userVo.setRealFilename(realFilename);
//		
//		int updateCnt = userService.updateUser(userVo);
//		
//		//사용자 정보 수정 정상 : 사용자 상세 조회 페이지 이동
//		if(updateCnt == 1)
//			resp.sendRedirect(req.getContextPath() + "/user?userId=" + userId);
//		//사용자 정보 수정 실패 : 사용자 등록 페이지 이동
//		else
//			doGet(req, resp);
//		
//	}
       
    }
}
