package com.gzpy.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
	
	@Autowired
	private HttpServletRequest request;
	
	protected ModelAndView ajaxDone(int statusCode, String message, String callbackType) {
		ModelAndView mav = new ModelAndView("ajaxDone.jsp");
		mav.addObject("statusCode", statusCode);
		mav.addObject("message", message);
		mav.addObject("callbackType", callbackType);
		return mav;
	}
	
	protected ModelAndView ajaxDone(int statusCode, String message, String navTabId,String callbackType) {
		ModelAndView mav = new ModelAndView("ajaxDone.jsp");
		mav.addObject("navTabId", navTabId);
		mav.addObject("statusCode", statusCode);
		mav.addObject("message", message);
		mav.addObject("callbackType", callbackType);
		return mav;
	}
	
	protected ModelAndView ajaxDoneSuccess(String message) {
		return ajaxDone(200, message, "");
	}
	
	protected ModelAndView ajaxDoneSuccess(String message,String navTabId,String callbackType) {
		return ajaxDone(200, message, navTabId, callbackType);
	}

	protected ModelAndView ajaxDoneError(String message) {
		return ajaxDone(300, message, "");
	}
}
