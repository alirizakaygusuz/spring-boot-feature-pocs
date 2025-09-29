package com.alirizakaygusuz.aop_poc.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
	
	@Around("execution (* com.alirizakaygusuz.aop_poc..service..*(..))")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		
		
		Object result = joinPoint.proceed();
		
		long duration = System.currentTimeMillis() - start;
		
		
		log.info("[Performance] Method {} execution in {} ms" , 
				joinPoint.getSignature().toShortString() ,
				duration);
		
		
		return result;
	}

}
