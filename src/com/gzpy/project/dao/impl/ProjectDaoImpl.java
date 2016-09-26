package com.gzpy.project.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;

import com.gzpy.project.entity.Project;

public class ProjectDaoImpl {
	
	@Resource
	private EntityManager em;
    
	public Project findProjectById(String projectId) {
		String hql = " from gzpy_projects g where g.projectId=?";
		Query q = em.createQuery(hql).setParameter(1, projectId);
		Project project= (Project) q.getSingleResult();
		return project;
	
	}
	
	public int deleteProject(String projectId){
		String sql="delete  from gzpy_projects where projectId=:projectId";
		Query q=em.createNativeQuery(sql);
		q.setParameter("projectId", projectId);
		return q.executeUpdate();
	}
	
	
	public Project updateProject(Project project){
		return em.merge(project);
	}
}
