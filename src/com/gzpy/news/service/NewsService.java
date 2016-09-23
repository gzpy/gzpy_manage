package com.gzpy.news.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gzpy.news.entity.News;

public interface NewsService {
	
	/**
	 * 分页查找所有文章
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<News> findNewsByCurrentPage(int currentPage,int pageSize);
	
	/**
	 * 保存文章
	 * @param news
	 * @return
	 */
	public News saveNews(News news);
	
	/**
	 * 按Id查找文章
	 * @param newsId
	 * @return
	 */
	public News findNewsById(String newsId);
	
	/**
	 * 按文章类型查找
	 * @param typeId
	 * @return
	 */
	public List<News> findNewsByType(String typeId);
	
	/**
	 * 删除文章
	 * @param newsId
	 */
	public void deleteNews(String newsId);
}
