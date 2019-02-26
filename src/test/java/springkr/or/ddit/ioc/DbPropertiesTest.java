package springkr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.CollectionBean;
import kr.or.ddit.ioc.DbProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-placeholder.xml")
public class DbPropertiesTest {

	@Resource(name="dbProperties")
	private DbProperties dbProperties;
	
	//property placeholder Å×½ºÆ®
	@Test
	public void testPlaceholder() {
		
		String url = dbProperties.getUrl();
		String driverClassName = dbProperties.getDriverClassName();
		String usename = dbProperties.getUsername();
		String password = dbProperties.getPassword();
		
		assertEquals("jdbc:oracle:thin:@localhost:1521:XE",url);
		assertEquals("oracle.jdbc.driver.OracleDriver",driverClassName);
		assertEquals("PC18_PC",usename);
		assertEquals("java",password);
	}

}
