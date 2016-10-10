package com.gzpy.news.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gzpy.common.BaseController;
import com.gzpy.news.entity.News;
import com.gzpy.news.entity.NewsType;
import com.gzpy.news.service.NewsService;
import com.gzpy.news.service.NewsTypeService;
import com.gzpy.util.GenerateGUID;

/**
 * 文章分类管理
 * 
 * @author 品韵科技
 *
 */
@Controller
@RequestMapping("/newsType")
public class NewsTypeController extends BaseController {

	@Autowired
	private NewsTypeService newsTypeService;

	@Autowired
	private NewsService newsService;

	private int currentPage;// 当前页码

	private int pageSize;// 单个页面显示数量

	@RequestMapping("/toNewsTypeManage.do")
	public String toNewsTypeManage(String pageNum, String numPerPage,
			String typeName, String delStatus, ModelMap map) {

		if (pageNum == null || "".equals(pageNum)) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(pageNum);
		}

		if (numPerPage == null || "".equals(numPerPage)) {
			pageSize = 20;
		} else {
			pageSize = Integer.parseInt(numPerPage);
		}

		if (typeName == null || "".equals(typeName)) {
			typeName = "%";
		} else {
			map.addAttribute("typeName", typeName);
			typeName = "%" + typeName + "%";
		}

		if (delStatus == null) {
			delStatus = "%N%";
		} else if ("".equals(delStatus)) {
			delStatus = "%";
		} else {
			delStatus = "%" + delStatus + "%";
		}

		map.addAttribute("delStatus", delStatus);

		int totalPage = newsTypeService.findNewsTypeBySearch(currentPage,
				pageSize, typeName, delStatus).getTotalPages();
		long totalCount = newsTypeService.findNewsTypeBySearch(currentPage,
				pageSize, typeName, delStatus).getTotalElements();
		List<NewsType> list_newsType = newsTypeService.findNewsTypeBySearch(
				currentPage, pageSize, typeName, delStatus).getContent();

		map.addAttribute("currentPage", currentPage);
		map.addAttribute("pageSize", pageSize);
		map.addAttribute("totalPage", totalPage);
		map.addAttribute("totalCount", totalCount);
		map.addAttribute("list_newsType", list_newsType);

		return "news/newsTypeList.jsp";
	}

	@RequestMapping("/toAddNewsType.do")
	public String toAddNewsType() {

		return "news/addNewsType.jsp";
	}

	/**
	 * 添加文章类型
	 * 
	 * @param newsType
	 * @return
	 */
	@RequestMapping("/addNewsType.do")
	public ModelAndView addNewsType(NewsType newsType) {

		newsType.setTypeId(GenerateGUID.getGuid());
		newsType.setDelStatus("N");

		NewsType result = newsTypeService.saveNewsType(newsType);

		if (result != null) {
			return this.ajaxDoneSuccess("添加成功", "newsTypeManage",
					"closeCurrent");
		}

		return this.ajaxDoneError("添加失败，请重新添加！");
	}

	@RequestMapping("/toUpdateNewsType.do")
	public String toUpdateNewsType(String typeId, ModelMap map) {

		NewsType newsType = newsTypeService.findNewsTypeById(typeId);
		map.addAttribute("newsType", newsType);

		return "news/updateNewsType.jsp";
	}

	/**
	 * 修改新闻类型
	 * 
	 * @param newsType
	 * @return
	 */
	@RequestMapping("/updateNewsType.do")
	public ModelAndView updateNewsType(NewsType newsType) {

		NewsType oldNewsType = newsTypeService.findNewsTypeById(newsType
				.getTypeId());

		if (newsType.getDelStatus() == null
				|| "".equals(newsType.getDelStatus())) {
			newsType.setDelStatus(oldNewsType.getDelStatus());
		}

		NewsType result = newsTypeService.saveNewsType(newsType);

		if (result != null) {
			return this.ajaxDoneSuccess("修改成功", "newsTypeManage",
					"closeCurrent");
		}

		return this.ajaxDoneError("修改失败，请输入正确的信息！");
	}

	/**
	 * 删除文章类型
	 * 
	 * @param typeId
	 * @return
	 */
	@RequestMapping("/deleteNewsType.do")
	public ModelAndView deleteNewsType(String typeId) {

		List<News> list_news = newsService.findNewsByType(typeId);

		if (list_news != null && list_news.size() > 0) {
			return this.ajaxDoneError("存在该类型文章，请勿删除！");
		}

		try {
			newsTypeService.deleteNewsType(typeId);
			return this.ajaxDoneSuccess("删除成功", "newsTypeManage", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.ajaxDoneError("删除失败");
	}

	@RequestMapping("/deleteTypes.do")
	public ModelAndView deleteTypes(HttpServletRequest request) {

		String ids[] = request.getParameterValues("ids");
		try {
			for (String id : ids) {
				List<News> list_news = newsService.findNewsByType(id);

				if (list_news != null && list_news.size() > 0) {
					return this.ajaxDoneError("存在所选类型文章，请勿删除！");
				}
			}
			for (String id : ids) {
				NewsType nt = newsTypeService.findNewsTypeById(id);
				nt.setDelStatus("Y");
				newsTypeService.saveNewsType(nt);
			}
			return this.ajaxDoneSuccess("删除成功", "newsTypeManage", "");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return this.ajaxDoneError("删除失败");
	}
}
