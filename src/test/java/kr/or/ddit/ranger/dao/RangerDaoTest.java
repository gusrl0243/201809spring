package kr.or.ddit.ranger.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//servlet-context.xml, application-context.xml
@ContextConfiguration({"classpath:kr/or/ddit/config/spring/application-context.xml"})
public class RangerDaoTest {
	
	@Resource(name="rangerDao")
	private IRangerDao rangerDao;
	
	@Resource(name="datasource")
	private DataSource datasource;
	
	@Before
	public void setup(){
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		rdp.addScript(new ClassPathResource("kr/or/ddit/config/db/init.sql"));
		rdp.setContinueOnError(false);
		DatabasePopulatorUtils.execute(rdp, datasource);
	}
	
	@Test
	public void testGetRanger_overflowIndex() {
		int index = 6;
		
		String ranger = rangerDao.getRanger(index);

		assertEquals("james", ranger);
	}

	
	/**
	 * 전체 레인저스 조회(db)
	 */
	@Test
	public void testGetRangerDb(){
		List<Map<String,String>> rangerList = rangerDao.getRangerDb();
		
		assertEquals(4, rangerList.size());
	}
	
	@Test
	public void testGetRangerId(){
		
		Map<String, String> ranger = rangerDao.getRanger("brown");
		
		assertNotNull(ranger);
		assertEquals("브라운", ranger.get("NAME"));
	}
	
	@Test
	public void testInsertRanger(){
		Map<String, String> ranger = new HashMap<String,String>();
		ranger.put("id", "ryon");
		ranger.put("name", "라이언");
		
		int insertCnt = rangerDao.insertRanger(ranger);
		
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void testDeleteRanger(){
		rangerDao.deleteRangerDept("brown");
		
		int deleteCnt = rangerDao.deleteRanger("brown");
		
		assertEquals(1, deleteCnt);
	}
}
