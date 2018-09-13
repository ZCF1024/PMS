package com.company.pms.pmsbase.service.log;

import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ManagerAroundTimer implements MethodInterceptor {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ManagerAroundTimer.class.getName());

	private void after(MethodInvocation invocation) {
		String methodName = invocation.getMethod().getName();
		logger.info("Method" + methodName + " end at :" + new Date());
	}

	private void before(MethodInvocation invocation) {
		String methodName = invocation.getMethod().getName();
		logger.info("Method " + methodName + " start at :" + new Date());
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		this.before(invocation);
		Object result = invocation.proceed();
		this.after(invocation);
		return result;
	}

}
