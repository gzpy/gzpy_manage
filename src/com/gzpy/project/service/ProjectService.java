package com.gzpy.project.service;

import org.springframework.data.domain.Page;

import com.gzpy.project.entity.Project;

public interface ProjectService {
	public Page findProject(int currentpage, int size);

	public Project findProjectById(String projectId);
	public Project addProject(Project project);
	public int deleteProject(String projectId);
	public Project updateProject(Project project);
	 public Page findProjectBySearch(int currentpage,int size,final String projectTitle);
}
