package com.gzpy.user.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.gzpy.common.BaseController;

import com.gzpy.user.entity.User;
import com.gzpy.user.service.UserService;
import com.gzpy.util.GenerateGUID;
/**
 * 用户管理
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
private int currentPage;//当前页码
	
	private int pageSize;//单个页面显示数量
	@RequestMapping("/toUserManage.do")
	public String toUserManage(String pageNum,String numPerPage,ModelMap map){
		if(pageNum == null || "".equals(pageNum)){
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(pageNum);
		}
		
		if(numPerPage == null || "".equals(numPerPage)){
			pageSize = 5;
		} else {
			pageSize = Integer.parseInt(numPerPage);
		}
		int totalPage = userService.findUserByCurrentPage(currentPage, pageSize).getTotalPages();
		long totalCount = userService.findUserByCurrentPage(currentPage,pageSize).getTotalElements();
		List<User> list_user = userService.findUserByCurrentPage(currentPage,pageSize).getContent();
		map.addAttribute("currentPage", currentPage);
		map.addAttribute("pageSize", pageSize);
		map.addAttribute("totalPage", totalPage);
		map.addAttribute("totalCount", totalCount);
		map.addAttribute("list_user",list_user);
		return "user/userList.jsp";
	}
	@RequestMapping("/toAddUser.do")
	public String toAddUser(){
		
		return "user/addUser.jsp";
	}
	
	@RequestMapping("/addUser.do")
	public ModelAndView addUser(User user,String userEmployDate){
		user.setEmployDate(Date.valueOf(userEmployDate));
		user.setUserId(GenerateGUID.getGuid());
		user.setDelStatus("N");
		User result=userService.saveUser(user);
		if(result == null || "".equals(result)){
			return this.ajaxDoneError("添加失败,请重新添加！");
		} else {
			return this.ajaxDoneSuccess("添加成功", "userManage", "closeCurrent");
		}
		
	}
	@RequestMapping("/deleteUser.do")
public ModelAndView deleteUser(String userId){
		
		try {
			userService.deleteUser(userId);
			return this.ajaxDoneSuccess("删除成功", "userManage", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.ajaxDoneError("删除失败");
	}
	@RequestMapping("/toUpdateUser.do")
public String toUpdateUser(String userId,ModelMap map){
		
		User user = userService.findUserById(userId);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(user.getEmployDate());
		
		StringBuffer sb = new StringBuffer();
		
		if(calendar.get(Calendar.MONTH) < 10){
			sb.append(calendar.get(Calendar.YEAR)).append("-0");
		} else {
			sb.append(calendar.get(Calendar.YEAR)).append("-");
		}
		
		sb.append(calendar.get(Calendar.MONTH) + 1).append("-");
		sb.append(calendar.get(Calendar.DATE));
		
		map.addAttribute("employDate", sb);
		map.addAttribute("user",user);
		return "user/updateUser.jsp";
	}
	@RequestMapping("/updateUser.do")
	public ModelAndView updateUser(User user,String userEmployDate){
		user.setEmployDate(Date.valueOf(userEmployDate));
		User result=userService.saveUser(user);
		if(result!=null){
			return this.ajaxDoneSuccess("修改成功", "userManage", "closeCurrent");
		}else{
			return this.ajaxDoneError("修改失败,请重新修改！");
		}
	}
	@RequestMapping("/toUserDetail.do")
	public String  toUpdateDetail(String userId,ModelMap map){
		User user=userService.findUserById(userId);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(user.getEmployDate());
		
		StringBuffer sb = new StringBuffer();
		
		if(calendar.get(Calendar.MONTH) < 10){
			sb.append(calendar.get(Calendar.YEAR)).append("-0");
		} else {
			sb.append(calendar.get(Calendar.YEAR)).append("-");
		}
		
		sb.append(calendar.get(Calendar.MONTH) + 1).append("-");
		sb.append(calendar.get(Calendar.DATE));
		
		map.addAttribute("employDate", sb);
		map.addAttribute("user",user);
		
		return "/user/userDetail.jsp";
		
	}
	//根据用户姓名查询用户
	@RequestMapping("/findUserByName.do")
	public String findUserByName(String pageNum, String numPerPage, String inputName, ModelMap map){
		if (pageNum == null || "".equals(pageNum)) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(pageNum);
		}

		if (numPerPage == null || "".equals(numPerPage)) {
			pageSize = 5;
		} else {
			pageSize = Integer.parseInt(numPerPage);
		}
		int totalPage = userService.findUserByName(inputName,currentPage, pageSize)
				.getTotalPages();
		long totalCount = userService.findUserByName(inputName,currentPage, pageSize)
				.getTotalElements();
		List<User> list_user = userService.findUserByName(inputName,currentPage, pageSize)
				.getContent();
		map.addAttribute("list_user",list_user);
		  map.addAttribute("totalPage",totalPage);
		  map.addAttribute("totalCount",totalCount);
		  map.addAttribute("numPerPage", numPerPage);
		  map.addAttribute("currentPage", currentPage);
		  return "user/userList.jsp";
	}
}
