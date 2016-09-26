package com.gzpy.login.controller;

import javax.annotation.Resource;
import javax.jms.Session;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzpy.common.BaseController;
import com.gzpy.user.dao.UserDao;
import com.gzpy.user.entity.User;
@Controller
public class LoginController{
	@Resource
    UserDao userDao;
	//跳转至登陆界面，用户退出，清除session
	@RequestMapping("/gologin.do")
	public String gologin(HttpSession session){
		session.removeAttribute("currentUser");
		return "login.jsp";
	}
	@RequestMapping("/goIndex.do")
	public String goIndex(){
		return "index.jsp";
	}
	
	
	@RequestMapping("/login.do")
	@ResponseBody
	public String userLogin(User user,HttpSession session){
		String message;
		if(userDao.userLogin(user)==null){
			message="loginerror";
			return message;
		}
		User currentUser=userDao.userLogin(user);
		if("Y".equals(currentUser.getDelStatus())){
			message="logindelstatus";
		}else{
			session.setAttribute("currentUser", currentUser);
			message="success";
		}
        return message;
	}
}
