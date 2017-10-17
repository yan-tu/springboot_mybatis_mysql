package com.yantu.sbmm.service;

import com.yantu.sbmm.pojo.User;

public interface UserService {
	
	User findById(String id);
	
	boolean deleteFromCache(String id);
	
	User updateUser(User user);

	User addUser(User user);
}
