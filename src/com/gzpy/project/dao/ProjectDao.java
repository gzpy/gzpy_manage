package com.gzpy.project.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.gzpy.project.entity.Project;


public interface ProjectDao extends  JpaSpecificationExecutor<Project>,JpaRepository<Project, String>{
	public Project findProjectById(String projectId);
	public int deleteProject(String projectId);
	public Project updateProject(Project project);
}
