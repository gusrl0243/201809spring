package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.model.RangerVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-type.xml")
public class PropertyEditorTest {

	@Resource(name="rangerVo")
	private RangerVo rangerVo;
	@Test
	public void testRangerVo() {
		
		
		
		String userId = rangerVo.getUserId();
		Date birth = rangerVo.getBirth();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		String birthStr = sdf.format(birth);
		
		assertNotNull(rangerVo);
		assertEquals("brown", userId);
		assertEquals("2018-08-08", birthStr);
	}

}
