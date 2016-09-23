package com.gzpy.news.controller;

import java.util.Date;
import java.util.List;

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

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController{
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private NewsTypeService newsTypeService;
	
	private int currentPage;//当前页码
	
	private int pageSize;	//单个页面显示数量
	
	@RequestMapping("/toNewsManage.do")
	public String toNewsManage(String pageNum,String numPerPage,ModelMap map){
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
		
		int totalPage = newsService.findNewsByCurrentPage(currentPage, pageSize).getTotalPages();
		long totalCount = newsService.findNewsByCurrentPage(currentPage,pageSize).getTotalElements();
		List<News> list_news = newsService.findNewsByCurrentPage(currentPage,pageSize).getContent();
		
		for(News news : list_news){
			news.setTypeName(newsTypeService.findNewsTypeById(news.getTypeId()).getTypeName());
		}
		
		map.addAttribute("currentPage", currentPage);
		map.addAttribute("pageSize", pageSize);
		map.addAttribute("totalPage", totalPage);
		map.addAttribute("totalCount", totalCount);
		map.addAttribute("list_news",list_news);
		
		return "news/newsList.jsp";
	}
	
	@RequestMapping("/toAddNews.do")
	public String toAddNews(ModelMap map){
		
		List<NewsType> list_newsType = newsTypeService.findAllNewsType();		
		map.addAttribute("list_newsType", list_newsType);
		
		return "news/addNews.jsp";
	}
	
	@RequestMapping("/addNews.do")
	public ModelAndView addNews(News news){
		
		news.setNewsId(GenerateGUID.getGuid());
		news.setIssueDate(new Date());
		news.setDelStatus("N");
		
		News result = newsService.saveNews(news);
		
		if(result != null){
			return this.ajaxDoneSuccess("添加成功", "newsManage", "closeCurrent");
		}
		
		return this.ajaxDoneError("添加失败，请重新添加！");
	}
	
	@RequestMapping("/toUpdateNews.do")
	public String toUpdateNews(String newsId,ModelMap map){
		
		List<NewsType> list_newsType = newsTypeService.findAllNewsType();	
		map.addAttribute("list_newsType", list_newsType);
		
		News news = newsService.findNewsById(newsId);		
		map.addAttribute("news", news);
		
		return "news/updateNews.jsp";
	}
	
	@RequestMapping("/updateNews.do")
	public ModelAndView updateNews(News news){
		
		News oldNews = newsService.findNewsById(news.getNewsId());
		
		if(news.getIssueDate() == null || "".equals(news.getIssueDate())){
			news.setIssueDate(oldNews.getIssueDate());
		}
		
		if(news.getImagePath() == null || "".equals(news.getImagePath())){
			news.setImagePath(oldNews.getImagePath());
		}
		
		if(news.getIsStick() == null || "".equals(news.getIsStick())){
			news.setIsStick(oldNews.getIsStick());
		}
		
		if(news.getUserId() == null || "".equals(news.getUserId())){
			news.setUserId(oldNews.getUserId());
		}
		
		if(news.getDelStatus() == null || "".equals(news.getDelStatus())){
			news.setDelStatus(oldNews.getDelStatus());
		}
		
		News result = newsService.saveNews(news);
		
		if(result != null){
			return this.ajaxDoneSuccess("修改成功", "newsManage", "closeCurrent");
		}
		
		return this.ajaxDoneError("修改失败，请输入正确的信息");
	}
	
	@RequestMapping("/deleteNews.do")
	public ModelAndView deleteNews(String newsId){
		
		try {
			newsService.deleteNews(newsId);
			return this.ajaxDoneSuccess("删除成功", "newsManage", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.ajaxDoneError("删除失败");
	}
	
	@RequestMapping("/toNewsDetail.do")
	public String toNewsDetail(String newsId,ModelMap map){
		
		News news = newsService.findNewsById(newsId);		
		map.addAttribute("news", news);
		
		return "news/newsDetail.jsp";
	}
}
