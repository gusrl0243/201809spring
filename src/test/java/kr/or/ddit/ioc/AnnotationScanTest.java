package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.service.IRangerService;

//설정정보
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-annotation.xml")
public class AnnotationScanTest {
	private static Logger logger = LoggerFactory.getLogger(IocMain.class);
	
   //rangerDao주입
   @Resource(name="rangerDaoImpl")
   private IRangerDao rangerDao;
   
   //rangerService 주입
   @Resource(name="rangerServiceImpl")
   private IRangerService rangerService;
   
   //두개의 스프링 빈이 정상적으로 생성 되었는지 테스트
   /**
    * Method : testAnnotation
    * 작성자 : 18
    * 변경이력 :
    * Method 설명 : rangerDao 빈 컴퍼넌트 스캔 테스트
    */
   @Test
   public void testRangerDaoBean() {
      /***Given***/
      
      /***When***/

      /***Then***/
      assertNotNull(rangerDao);
   }
   
   /**
    * Method : testRangerServiceBean
    * 작성자 : 18
    * 변경이력 :
    * Method 설명 : rangerService 빈 컴퍼넌트 스캔 테스트ㄴ
    */
   @Test
   public void testRangerServiceBean() {
      /***Given***/
      
      /***When***/

      /***Then***/
      assertNotNull(rangerService);
   }
}