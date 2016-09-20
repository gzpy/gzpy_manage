package com.gzpy.project.controll;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gzpy.project.entity.Project;
import com.gzpy.project.service.ProjectService;

@Controller
public class ProjectAction {
	@Resource
	ProjectService projectService;
	@Transactional
	@RequestMapping("/find.do")
	  public String findProject(String currentpage,ModelMap map){
		  if(currentpage==null){
		     currentpage="1";
		  }
		  System.out.print("11111");
		  currentpage="1";
		  int totalpage=projectService.findProject(Integer.parseInt(currentpage),5).getTotalPages();
		  List<Project> list=projectService.findProject(Integer.parseInt(currentpage), 5).getContent();
		  map.addAttribute("list",list);
		  map.addAttribute("totalpage",totalpage);
		  System.out.println("totalpage"+totalpage);
		  return "product/productList.jsp";
	  }
}
