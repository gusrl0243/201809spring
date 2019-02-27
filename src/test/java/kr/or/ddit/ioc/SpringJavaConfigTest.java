package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.SpringJavaConfig;
import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.service.IRangerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {SpringJavaConfig.class})
public class SpringJavaConfigTest {
	private static Logger logger = LoggerFactory.getLogger(IocMain.class);
	
//	@Autowired
	@Resource(name="getRangerDao")
	private IRangerDao rangerDao;

	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	@Test
	public void testRangerDao() {
	
		logger.debug("rangers : {}", rangerDao.getRangers());
		
		assertNotNull(rangerDao);
	}
	
	@Test
	public void testRangerService() {
		
		logger.debug("rangers : {}", rangerService.getRangers());
		
		assertNotNull(rangerService);
	}
	
	/**
	 * rangerService ½ºÇÁ¸µ 
	 */
	@Test
	public void testRangerDaoEquals() {
		
		IRangerDao rangerServiceDao = rangerService.getRangerDao();
		
		assertEquals(rangerDao, rangerServiceDao);
	}

}
