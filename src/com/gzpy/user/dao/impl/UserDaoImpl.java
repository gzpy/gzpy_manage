package com.gzpy.user.dao.impl;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.gzpy.user.entity.User;

public class UserDaoImpl {
	@Resource
	EntityManager em;
	@Transactional
    public User userLogin(User user){
    	String sql="from gzpy_user g where g.loginName=? and g.password=?";
    	Query q=em.createQuery(sql).setParameter(1, user.getLoginName())
    			.setParameter(2, user.getPassword());
        if(q.getResultList().isEmpty()){
        	return null;
        }
    	return (User)q.getSingleResult();
    }


}
