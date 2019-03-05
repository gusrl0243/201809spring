package kr.or.ddit.mvc;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Controller
public class MvcController {
	private Logger logger = LoggerFactory.getLogger(UserVo.class);
	
	/**
	 * part를 테스트 할 view 요청
	 * @return
	 */
	@RequestMapping("/mvc/view")
	public String view(){
		return "mvc/view";
	}
	
	
	/**
	 * fileupload 처리 요청 테스트
	 * @return
	 */
	//파라미터 : userId (text), profile (file)
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
	
	
	
}
