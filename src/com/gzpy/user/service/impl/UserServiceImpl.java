package com.gzpy.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzpy.product.entity.Product;
import com.gzpy.user.dao.UserDao;
import com.gzpy.user.entity.User;
import com.gzpy.user.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
public Page<User> findUserByCurrentPage(int currentPage, int pageSize) {
		
		Pageable pb = new PageRequest(currentPage - 1, pageSize,
				Sort.Direction.ASC, "userId");
		Page<User> page = userDao.findAll(pb);
		
		return page;
	}

@Override
public User saveUser(User user) {
	
	return userDao.save(user);
}

@Override
public void deleteUser(String userId) {
	// TODO Auto-generated method stub
	userDao.delete(userId);
}

@Override
public User findUserById(String userId) {
	// TODO Auto-generated method stub
	return userDao.findOne(userId);
}

}
