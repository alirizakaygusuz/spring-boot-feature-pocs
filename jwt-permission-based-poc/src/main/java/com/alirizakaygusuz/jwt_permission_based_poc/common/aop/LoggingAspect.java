package com.alirizakaygusuz.jwt_permission_based_poc.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
	
	
	@AfterThrowing(pointcut = "execution(* com.alirizakaygusuz.jwt_permission_based_poc..service..*(..)"
			,throwing = "ex")
	public void logAfterThrowing(JoinPoint joinPoint , Throwable ex) {
		log.error("[After Throwing] Class: {}, Method: {}, Exception: {}, Message: {}",
			    joinPoint.getTarget().getClass().getSimpleName(),
			    joinPoint.getSignature().getName(),
			    ex.getClass().getSimpleName(),
			    ex.getMessage());
	}

}
