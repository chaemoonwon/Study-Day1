package com.bookshop01.center.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;

@Controller("centerController")
@RequestMapping(value="/center")
public class CenterControllerImpl extends BaseController implements CenterController{
	
	
	  @Override
	  @RequestMapping(value = "/center.do", method = RequestMethod.GET)
	    public ModelAndView center(HttpServletRequest request, HttpServletResponse response) {
	        
			ModelAndView mav = new ModelAndView();

		/*
		 * HttpSession session=request.getSession();
		 */
	        
	        // 뷰 이름 설정 (실제 뷰 이름에 맞게 수정)
	        mav.setViewName("redirect:/center/center.do");
	        
	        return mav;
	    }
	
}
