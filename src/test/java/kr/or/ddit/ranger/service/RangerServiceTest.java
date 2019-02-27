package kr.or.ddit.ranger.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//servlet-context.xml, application-context.xml
@ContextConfiguration({"classpath:kr/or/ddit/config/spring/application-context.xml"})
public class RangerServiceTest {
	
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	@Test
	public void testGetRanger_overflowIndex() {
		int index = 6;
		
		String ranger = rangerService.getRanger(index);

		assertEquals("james", ranger);
	}

}
