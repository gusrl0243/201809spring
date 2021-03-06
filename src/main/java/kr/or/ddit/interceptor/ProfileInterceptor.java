package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.user.model.UserVo;

public class ProfileInterceptor extends HandlerInterceptorAdapter{
	private Logger logger = LoggerFactory.getLogger(UserVo.class);

	/**
	 * controller method 실행전
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.debug("ProfileInterceptor preHandle");
		//다른 인터셉터 혹은 controller로 요청을 계속 위임 처리
		
		request.setAttribute("startTime", System.currentTimeMillis());
		
		return true;
	}

	/**
	 * controller method 실행 후
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("ProfileInterceptor postHandle");
		
		long startTime = (Long)request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		
		long profilingTime = endTime-startTime;
		
		logger.debug("{}-profilingTime : {}", request.getRequestURI(), profilingTime);
		//proHandel에서 구한 startTime 값을 가져와야...??
	}
	
	
}
