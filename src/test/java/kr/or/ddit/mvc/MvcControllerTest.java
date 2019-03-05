package kr.or.ddit.mvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import kr.or.ddit.test.WebTestConfig;

public class MvcControllerTest extends WebTestConfig{

	

	/**
	 * ���� ���ε� �׽�Ʈ �� ��û �׽�Ʈ
	 * @throws Exception 
	 */
	@Test
	public void testView() throws Exception {
		
		mockMvc.perform(get("/mvc/view"))
					.andExpect(status().isOk())
					.andExpect(view().name("mvc/view"));
		
	}
	
	/**
	 * ���� ���ε� �׽�Ʈ
	 * @throws Exception 
	 */
	@Test
	public void testFileupload() throws Exception{
		File profileFile = new File("D:\\web\\������������\\sally.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "sally.png", "image/png", fis);
		
		
		mockMvc.perform(fileUpload("/mvc/fileupload")
								 .file(file)
								 .param("userId","brown"))
								 .andExpect(status().isOk())
								 .andExpect(view().name("mvc/view"));
	}

}
