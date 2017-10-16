package com.yantu.sbmm.service;

import com.yantu.sbmm.pojo.User;

public interface UserService {
	
	User findById(String id);
	
	void deleteFromCache(String id);
	
	int updateUser(User user);
}
