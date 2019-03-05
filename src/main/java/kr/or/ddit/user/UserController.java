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
	
	/**
	 * ����� ����� ��û
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
		
		//�ߺ�üũ ���(�űԵ��)
		if(duplicateUserVo == null){
			//����� ���������� ���ε� �Ѱ��
			
			String filename = "";
			String realFilename = "";
			if(profile.getSize() > 0){
				filename = profile.getOriginalFilename();
				realFilename = "d:\\picture\\" + UUID.randomUUID().toString();
				profile.transferTo(new File(realFilename));
			}
			userVo.setFileName(filename);
			userVo.setRealFileName(realFilename);
			
			//����� ��й�ȣ ��ȣȭ����
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			
			int insertCnt = 0;
			try{
				insertCnt = userService.insertUser(userVo);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			//�����Է�(����)
			if(insertCnt == 1){
				session.setAttribute("msg", "���� ��� �Ǿ����ϴ�.");
				return "redirect:/user/userPagingList"; //contextPath �۾� �ʿ�
			}
			else{
				return "user/userForm";
			}
		}
		//�ߺ�üũ ����
		else{
			model.addAttribute("msg", "�ߺ�üũ�� ���� �߽��ϴ�.");
			
			
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
       
       //��й�ȣ ���� ��û ����
       //����ڰ� ���� �Է����� ���� ��� == ���� ��й�ȣ ����
       if(userVo.getPass().equals("")){
    	   UserVo userVoForPass = userService.selectUser(userVo.getUserId());
    	   userVo.setPass(userVoForPass.getPass());
    	   
       }else{//����ڰ� ��й�ȣ�� �ű� ����� ���
    	   userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
       }
       
       
       
       int updateCnt = userService.updateUser(userVo);
       
       //����� ���� ���� ���� : ����� �� ��ȸ ������ �̵�
       if(updateCnt == 1){
    	   //redirect �Ķ���͸� ������ ���
    	   //1.url�� �ۼ�
    	   //  "redirect:/user/user?userId=" + userVo.getUserId();
    	   //2.model��ü�� �Ӽ� : ����� �Ӽ��� �ڵ��� �Ķ���� ��ȯ
    	   //  model.addAttribute("userId", userVo.getUserId());
    	   //  return "redirect:/user/user";
    	   //3. RedirectAtrributes(ra) ��ü�� �̿�
    	   //  ra.addAttribute("userId", userVo.getUserId());
    	   //  return "redirect:/user/user";
//1.          return "redirect:/user/user?userId=" + userVo.getUserId();
//2.   	   model.addAttribute("userId", userVo.getUserId());
    	   ra.addAttribute("userId", userVo.getUserId());
    	   ra.addFlashAttribute("msg", "���� ��� �Ǿ����ϴ�");
    	   //model.addAttribute("msg_model", "���� ��� �Ǿ����ϴ�._model");
    	   
    	   return "redirect:/user/user" + req.getContextPath() + "/user/user";
          //����� ���� ���� ���� : ����� ��� ������ �̵�
       }else{
          return "user/userModify";
       }
       
//		//����� ���� Ȯ��
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
//		//����� ����
//		Part profilePart = req.getPart("profile");
//		
//		//����ڰ� ������ �ø� ���
//		String filename = "";
//		String realFilename = "";
//		
//		if(profilePart.getSize() > 0){
//			//����� ���̺� ���ϸ��� ����
//			//(���� ���ε��� ���ϸ�-filename,
//			// ���� �浹�� �����ϱ� ���� ����� uuid = realFilename)
//			String contentDisposition 
//					= profilePart.getHeader("Content-Disposition");
//			
//			filename = PartUtil.getFileNameFromPart(contentDisposition);
//			realFilename = "d:\\picture\\" + UUID.randomUUID().toString();
//			
//			//��ũ�� ��� ( d:\picture\ + realFileName)
//			profilePart.write(realFilename);
//		}
//		//����ڰ� ������ �ø��� ���� ���
//		
//		userVo.setFilename(filename);
//		userVo.setRealFilename(realFilename);
//		
//		int updateCnt = userService.updateUser(userVo);
//		
//		//����� ���� ���� ���� : ����� �� ��ȸ ������ �̵�
//		if(updateCnt == 1)
//			resp.sendRedirect(req.getContextPath() + "/user?userId=" + userId);
//		//����� ���� ���� ���� : ����� ��� ������ �̵�
//		else
//			doGet(req, resp);
//		
//	}
       
    }
}
