package com.bookshop01.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


//
public class ViewNameInterceptor extends  HandlerInterceptorAdapter{
	
	
	
	//전처리 작업
	//	controller가 호출되기 전에 실행하는 interceptor
	   @Override
	   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		   
		   // HttpServletRequest를 통해서 현재 요청의 뷰 이름을 가져온 뒤
		   
		   
		   try {
			   
		   // viewName이라는 이름으로 저장
			String viewName = getViewName(request);
			System.out.println("여기는viewName 인터셉터 :" + viewName);
			
			// viewName 속성을 설정 / 
			// 현재 viewName의 값을 대입해 뷰로 전달할 데이터를 설정하는 데 사용된다.
			request.setAttribute("viewName", viewName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		   
		   //실행 성공 , 실패 둘다 true 반환
		   return true;
	   }
	   
	   
	   
	   
	   
	   //후처리 작업
	   
	   @Override
	   public void postHandle(HttpServletRequest request, HttpServletResponse response,
	                           Object handler, ModelAndView modelAndView) throws Exception {
	   }

	   
	   
	   
	   //HandlerInterceptor 인터페이스에서 제공하는 메서드 
	   //컨트롤러의 핸들러 메서드가 모두 실행된 후 (뷰가 렌더링된 후) 호출
	   //주로 리소스를 해제하거나 로깅과 같은 마무리 작업을 수행한다.
	   
	   //메서드 리턴 값이 없기에 예외가 발생해도 클라이언트에게 전달되지 않는다.
	   //주로 로깅 등을 위한 용도
	   @Override
	   public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
	                                    Object handler, Exception ex)    throws  Exception {
	   }
	   
	   
	   
	   
	   
	   //HttpServletRequest에서 받은 뷰 이름을 추출하는 메서드
	   private String getViewName(HttpServletRequest request) throws Exception {
		   
		   //HttpServletRequest에서 컨텍스트 경로 getContextPath를 가져온다.
			String contextPath = request.getContextPath();
			
			//"javax.servlet.include.request_uri" 속성을 통해 현재 요청의
			// URL를 가져온다.
			String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
			
			//만약 없다면 직접 request.getRequestURI를 사용한다.
			if (uri == null || uri.trim().equals("")) {
				uri = request.getRequestURI();
			}
			
			
			
			//컨텍스트 경로의 길드를 계산하는 부분
			//경로가 null이거나 빈(" ")문자열이 아닐 경우에만 begin을 컨텍스트 경로의 길리로 설정 
			
			//컨텍스트 경로가 존재하면 begin은 컨텍스트 경로의 길이로 설정
			//만약 경로가 없으면 begin은 여전히 0으로 유지
			int begin = 0;
			if (!((contextPath == null) || ("".equals(contextPath)))) {
				begin = contextPath.length();
			}

			int end;
			if (uri.indexOf(";") != -1) {
				end = uri.indexOf(";");
			} else if (uri.indexOf("?") != -1) {
				end = uri.indexOf("?");
			} else {
				end = uri.length();
			}

			String fileName = uri.substring(begin, end);
			if (fileName.indexOf(".") != -1) {
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
			}
			if (fileName.lastIndexOf("/") != -1) {
				fileName = fileName.substring(fileName.lastIndexOf("/",1), fileName.length());
			}
			return fileName;
		}
	}
