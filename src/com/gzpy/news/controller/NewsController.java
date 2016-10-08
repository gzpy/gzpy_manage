package com.gzpy.news.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gzpy.common.BaseController;
import com.gzpy.news.entity.News;
import com.gzpy.news.entity.NewsType;
import com.gzpy.news.service.NewsService;
import com.gzpy.news.service.NewsTypeService;
import com.gzpy.user.entity.User;
import com.gzpy.util.GenerateGUID;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsTypeService newsTypeService;

	private int currentPage;// 当前页码

	private int pageSize; // 单个页面显示数量

	@RequestMapping("/toNewsManage.do")
	public String toNewsManage(String pageNum, String numPerPage,
			String newsTitle, String delStatus, ModelMap map) {
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

		if (newsTitle == null || "".equals(newsTitle)) {
			newsTitle = "%";
		} else {
			map.addAttribute("newsTitle", newsTitle);
			newsTitle = "%" + newsTitle + "%";
		}

		if (delStatus == null) {
			delStatus = "%N%";
		} else if ("".equals(delStatus)) {
			delStatus = "%";
		} else {
			delStatus = "%" + delStatus + "%";
		}

		map.addAttribute("delStatus", delStatus);

		int totalPage = newsService.findNewsBySearch(currentPage, pageSize,
				newsTitle, delStatus).getTotalPages();
		long totalCount = newsService.findNewsBySearch(currentPage, pageSize,
				newsTitle, delStatus).getTotalElements();
		List<News> list_news = newsService.findNewsBySearch(currentPage,
				pageSize, newsTitle, delStatus).getContent();

		for (News news : list_news) {
			news.setTypeName(newsTypeService.findNewsTypeById(news.getTypeId())
					.getTypeName());
		}

		map.addAttribute("currentPage", currentPage);
		map.addAttribute("pageSize", pageSize);
		map.addAttribute("totalPage", totalPage);
		map.addAttribute("totalCount", totalCount);
		map.addAttribute("list_news", list_news);

		return "news/newsList.jsp";
	}

	@RequestMapping("/toAddNews.do")
	public String toAddNews(ModelMap map) {

		List<NewsType> list_newsType = newsTypeService.findAllNewsType();
		map.addAttribute("list_newsType", list_newsType);

		return "news/addNews.jsp";
	}

	@RequestMapping("/addNews.do")
	public ModelAndView addNews(@RequestParam MultipartFile file, News news,
			HttpServletRequest request) {

		List<String> allowTypes = new ArrayList<String>();// 设置允许上传的类型
		allowTypes.add(".jpg");
		allowTypes.add(".jepg");
		allowTypes.add(".bmp");
		allowTypes.add(".png");

		if (file != null && file.getSize() > 0) {
			String fileOriginName = file.getOriginalFilename();
			String ext = fileOriginName.substring(
					fileOriginName.lastIndexOf("."), fileOriginName.length());

			boolean flag = false;

			for (String allowType : allowTypes) {
				if (ext.equals(allowType)) {
					flag = true;
				}
			}

			if (!flag) {
				return this.ajaxDoneError("请选择图片格式文件!");
			}

			if (file.getSize() > 10485760) {
				return this.ajaxDoneError("选择文件不能超过10M");
			}

			String filePath = request.getSession().getServletContext()
					.getRealPath("/upload/news");// 设置上传路径

			File fileUpload = new File(filePath);
			if (!fileUpload.exists()) {
				fileUpload.mkdirs();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String fileName = sdf.format(new java.util.Date()) + ".jpg";

			try {
				file.transferTo(new File(filePath + "\\" + fileName));// 上传文件
				news.setImagePath("\\upload\\news\\" + fileName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		User user = (User) request.getSession().getAttribute("currentUser");
		news.setUserId(user.getUserId());
		news.setNewsId(GenerateGUID.getGuid());
		news.setIssueDate(new Date());
		news.setDelStatus("N");

		News result = newsService.saveNews(news);

		if (result != null) {
			return this.ajaxDoneSuccess("添加成功", "newsManage", "closeCurrent");
		}

		return this.ajaxDoneError("添加失败，请重新添加！");
	}

	@RequestMapping("/toUpdateNews.do")
	public String toUpdateNews(String newsId, ModelMap map) {

		List<NewsType> list_newsType = newsTypeService.findAllNewsType();
		map.addAttribute("list_newsType", list_newsType);

		News news = newsService.findNewsById(newsId);
		map.addAttribute("news", news);

		return "news/updateNews.jsp";
	}

	@RequestMapping("/updateNews.do")
	public ModelAndView updateNews(@RequestParam MultipartFile file, News news,
			HttpServletRequest request) {

		List<String> allowTypes = new ArrayList<String>();
		allowTypes.add(".jpg");
		allowTypes.add(".jepg");
		allowTypes.add(".bmp");
		allowTypes.add(".png");

		News oldNews = newsService.findNewsById(news.getNewsId());

		if (news.getIssueDate() == null || "".equals(news.getIssueDate())) {
			news.setIssueDate(oldNews.getIssueDate());
		}

		if (news.getImagePath() == null || "".equals(news.getImagePath())) {
			news.setImagePath(oldNews.getImagePath());
		}

		if (news.getIsStick() == null || "".equals(news.getIsStick())) {
			news.setIsStick(oldNews.getIsStick());
		}

		if (news.getUserId() == null || "".equals(news.getUserId())) {
			news.setUserId(oldNews.getUserId());
		}

		if (file != null && file.getSize() > 0) {

			String fileOriginName = file.getOriginalFilename();
			String ext = fileOriginName.substring(
					fileOriginName.lastIndexOf("."), fileOriginName.length());

			boolean flag = false;

			for (String allowType : allowTypes) {
				if (ext.equals(allowType)) {
					flag = true;
				}
			}

			if (!flag) {
				return this.ajaxDoneError("请选择图片格式文件!");
			}

			if (file.getSize() > 10485760) {
				return this.ajaxDoneError("选择文件不能超过10M");
			}

			String filePath = request.getSession().getServletContext()
					.getRealPath("/upload/news");

			File fileUpload = new File(filePath);
			if (!fileUpload.exists()) {
				fileUpload.mkdirs();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String fileName = sdf.format(new java.util.Date()) + ".jpg";

			try {
				file.transferTo(new File(filePath + "\\" + fileName));
				news.setImagePath("\\upload\\news\\" + fileName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (!(oldNews.getImagePath() == null || "".equals(oldNews
					.getImagePath()))) {
				File oldFile = new File(request.getSession()
						.getServletContext()
						.getRealPath(oldNews.getImagePath()));
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
		}

		News result = newsService.saveNews(news);

		if (result != null) {
			return this.ajaxDoneSuccess("修改成功", "newsManage", "closeCurrent");
		}

		return this.ajaxDoneError("修改失败，请输入正确的信息");
	}

	@RequestMapping("/deleteNews.do")
	public ModelAndView deleteNews(String newsId, HttpServletRequest request) {

		News news = newsService.findNewsById(newsId);

		if (!(news.getImagePath() == null || "".equals(news.getImagePath()))) {
			File oldFile = new File(request.getSession().getServletContext()
					.getRealPath(news.getImagePath()));
			if (oldFile.exists()) {
				oldFile.delete();
			}
		}

		try {
			newsService.deleteNews(newsId);
			return this.ajaxDoneSuccess("删除成功", "newsManage", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.ajaxDoneError("删除失败");
	}

	@RequestMapping("/toNewsDetail.do")
	public String toNewsDetail(String newsId, ModelMap map) {

		News news = newsService.findNewsById(newsId);
		map.addAttribute("news", news);

		return "news/newsDetail.jsp";
	}
}
