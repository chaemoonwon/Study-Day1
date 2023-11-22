package com.bookshop01.common.log;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAdvice {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

	// 공통 관심사를 관통하는 기능 AOP
	// target �޼����� �Ķ���͵� ������ ����մϴ�.
	@Before("execution(* com.bookshop01.*.service.*.*(..)) or "
			+ "execution(* com.bookshop01.*.dao.*.*(..))")
	public void startLog(JoinPoint jp) {

		
		logger.info("-------------------------------------");
		logger.info("-------------------------------------");

		// 메서드 인수 : 메서드에 전달된 인수 배열을 반환. 메서드 인수를 문자열로 기록 
		logger.info("1:" + Arrays.toString(jp.getArgs()));

		// JoinPoint의 종류(메서드 실행, 메서드 호출 등)를 나타내는 문자열을 반환
		logger.info("2:" + jp.getKind());
 
		// 호출되는 메서드에 대한 정보에 대한 메서드의 이름을 반환
		logger.info("3:" + jp.getSignature().getName());

		// getTarget()의 경우  대상 개체를 반환
		logger.info("4:" + jp.getTarget().toString());

		// getThis()의 경우 현재 실행중인 개체를 가리킴,
		// getThis().getClass()를 했을 경우 해당 객체에 프록시가 적용 되었는 지 확인 가능
		logger.info("5:" + jp.getThis().toString());

	}
	
	@After("execution(* com.bookshop01.*.service.*.*(..)) or "
			+ "execution(* com.bookshop01.*.*.dao.*.*(..))")
	public void after(JoinPoint jp) { 
		logger.info("-------------------------------------");
		logger.info("-------------------------------------");

		// ���޵Ǵ� ��� �Ķ���͵��� Object�� �迭�� �����ɴϴ�. 
		logger.info("1:" + Arrays.toString(jp.getArgs()));

		// �ش� Advice�� Ÿ���� �˾Ƴ��ϴ�. 
		logger.info("2:" + jp.getKind());

		// �����ϴ� ��� ��ü�� �޼ҵ忡 ���� ������ �˾Ƴ� �� ����մϴ�.
		logger.info("3:" + jp.getSignature().getName());

		// target ��ü�� �˾Ƴ� �� ����մϴ�. 
		logger.info("4:" + jp.getTarget().toString());

		// Advice�� ���ϴ� ��ü�� �˾Ƴ� �� ����մϴ� 
		logger.info("5:" + jp.getThis().toString());
	
	}


	// target �޼ҵ��� ���� �ð��� �����մϴ�.
	@Around("execution(* com.bookshop01.*.service.*.*(..)) or "
			+ "execution(* com.bookshop01.*.dao.*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		// 현재 시스템 시간
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));

		
		Object result = pjp.proceed();

		// 종료 시간 기록 : 대상 메서드가 호출되고 현재 시스템 시간
		long endTime = System.currentTimeMillis();
		// 메서드 이름 및 실행에 걸린 시간 기록
		logger.info(pjp.getSignature().getName() + " : " + (endTime - startTime)); 
		logger.info("==============================");

		// Around�� ����� ��� �ݵ�� Object�� �����ؾ� �մϴ�.
		return result;
	}

}
