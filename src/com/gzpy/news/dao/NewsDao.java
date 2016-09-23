package com.gzpy.news.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzpy.news.entity.News;

public interface NewsDao extends JpaSpecificationExecutor<News>,JpaRepository<News, String>{

	public List<News> findNewsByType(String typeId);
}
