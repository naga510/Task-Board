package com.src.board.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LoggingAspect {

	private static final Logger log = Logger.getLogger(LoggingAspect.class);
	
	@Around("execution(* com.src.board..*.*(..))")
	public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch watch=new StopWatch();
		watch.start();
		Object retVal = joinPoint.proceed();
		watch.stop();
		StringBuffer logMessage = new StringBuffer();
		logMessage.append(joinPoint.getTarget().getClass().getName());
		logMessage.append(".");
		logMessage.append(joinPoint.getSignature().getName());
		
		
//		logMessage.append("(");
//		// append args
//		Object[] args = joinPoint.getArgs();
//		for (int i = 0; i < args.length; i++) {
//			logMessage.append(args[i]).append(",");
//		}
//		if (args.length > 0) {
//			logMessage.deleteCharAt(logMessage.length() - 1);
//		}
//
//		logMessage.append(")");
		logMessage.append(" execution time: ");
		logMessage.append(watch.getTotalTimeMillis());
		logMessage.append(" ms");
		log.info(logMessage.toString());
		return retVal;
	}
	
	
}
