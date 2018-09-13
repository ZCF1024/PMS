package com.company.pms.pmsbase.service.log;

import java.lang.reflect.Method;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

@Aspect
@Component("beforeLogger")
public class ManagerBeforeLogger implements MethodBeforeAdvice {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ManagerBeforeLogger.class.getName());

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		logger.info("Method" + target.getClass().getName() + ":" + method.getName() + " will be executed !");
	}

}
