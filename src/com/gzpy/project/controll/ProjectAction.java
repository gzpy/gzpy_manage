package com.gzpy.project.controll;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.messaging.simp.user.UserSessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzpy.common.BaseController;
import com.gzpy.project.entity.Project;
import com.gzpy.project.service.ProjectService;
import com.gzpy.util.GenerateGUID;

@Controller
public class ProjectAction extends BaseController{
	@Resource
	private ProjectService projectService;
	
	@RequestMapping("/find.do")
	  public String findProject(String pageNum,String numPerPage,ModelMap map){
		String currentPage;
		  if(pageNum==null){
		     currentPage="1";
		  }else{
			  currentPage=pageNum;
		  }
		  if(numPerPage == null || "".equals(numPerPage)){
				numPerPage = "5";
			} 
		  int totalPage=projectService.findProject(Integer.parseInt(currentPage),Integer.parseInt(numPerPage)).getTotalPages();
		  long totalCount = projectService.findProject(
					Integer.parseInt(currentPage), Integer.parseInt(numPerPage)).getTotalElements();
		  List<Project> list=projectService.findProject(Integer.parseInt(currentPage), Integer.parseInt(numPerPage)).getContent();
		  map.addAttribute("list",list);
		  map.addAttribute("totalPage",totalPage);
		  map.addAttribute("totalCount",totalCount);
		  map.addAttribute("numPerPage", numPerPage);
		  map.addAttribute("currentPage", currentPage);
		  return "project/project.jsp";
	  }
	//添加项目
	@RequestMapping("/addProject.do")
	public ModelAndView addProject(Project project,String PushDate,ModelMap map){
		String projectId=GenerateGUID.getGuid();
		Date issueDate = Date.valueOf(PushDate); 
		project.setProjectId(projectId);
	    project.setIssueDate(issueDate);
	    project.setDelStatus("N");
		Project pj=projectService.addProject(project);
		if(pj == null || "".equals(pj)){
			return this.ajaxDoneError("添加失败,请重新添加！");
		} else {
			return this.ajaxDoneSuccess("添加成功", "dlg_page1", "closeCurrent");
		}
	}
	
	//跳转至添加页面
	@RequestMapping("/goAddProject.do")
	public String goAddView(){
		return "project/addProject.jsp";
	}
	
	//跳转至修改页面
	@RequestMapping("/goEditProject.do")
	public String goEditView(String projectId,ModelMap map)
	{  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	    Project project=projectService.findProjectById(projectId);
	    String issueDate=dateFormat.format(project.getIssueDate());
		map.addAttribute("project",project);
		map.addAttribute("issueDate", issueDate);
		return "project/editProject.jsp";
	
	}
	//删除项目
	@RequestMapping("/deleteProject.do")
	public ModelAndView deleteProject(String projectId,ModelMap map){
		int i=projectService.deleteProject(projectId);
		if(i<1){
			return this.ajaxDoneError("删除失败,请重新添加！");
		} else {
			return this.ajaxDoneSuccess("删除成功");
		}
	}
	
	@RequestMapping("/editProject.do")
	public ModelAndView editProject(Project project,String PushDate,ModelMap map){
		Date issueDate = Date.valueOf(PushDate); 
		project.setIssueDate(issueDate);
		Project p=projectService.updateProject(project);
		if(p == null || "".equals(p)){
			return this.ajaxDoneError("修改失败,请重新修改！");
		} else {
			return this.ajaxDoneSuccess("修改成功", "dlg_page1", "closeCurrent");
		}
	}
	
	@RequestMapping("/projectDetail.do")
	public String projectDetail(String projectId,ModelMap map){
		Project project=projectService.findProjectById(projectId);
		map.addAttribute("project",project);
		return "project/projectDetail.jsp";
	
	}
}
