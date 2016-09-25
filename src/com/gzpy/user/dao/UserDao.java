package com.gzpy.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzpy.user.entity.User;

public interface UserDao extends JpaSpecificationExecutor<User>,
		JpaRepository<User, String> {
	 public User userLogin(User user);

}
