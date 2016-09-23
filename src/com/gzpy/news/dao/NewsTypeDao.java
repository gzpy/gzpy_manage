package com.gzpy.news.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzpy.news.entity.NewsType;

public interface NewsTypeDao extends JpaSpecificationExecutor<NewsType>,JpaRepository<NewsType, String>{
	
}
