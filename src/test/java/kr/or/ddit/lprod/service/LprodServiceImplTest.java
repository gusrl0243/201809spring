package kr.or.ddit.lprod.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.service.IUserService;

public class LprodServiceImplTest extends LogicTestConfig{

	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	

	@Test
	public void testGetAllLprod() {
		List<LprodVo> lprodList = lprodService.getAllLprod();
		
		assertNotNull(lprodList);
		assertEquals(14, lprodList.size());
	}

}
