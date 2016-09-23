package com.gzpy.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzpy.news.dao.NewsTypeDao;
import com.gzpy.news.entity.NewsType;
import com.gzpy.news.service.NewsTypeService;

@Service
@Transactional
public class NewsTypeServiceImpl implements NewsTypeService{

	@Autowired
	private NewsTypeDao newsTypeDao;
	
	@Override
	public Page<NewsType> findNewsTypeByCurrentPage(int currentPage,
			int pageSize) {
		
		Pageable pb = new PageRequest(currentPage - 1, pageSize,
				Sort.Direction.ASC, "typeId");
		Page<NewsType> page = newsTypeDao.findAll(pb);
		
		return page;
	}

	@Override
	public NewsType saveNewsType(NewsType newsType) {
		
		return newsTypeDao.save(newsType);
	}

	@Override
	public NewsType findNewsTypeById(String typeId) {
		
		return newsTypeDao.findOne(typeId);
	}

	@Override
	public void deleteNewsType(String typeId) {
		newsTypeDao.delete(typeId);
	}

	@Override
	public List<NewsType> findAllNewsType() {
		return newsTypeDao.findAll();
	}
	
}
