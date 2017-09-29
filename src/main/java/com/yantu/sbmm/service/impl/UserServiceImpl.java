package com.yantu.sbmm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yantu.sbmm.dao.UserDAO;
import com.yantu.sbmm.pojo.User;
import com.yantu.sbmm.service.UserService;

@Service("user")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public User findById(User user) {
		return userDAO.findById(user);
	}

}
