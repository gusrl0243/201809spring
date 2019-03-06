package kr.or.ddit.mvc;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.exception.NoFileException;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.validator.UserVoValidator;
import kr.or.ddit.util.model.PageVo;

@Controller
public class MvcController {
	private Logger logger = LoggerFactory.getLogger(UserVo.class);
	
	/**
	 * part�� �׽�Ʈ �� view ��û
	 * @return
	 */
	@RequestMapping("/mvc/view")
	public String view(){
		return "mvc/view";
	}
	
	
	/**
	 * fileupload ó�� ��û �׽�Ʈ
	 * @return
	 */
	//�Ķ���� : userId (text), profile (file)
	@RequestMapping("/mvc/fileupload")
	public String fileupload(@RequestParam("userId") String userId ,
							 @RequestPart("profile") MultipartFile multipartFile){
		
		logger.debug("userId : {}",  userId);
		logger.debug("getOriginalFilename() : {} ", multipartFile.getOriginalFilename());
		logger.debug("getNma() : {} ", multipartFile.getName());
		logger.debug("size() : {} ", multipartFile.getSize());
		
		String filename = multipartFile.getOriginalFilename() + "_" + UUID.randomUUID().toString();
		File profile = new File("d:\\picture\\" + filename);
		try {
			multipartFile.transferTo(profile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "mvc/view";
	}
	
	
    @RequestMapping("/textView")
    public String textView(){
       return "mvc/textView";
    }
	
	   // @RequestParam ������̼��� �������� �ʾƵ� �ν��Ͻ����̶� �����ϸ� ���ε� ��.
	   // �Ķ���͸��̶� �ν��Ͻ� ���̶� �ٸ� ��� -> @RequestParam
	   
	   // BindingResult��ü�� command��ü(vo)�� ���ε� �������� �߻��� ����� ��� ��ü�� �ݵ��
	   // command��ü �޼��� ���� �ڿ� ��ġ �ؾ��Ѵ�.
	   // (UserVo userVo, BindingResult result, Model model) (O)
	   // (UserVo userVo, Model model, BindingResult result) (X)
	   @RequestMapping("/textReq")
	   public String textReq(UserVo userVo, BindingResult result, Model model){
	      new UserVoValidator().validate(userVo, result);
	      
//	      logger.debug("paramCheck : {}", paramCheck);
	      
	      logger.debug("userId : {}", userVo.getUserId());
	      logger.debug("pass : {}", userVo.getPass());
	      
	      if(result.hasErrors()){
	         logger.debug("has error!");
	         
	         return "mvc/textView";
	      }
	      
	      // pass : 8�ڸ� �̻�
//	      if(userVo.getPass().length() < 8){
//	         model.addAttribute("passwordLengthMsg", "��й�ȣ�� 8�ڸ� �̻��̾�� �մϴ�.");
//	      }
	      
	      return "mvc/textView";
	   }
	   
	   @RequestMapping("/textReqJsr303")
	   public String textReqJsr303(@Valid UserVo userVo, BindingResult result){
		   logger.debug("has errors(jsr303): {}", result.hasErrors());
		   
		   return "mvc/textView";
	   }
	   
	   @RequestMapping("/textReqValJsr303")
	   public String textReqValJsr303(@Valid UserVo userVo, BindingResult result){
		   logger.debug("has errors(Valjsr303): {}", result.hasErrors());
		   
		   return "mvc/textView";
	   }
	   
	   @InitBinder
	   public void initBirder(WebDataBinder binder){
		   binder.addValidators(new UserVoValidator());
	   }
	   
	   /**
	    * arithmetic ���� ���� �߻�
	    * @return
	    */
	   @RequestMapping("throwArith")
	   public String throwArtihmeticException(){
		   if(1==1)
		   	throw new ArithmeticException();
		   return "mvc/textView";
	   };
	   
	   @RequestMapping("/throwNoFileException")
	   public String throwNoFileException() throws NoFileException{
		   Resource resource =
		   new ClassPathResource("kr/or/ddit/config/spring/no-exsits.xml");
		
		   try{
			   resource.getFile();
		   }catch(IOException e){
			   e.printStackTrace();
			   throw new NoFileException();
		   }
		   
		   return "mvc/textView";
	   }
	   
	   
	   
}
