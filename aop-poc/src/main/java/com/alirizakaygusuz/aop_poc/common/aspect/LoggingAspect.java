package com.alirizakaygusuz.aop_poc.common.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Aspect
@Component
@Slf4j
public class LoggingAspect {
	
	
	@Before("execution(* com.alirizakaygusuz.aop_poc..service..*(..))")
	public void logBefore(JoinPoint joinPoint) {
		log.info("[Before] Method: {} called with args: {}", 
				joinPoint.getSignature().toShortString(),
				Arrays.toString(joinPoint.getArgs()));
	}
	
	
	@AfterReturning(pointcut =  "execution(* com.alirizakaygusuz.aop_poc..service..*(..))" ,
			returning = "result")
	public void logAfterReturning(JoinPoint joinPoint , Object result) {
		log.info("[After Returning] Method: {} returned: {}",
				joinPoint.getSignature().toShortString(),
				result);
	}
	
	@AfterThrowing(pointcut =  "execution(* com.alirizakaygusuz.aop_poc..service..*(..))" ,
			throwing = "ex")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
	    log.error("[After Throwing] Method: {} threw exception. Type: {}, Message: {}",
	              joinPoint.getSignature().toShortString(),
	              ex.getClass().getSimpleName(),
	              ex.getMessage());
	}

}
