package com.gzpy.remark.dao.impl;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import com.gzpy.remark.entity.Remark;

public class RemarkDaoImpl {
	@Resource
	EntityManager em;
  public Remark updateRemark(Remark remark){
	  return em.merge(remark);
  }
}
