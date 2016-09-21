package com.gzpy.project.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.gzpy.project.dao.ProjectDao;
import com.gzpy.project.entity.Project;
import com.gzpy.project.service.ProjectService;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    @Resource
	ProjectDao projectDao;

	public Page findProject(int currentpage, int size) {
		Pageable pb = new PageRequest(currentpage - 1, size,
				Sort.Direction.ASC, "projectId");
		Page page = projectDao.findAll(pb);
		return page;
	}

}
