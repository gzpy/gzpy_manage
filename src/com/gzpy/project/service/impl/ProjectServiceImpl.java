package com.gzpy.project.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.gzpy.project.dao.ProjectDao;
import com.gzpy.project.entity.Project;
import com.gzpy.project.service.ProjectService;
import com.gzpy.remark.entity.Remark;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Resource
	ProjectDao projectDao;

	// 分页查询
	/*public Page findProject(int currentpage, int size) {
		Specification<Project> spec = new Specification<Project>() {
			public Predicate toPredicate(Root<Project> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return null;
			}
		};
		Pageable pb = new PageRequest(currentpage - 1, size,
				Sort.Direction.DESC, "issueDate");
		Page page = projectDao.findAll(pb);
		return page;
	}*/
//查询状态为N的项目
    public Page findProject(int currentpage, int size) {
	Specification<Project> spec = new Specification<Project>() {
		public Predicate toPredicate(Root<Project> root,
				CriteriaQuery<?> query, CriteriaBuilder cb) {
			   Path<String> delStatus=root.get("delStatus");
			   Predicate isdelStatus=cb.equal(delStatus,"N");
			   query.where(cb.equal(delStatus,"N"));  
				return query.getRestriction();  
		}
	};
	Pageable pb = new PageRequest(currentpage - 1, size,
			Sort.Direction.DESC, "issueDate");
	Page page = projectDao.findAll(spec,pb);
	return page;
	}
	
	// 通过id查询项目
	public Project findProjectById(String projectId) {
		return projectDao.findProjectById(projectId);
	}
	
	
	//添加项目
	public Project  addProject(Project project){
	  	return projectDao.save(project);
	}
    //删除项目
    public int deleteProject(String projectId){
    	return projectDao.deleteProject(projectId);
    }
    //修改项目
    public Project updateProject(Project project){
    	
    	return projectDao.updateProject(project);
    }

    public Page findProjectBySearch(int currentpage,int size,final String projectTitle){
    	Specification<Project> spec = new Specification<Project>() {
			public Predicate toPredicate(Root<Project> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> title=root.get("projectTitle");
				Path<String> delStatus=root.get("delStatus");
				 Predicate searchTitle=cb.like(title, "%"+projectTitle+"%");
				 Predicate isdelStatus=cb.equal(delStatus,"N");
				 query.where(cb.and(searchTitle,isdelStatus));  
				return query.getRestriction();  
			}
		};
		return projectDao.findAll(spec,new PageRequest(currentpage - 1, size,
				Sort.Direction.DESC, "issueDate"));
    	
    }
}
