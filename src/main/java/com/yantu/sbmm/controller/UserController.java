package com.yantu.sbmm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yantu.sbmm.pojo.User;
import com.yantu.sbmm.service.UserService;

@RestController
public class UserController {
	@Autowired
	@Qualifier("user")
	private UserService userService;
	
	@RequestMapping("findUser")
	public String findUser(){
		User queryUser = new User();
		queryUser.setId("1001");
		User user = userService.findById(queryUser);
		return user.getName()+","+Integer.toString(user.getAge());
	}
}
