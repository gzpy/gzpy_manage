package com.gzpy.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzpy.news.dao.NewsDao;
import com.gzpy.news.entity.News;
import com.gzpy.news.service.NewsService;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsDao;
	
	@Override
	public Page<News> findNewsByCurrentPage(int currentPage, int pageSize) {

		Pageable pb = new PageRequest(currentPage - 1, pageSize,
				Sort.Direction.ASC, "newsId");
		Page<News> page = newsDao.findAll(pb);
		
		return page;
	}

	@Override
	public News saveNews(News news) {
		
		return newsDao.save(news);
	}

	@Override
	public News findNewsById(String newsId) {

		return newsDao.findOne(newsId);
	}

	@Override
	public void deleteNews(String newsId) {
		
		newsDao.delete(newsId);
	}

	@Override
	public List<News> findNewsByType(String typeId) {
		
		return newsDao.findNewsByType(typeId);
	}

}
