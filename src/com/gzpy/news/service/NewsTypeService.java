package com.gzpy.news.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gzpy.news.entity.NewsType;

public interface NewsTypeService {
	/**
	 * 分页查找所有文章类型
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<NewsType> findNewsTypeByCurrentPage(int currentPage,int pageSize);
	
	/**
	 * 按关键字查找文章类型 ，分页
	 * @param currentPage
	 * @param pageSize
	 * @param tName
	 * @param dStatus
	 * @return
	 */
	public Page<NewsType> findNewsTypeBySearch(int currentPage,int pageSize,String tName,String dStatus);
	/**
	 * 保存文章类型
	 * @param newsType
	 * @return
	 */
	public NewsType saveNewsType(NewsType newsType);
	
	/**
	 * 按ID查找文章类型
	 * @param typeId
	 * @return
	 */
	public NewsType findNewsTypeById(String typeId);
	
	/**
	 * 按ID删除文章类型
	 * @param typeId
	 */
	public void deleteNewsType(String typeId);
	
	/**
	 * 查询所有文章类型，不分页
	 * @return
	 */
	public List<NewsType> findAllNewsType();
}
