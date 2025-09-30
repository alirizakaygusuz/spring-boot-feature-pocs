package com.alirizakaygusuz.exception_poc.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@AfterThrowing(pointcut = "execution(* com.alirizakaygusuz.exception_poc..service..*(..))"
			, throwing = "ex")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
		log.error("[After Throwing] Method: {} threw exception. Type: {}, Message: {}",
				joinPoint.getSignature().toShortString(), ex.getClass().getSimpleName(), ex.getMessage());
	}

}
