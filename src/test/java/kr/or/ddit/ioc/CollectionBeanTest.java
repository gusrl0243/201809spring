package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.CollectionBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-collection.xml")
public class CollectionBeanTest {
	private static Logger logger = LoggerFactory.getLogger(IocMain.class);

	@Resource(name="collectionBean")
	private CollectionBean collectionBean;
	
	/**
	 * 스프링 설정 파일을 통해 컬렉션 객체를 생성하고, 주입받는다.
	 * list, set, map, properties
	 */
	@Test
	public void testCollectionBean() {
		
		List<String> list = collectionBean.getList();
		logger.debug("list : {} ", list);
		
		//set,map,properties 출력
		Set<String> set = collectionBean.getSet();
		logger.debug("set : {}", set);
		
		Map<String, String> map = collectionBean.getMap();
		logger.debug("map : {}", map);
		
		Properties properties = collectionBean.getProperties();
		logger.debug("properties : {}", properties);
		
		assertNotNull(list);
		assertEquals(3, list.size());
		
		//assert 구문을 이용하여 속성이 정상적으로 주입되었는지 테스트 코드 작성
		assertEquals(3, set.size());
		assertEquals(3, map.size());
		assertEquals(3, properties.size());
	}

}
