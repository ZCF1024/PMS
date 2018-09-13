package com.company.pms.pmsbase.service.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 系统服务组件Aspect切面Bean
 * 
 * @author liuxiaoming
 *
 */
// 声明这是一个组件
@Component
// 声明这是一个切面Bean
@Aspect
public class LoggingAspect {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(LoggingAspect.class.getName());

	// 配置后置通知,使用在方法aspect()上注册的切入点
	@After("aspect()")
	public void after(JoinPoint joinPoint) {
		if (logger.isDebugEnabled()) {
			logger.debug("after(JoinPoint) - end"); //$NON-NLS-1$
		}
	}

	// 配置后置返回通知,使用在方法aspect()上注册的切入点
	@AfterReturning("aspect()")
	public void afterReturn(JoinPoint joinPoint) {
		if (logger.isDebugEnabled()) {
			logger.debug("afterReturn(JoinPoint) - end"); //$NON-NLS-1$
		}
	}

	// 配置抛出异常后通知,使用在方法aspect()上注册的切入点
	@AfterThrowing(pointcut = "aspect()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex) {
		if (logger.isDebugEnabled()) {
			logger.debug("afterThrow(JoinPoint, Exception) - start"); //$NON-NLS-1$
		}

	}

	// 配置环绕通知,使用在方法aspect()上注册的切入点
	@Around("aspect()")
	public void around(JoinPoint joinPoint) {
		if (logger.isDebugEnabled()) {
			logger.debug("around(JoinPoint) - start"); //$NON-NLS-1$
		}

		long start = System.currentTimeMillis();
		try {
			((ProceedingJoinPoint) joinPoint).proceed();
			long end = System.currentTimeMillis();
			if (logger.isInfoEnabled()) {
				logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
			}
		} catch (Throwable e) {
			logger.error("around(JoinPoint)", e); //$NON-NLS-1$
			long end = System.currentTimeMillis();
			if (logger.isInfoEnabled()) {
				logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : "
						+ e.getMessage());
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("around(JoinPoint) - end"); //$NON-NLS-1$
		}
	}

	// 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution(* edu.zut.cs.javaee.dream.*.service.*..*(..))")
	public void aspect() {
	}

	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint) {
		if (logger.isDebugEnabled()) {
			logger.debug("before(JoinPoint) - end"); //$NON-NLS-1$
		}
	}

}