package com.gzpy.user.service;

import org.springframework.data.domain.Page;

import com.gzpy.user.entity.User;

public interface UserService {
	/**
	 * 分页查找所有用户
	 * @param currentPage
	 * @param pageSize
	 * @param inputName 
	 * @param delStatus 
	 * @return
	 */
	public Page<User> findUserByCurrentPage(int currentPage, int pageSize, String delStatus, String inputName);
	//添加用户
	public User saveUser(User user);
	
	//删除用户
	public void deleteUser(String userId);
	
	public User findUserById(String userId);
	
	public Page<User> findUserByName(final String inputName, int currentPage,
			int pageSize);

}
