//package com.yantu.sbmm.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.yantu.sbmm.dao.UserDAO;
//import com.yantu.sbmm.pojo.User;
//import com.yantu.sbmm.service.UserService;
//
//@Service("user")
//public class UserServiceImpl implements UserService {
//	
//	@Autowired
//	private UserDAO userDAO;
//
//	@Override
//	public User findById(User user) {
//		User result = userDAO.findById(user);
//		System.out.println("获取缓存失败，调用数据库..........");
//		return result;
//	}
//
//	@Override
//	public int updateUser(User user) {
//		int result = userDAO.updateUser(user);
//		return result;
//	}
//
//}
