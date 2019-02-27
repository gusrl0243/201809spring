package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context.xml")
public class IocMainTest {

	//rangerDao, rangerService
	
	//DI(Dependency Injection)
	@Resource(name="rangerDaoSpringBean")
	private IRangerDao rangerDao;
	
	@Resource(name="rangerDaoSpringBean")
	private IRangerDao rangerDao2;
	
	@Resource(name="rangerDao")
	private IRangerDao rangerDao3;
	
	@Resource(name="rangerDaoPrototype")
	private IRangerDao rangerDaoPrototype1;
	
	@Resource(name="rangerDaoPrototype")
	private IRangerDao rangerDaoPrototype2;
	
	
	
	@Test
	public void testRangerDao(){
		//���� ���
		//ApplicationContext context = new ......
		//DL �� ���� ������ Ŀ���̳ʿ� ������ ���� ��û
		//IRangerDao rangerDao = context.getBean("rangerDao");
		
		//���� ���
		//������ �����̳� ������ junit runner���� ����
		//�츮�� ����ϰ��� �ϴ� ��ü�� DI(Dependency Injection)�� ���� ���Թ޴´�.
		//@Autowired(������), @Resource (java ǥ��)
		
		//�׽�Ʈ ���
		//1. ���������� ���������� �����ǰ�, ������ ������ ������
		
		assertNotNull(rangerDao);
	}
	
	/**
	 * ���� �̸��� ������ ��(scope = singleton)��
	 *  �ι� ���� �޾��� �� �ش� ��ü�� ������ ��ü���� �� 
	 */
	@Test
	public void testSpringSingletonBean() {
		
		assertEquals(rangerDao, rangerDao2);
	}
	
	/**
	 * ���� Ŭ������ ����� ���� �ٸ� ������ ��(singleton)��
	 * ������ ������ ���Ǵ�� �������� �� ������ ���� �������� �׽�Ʈ  
	 */
	@Test
	public void testSpringSingletonBean2() {
		//������ ���Ͽ� ���ϸ� ���� Ŭ�����κ��� �ϳ��� �ν��Ͻ��� �����ϰ� �ϴ� ������
		//�̱���
		//rangerDaoSpringBean(rangerDao)�� rangerDao(rangerDao3) ������ ���� ���� ���� RangerDaoImpl
		//Ŭ�����κ��� ������ ��ü.
		//������ ������ ���ǿ� ���ؼ� �ΰ��� ��ü�� ���� ���ƾ� �ȴ�.
		//assertEquals(rangerDao, rangerDao3);
		
		//������ bean scope���� �̾߱��ϴ� singleton�� ������ �̸� ������ ��ü�� �����ȴ�.
		//rangerDaoSpringBean�� rangerDao�� ���� Ŭ������ ���� �����Ⱦ�����
		//spring bean �̸��� ���� �ٸ��� ���� �Ͽ��� ������ ������ �����̳� ��������
		//���� �ٸ� ��ü�� �ν��� �Ѵ�.
		assertNotEquals(rangerDao, rangerDao3);
	}
	
	/**
	 * ������ prototype bean�׽�Ʈ
	 */
	@Test
	public void testSpringPrototypeBean() {
		assertNotEquals(rangerDaoPrototype1, rangerDaoPrototype2);
	}
	
}
