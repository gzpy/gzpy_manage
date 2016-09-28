package com.gzpy.project.controll;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.messaging.simp.user.UserSessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gzpy.common.BaseController;
import com.gzpy.project.entity.Project;
import com.gzpy.project.service.ProjectService;
import com.gzpy.util.GenerateGUID;

@Controller
public class ProjectAction extends BaseController {
	@Resource
	private ProjectService projectService;

	@RequestMapping("/find.do")
	public String findProject(String pageNum, String numPerPage, ModelMap map) {
		String currentPage;
		if (pageNum == null) {
			currentPage = "1";
		} else {
			currentPage = pageNum;
		}
		if (numPerPage == null || "".equals(numPerPage)) {
			numPerPage = "5";
		}
		int totalPage = projectService.findProject(
				Integer.parseInt(currentPage), Integer.parseInt(numPerPage))
				.getTotalPages();
		long totalCount = projectService.findProject(
				Integer.parseInt(currentPage), Integer.parseInt(numPerPage))
				.getTotalElements();
		List<Project> list = projectService.findProject(
				Integer.parseInt(currentPage), Integer.parseInt(numPerPage))
				.getContent();
		map.addAttribute("list", list);
		map.addAttribute("totalPage", totalPage);
		map.addAttribute("totalCount", totalCount);
		map.addAttribute("numPerPage", numPerPage);
		map.addAttribute("currentPage", currentPage);
		return "project/project.jsp";
	}

	// 添加项目
	@RequestMapping("/addProject.do")
	public ModelAndView addProject(Project project, String PushDate,
			ModelMap map) {
		String projectId = GenerateGUID.getGuid();
		Date issueDate = Date.valueOf(PushDate);
		project.setProjectId(projectId);
		project.setIssueDate(issueDate);
		project.setDelStatus("N");
		Project pj = projectService.addProject(project);
		if (pj == null || "".equals(pj)) {
			return this.ajaxDoneError("添加失败,请重新添加！");
		} else {
			return this.ajaxDoneSuccess("添加成功", "dlg_page1", "closeCurrent");
		}
	}

	// 跳转至添加页面
	@RequestMapping("/goAddProject.do")
	public String goAddView() {
		// return "project/addProject.jsp";
		return "project/addProject.jsp";
	}

