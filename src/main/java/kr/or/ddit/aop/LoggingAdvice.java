package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAdvice {
   private Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

   public void beforeMethod(JoinPoint joinPoint){
      logger.debug("loggingAdvice before");
   }
   
   public void afterMethod(JoinPoint joinPoint){
      String className = joinPoint.getTarget().getClass().getName();
      String methodName = joinPoint.getSignature().getName();
      
      logger.debug("loggingAdvice after - {}.{}", className, methodName);
   }
   
   public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable{
      // �ٽ� ���� ȣ�� ��.
      long startTime = System.currentTimeMillis();
      
      // �ٽ� ���� ȣ��.
      Object[] args = joinPoint.getArgs();
      Object returnObj = joinPoint.proceed(args);
      
      // �ٽ� ���� ȣ�� ��.
      long endTime = System.currentTimeMillis();
      logger.debug("profilingTime : {}", endTime - startTime);
      
      return returnObj;
   }
   
   
}







