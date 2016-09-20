package com.gzpy.project.service;

import org.springframework.data.domain.Page;
import com.gzpy.project.entity.Project;

public interface ProjectService {
  public Page findProject(int currentpage,int size);
  //public Project findProjectById(int projectId);
}
