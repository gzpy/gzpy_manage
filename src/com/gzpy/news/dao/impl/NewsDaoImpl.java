package com.gzpy.news.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.gzpy.news.entity.News;

public class NewsDaoImpl{
	
	@Autowired
	private EntityManager em;

	public List<News> findNewsByType(String typeId){
		
		StringBuffer sb = new StringBuffer();
		sb.append("select n from News n where n.typeId=:typeId");
		
		Query query = this.em.createQuery(sb.toString());
		query.setParameter("typeId", typeId);
		
		return query.getResultList();
	}
}