	// 跳转至修改页面
	@RequestMapping("/goEditProject.do")
	public String goEditView(String projectId, ModelMap map) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Project project = projectService.findProjectById(projectId);
		String issueDate = dateFormat.format(project.getIssueDate());
		if (project.getImagePath() != null) {
			List<String> list = Arrays
					.asList(project.getImagePath().split(","));
			map.addAttribute("imageList", list);
		}
		map.addAttribute("project", project);
		map.addAttribute("issueDate", issueDate);
		return "project/editProject.jsp";

	}

	// 删除项目
	@RequestMapping("/deleteProject.do")
	public ModelAndView deleteProject(String projectId, ModelMap map) {
		Project project;
		project = projectService.findProjectById(projectId);
		project.setDelStatus("Y");
		project = projectService.updateProject(project);
		if (project == null || "".equals(project)) {
			return this.ajaxDoneError("删除失败,请重新添加！");
		} else {
			return this.ajaxDoneSuccess("删除成功");
		}
	}

	@RequestMapping("/editProject.do")
	public ModelAndView editProject(@RequestParam("file") CommonsMultipartFile files[],
			Project project, String PushDate, HttpServletRequest request,
			ModelMap model) {
		ServletContext sc = request.getSession().getServletContext();
		String oldImagePath=projectService.findProjectById(project.getProjectId()).getImagePath();
		List<String> list;
		if (oldImagePath!= null) {
			list = Arrays.asList(oldImagePath.split(","));
		    for(String path:list){
		    	//得到文件的绝对路径
		    	String oldPath=sc.getRealPath("/") +path;
		    	System.out.println(oldPath+"111111111111111111111111111");
		    	File f=new File(oldPath);
		    	if(f.exists())
		    	    f.delete();
		    }
		}
		Date issueDate = Date.valueOf(PushDate);
		project.setIssueDate(issueDate);
		project.setDelStatus("N");
		String path=fileupload(files, request);
		project.setImagePath(path);
		Project p = projectService.updateProject(project);
		if (p == null || "".equals(p)) {
			return this.ajaxDoneError("修改失败,请重新修改！");
		} else {
			return this.ajaxDoneSuccess("修改成功", "dlg_page1", "closeCurrent");
		}
	}

	@RequestMapping("/projectDetail.do")
	public String projectDetail(String projectId, ModelMap map) {
		Project project = projectService.findProjectById(projectId);
		map.addAttribute("project", project);
		// 转变为集合
		List<String> list;
		if (project.getImagePath() != null) {
			list = Arrays.asList(project.getImagePath().split(","));
			map.addAttribute("imageList", list);
		}
		return "project/projectDetail.jsp";

	}

	// 检索
	@RequestMapping("/searchProject.do")
	public String searchProject(String pageNum, String numPerPage,
			String projectTitle, ModelMap map) {
		System.out.println(projectTitle);
		String currentPage;
		if (pageNum == null) {
			currentPage = "1";
		} else {
			currentPage = pageNum;
		}
		if (numPerPage == null || "".equals(numPerPage)) {
			numPerPage = "5";
		}
		int totalPage = projectService.findProjectBySearch(
				Integer.parseInt(currentPage), Integer.parseInt(numPerPage),
				projectTitle).getTotalPages();
		long totalCount = projectService.findProjectBySearch(
				Integer.parseInt(currentPage), Integer.parseInt(numPerPage),
				projectTitle).getTotalElements();
		List<Project> list = projectService.findProjectBySearch(
				Integer.parseInt(currentPage), Integer.parseInt(numPerPage),
				projectTitle).getContent();
		map.addAttribute("list", list);
		map.addAttribute("totalPage", totalPage);
		map.addAttribute("totalCount", totalCount);
		map.addAttribute("numPerPage", numPerPage);
		map.addAttribute("currentPage", currentPage);
		map.addAttribute("projectTitle", projectTitle);
		return "project/project.jsp";
	}
  //批量删除
	@RequestMapping("/deleteAll.do")
	public ModelAndView deleteAll(HttpServletRequest request) {
		String id[] = request.getParameterValues("ids");
		Project project = null;
		for (String projectId : id) {
			project = projectService.findProjectById(projectId);
			project.setDelStatus("Y");
			project = projectService.updateProject(project);
		}

		if (project == null || "".equals(project)) {
			return this.ajaxDoneError("删除失败,请重新添加！");
		} else {
			return this.ajaxDoneSuccess("删除成功");
		}
	}

	// 多文件添加上传
	@RequestMapping("/addProjectPhoto.do")
	public ModelAndView manayFileUpload(
			@RequestParam("file") CommonsMultipartFile files[],
			Project project, String PushDate, HttpServletRequest request,
			ModelMap model) {
		String projectId = GenerateGUID.getGuid();
		Date issueDate = Date.valueOf(PushDate);
		project.setProjectId(projectId);
		project.setIssueDate(issueDate);
		project.setDelStatus("N");
		//获取保存地址字符串
		String s=fileupload(files,request);
		System.out.println("s"+"0000000000"+s.equals(""));
		if(s.equals("")){
			s=null;
			project.setImagePath(s);
		}else
		    project.setImagePath(s);
		Project pj = projectService.addProject(project);
	
		if (pj == null || "".equals(pj)) {
			return this.ajaxDoneError("添加失败,请重新添加！");
		} else {
			return this.ajaxDoneSuccess("添加成功", "dlg_page1", "closeCurrent");
		}
	}
	
	
	//上传文件
	public String fileupload(CommonsMultipartFile files[],HttpServletRequest request){
		ServletContext sc = request.getSession().getServletContext();
		// 上传位置
		String path = sc.getRealPath("/upload/project") + "/"; // 设定文件保存的目录	
		List<String> list = new ArrayList<String>();
		File f = new File(path);
		if(!f.exists()){
			f.mkdirs();
		}
	    String testFileName = files[0].getOriginalFilename();
    //上传文件为空不执行for循环
	   if (!testFileName.equals("")){
		  for (int i = 0; i < files.length; i++) {
			  // 获得原始文件名
			  String fileName = files[i].getOriginalFilename();
			  // 取得文件类型
			  String suffixname = fileName
					.substring(fileName.lastIndexOf(".")).trim()
					.toLowerCase();
			// 新文件名
			  String newFileName = UUID.randomUUID() + suffixname;
			        if (!files[i].isEmpty()) {
			         	try {FileOutputStream fos = new FileOutputStream(path
							    + newFileName);
					            InputStream in = files[i].getInputStream();
					            int b = 0;
					            while ((b = in.read()) != -1) {
						        fos.write(b);
					        }
					     fos.close();
					     in.close();
				      } catch (Exception e) {
					     e.printStackTrace();
				      }
			       }
			    System.out.println("上传图片到:" + path + newFileName);
			    // 数据库中保存的相对路径
			    String relativepath = "/upload/project/" + newFileName;
		      	System.out.println(relativepath);
			    list.add(relativepath);
		  }
		}
		StringBuffer sb = new StringBuffer();
		for (String l : list) {
			sb.append(l + ",");
		}
		String imagePaths=sb.toString();
		 return imagePaths;
	  }
	
}
