package kr.or.ddit.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
      {"classpath:kr/or/ddit/config/spring/servlet-context.xml",
       "classpath:kr/or/ddit/config/spring/application-context.xml"})
@WebAppConfiguration // ������ �����̳ʸ� ���鶧 WebApplicationContext�� ����. �������
                // applicationContext.
public class WebTestConfig {
   
   @Autowired
   private WebApplicationContext context;
   
   // private -> protected(��ӹ��� �༮�� ���� ����.)
   protected MockMvc mockMvc;

   // * test code �������.
   // @BeforeClass(static -> ���󵵰� ������.)
   //  @Before - @Test - @After
   //  @Before - @Test - @After
   //  ...
   // @AfterClass(static -> ���󵵰� ������.

   @Before
   public void setup(){
      mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
   }

}