package kr.or.ddit.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.dao.RangerDaoImpl;
import kr.or.ddit.ranger.service.IRangerService;
import kr.or.ddit.ranger.service.RangerServiceImpl;

//�ش� �ڹ������� ������ �� ���� Ŭ�������� �˷��ִ� ������̼�
@Configuration
public class SpringJavaConfig {

	// <bean name="">
	@Bean
	public IRangerDao getRangerDao() {
		IRangerDao rangerDao = new RangerDaoImpl();
		return rangerDao;
	}
	@Bean
	public IRangerService getRangerService() {
		//rangerService�� �����ؾ��ϴ� rangerDao ��ü��
		//���������� �����Ǵ� ������ ���� �����ؾ��Ѵ�.
		// *** ������ rangerDao�� new �����ڸ� ���ؼ� �����ϸ�ȵȴ�.
		IRangerService rangerService = new RangerServiceImpl(getRangerDao());
		
		return rangerService;
	}

	
}
