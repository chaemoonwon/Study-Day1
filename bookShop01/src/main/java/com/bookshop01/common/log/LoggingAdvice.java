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

	//주 기능 나오기 전에 실행
	// target 메서드의 파라미터등 정보를 출력합니다.
	
//	service에 있는 모든 클래스 메서드 
//	dao에 있는 모든 클래스 메서드
//	(..)은 * com.bookshop01.*.service.*.* 앞이 일치하는 것들 전부 가져오기
//	(.)은 정확하게 일치하는 것
//	*(리턴타입 / 다 된 다는 뜻) com.bookshop01(com.bookshop 패키지 *).*.service.*.*(..)
	@Before("execution(* com.bookshop01.*.service.*.*(..)) or "
			+ "execution(* com.bookshop01.*.dao.*.*(..))")
	public void startLog(JoinPoint jp) {

		
//		주기능이 사용되기 전에 로그 실선 그어주는 용도 (AOP 보조기능)
		logger.info("-------------------------------------");
		logger.info("-------------------------------------");

		// 전달되는 모든 파라미터들을 Object의 배열로 가져옵니다. 
		logger.info("1:" + Arrays.toString(jp.getArgs()));

		//해당 Advice의 타입을 알아냅니다. 
		logger.info("2:" + jp.getKind());

		// 실행하는 대상 객체의 메소드에 대한 정보를 알아낼 때 사용합니다. 
		logger.info("3:" + jp.getSignature().getName());

		// target 객체를 알아낼 때 사용합니다. 
//		Target (대상 클래스)
		logger.info("4:" + jp.getTarget().toString());

		// Advice를 행하는 객체를 알아낼 때 사용합니다. 
		logger.info("5:" + jp.getThis().toString());

	}
	
	
	//주기능 나온 후 실행 (끝날 때)
	@After("execution(* com.bookshop01.*.service.*.*(..)) or "
			+ "execution(* com.bookshop01.*.*.dao.*.*(..))")
	public void after(JoinPoint jp) { 
		logger.info("-------------------------------------");
		logger.info("-------------------------------------");

		// 전달되는 모든 파라미터들을 Object의 배열로 가져옵니다. 
		logger.info("1:" + Arrays.toString(jp.getArgs()));

		// 해당 Advice의 타입을 알아냅니다. 
		logger.info("2:" + jp.getKind());

		// 실행하는 대상 객체의 메소드에 대한 정보를 알아낼 때 사용합니다.
		logger.info("3:" + jp.getSignature().getName());

		// target 객체를 알아낼 때 사용합니다. 
		logger.info("4:" + jp.getTarget().toString());

		// Advice를 행하는 객체를 알아낼 때 사용합니다 
		logger.info("5:" + jp.getThis().toString());
	
	}


	
	
	// target 메소드의 동작 시간을 측정합니다.
	
	// @Around = 공통 관심사항을 동작해주는 어노테이션
	//공통 관심사항 = 어플리케이션의 여러 부분에서 반복적으로 나타나는 코드 동작
	//메소드의 동작 시간 측정을 여러 곳에서 같은 코드를 사용해 계속 재야하기 때문에 AOP 사용
	@Around("execution(* com.bookshop01.*.service.*.*(..)) or "
			+ "execution(* com.bookshop01.*.dao.*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));

		// 실제 타겟을 실행하는 부분이다. 이 부분이 없으면 advice가 적용된 메소드가 동작하지않습니다.
		Object result = pjp.proceed(); // proceed는 Exception 보다 상위 Throwable을 처리해야 합니다.

		long endTime = System.currentTimeMillis();
		// target 메소드의 동작 시간을 출력한다.
		logger.info("spl 실행 후 시간측정 "+pjp.getSignature().getName() + " : " + (endTime - startTime)+ "초"); 
		logger.info("==============================");

		// Around를 사용할 경우 반드시 Object를 리턴해야 합니다.
		return result;
	}

}
