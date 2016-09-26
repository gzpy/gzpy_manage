package com.gzpy.remark.service.impl;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzpy.project.entity.Project;
import com.gzpy.remark.dao.RemarkDao;
import com.gzpy.remark.entity.Remark;
import com.gzpy.remark.service.RemarkService;
@Service
@Transactional
public class RemarkServiceImpl implements RemarkService{
	@Resource
    RemarkDao remarkDao;
	//分页查询所有留言
	public Page findRemark(int currentpage, int size) {
		{
			Specification<Remark> spec = new Specification<Remark>() {
				public Predicate toPredicate(Root<Remark> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					return null;
				}
			};
			Pageable pb = new PageRequest(currentpage - 1, size,
					Sort.Direction.DESC, "remarkTime");
			Page page = remarkDao.findAll(pb);
			return page;
		}
	}
    //按id查询留言
	public Remark findRemarkById(String remarkId) {
	return 	remarkDao.findOne(remarkId);
	}

	public Remark updateRemark(Remark remark) {
		
		return remarkDao.updateRemark(remark);
	}
	//查询所有未删除状态的留言
	public Page findRemarkByDelStatus(int currentpage, int size) {
		Specification<Remark> spec = new Specification<Remark>() {
			public Predicate toPredicate(Root<Remark> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> delStatus = root.get("delStatus");
				query.where(cb.equal(delStatus, "N"));
				return query.getRestriction();   
			}
		};
		return remarkDao.findAll(spec,new PageRequest(currentpage - 1, size,
				Sort.Direction.DESC, "remarkTime"));
	}
  //留言检索
	public Page findRemarkBySearch(int currentpage, int size,final String name,final String status){
		Specification<Remark> spec = new Specification<Remark>() {
			public Predicate toPredicate(Root<Remark> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> delStatus = root.get("delStatus");
				Path<String> remarkname=root.get("name");
				//Path<String> remarkTime=root.get("remarkTime");
				Path<String> remarkstatus=root.get("status");
				 Predicate isDelStatus=cb.equal(delStatus, "N");
				 Predicate searchRemarkName=(cb.like(remarkname,"%"+name+"%"));
				// Predicate searchRemarkTime=cb.equal(remarkTime, remark.getRemarkTime());
				 Predicate searchStatus=cb.equal(remarkstatus,status);
				 if(status==""){
					 query.where(cb.and(isDelStatus,searchRemarkName));  
				 }else
				 query.where(cb.and(isDelStatus,searchRemarkName,searchStatus));  
				return query.getRestriction();  
			}
		};
		return remarkDao.findAll(spec,new PageRequest(currentpage - 1, size,
				Sort.Direction.DESC, "remarkTime"));
	}
	
}
