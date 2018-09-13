package com.company.pms.pmsbase.service.log;

import java.lang.reflect.Method;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ManagerAfterLogger implements AfterReturningAdvice {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ManagerAfterLogger.class.getName());

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		logger.warn("Method " + target.getClass().getName() + ":" + method.getName()
				+ " was executed and returnValue = " + returnValue);
	}

}
