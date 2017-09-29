package com.yantu.sbmm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yantu.sbmm.pojo.User;
import com.yantu.sbmm.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	@Qualifier("user")
	private UserService userService;
	
	@RequestMapping("/findUser")
	public String findUser(@RequestParam Map<String,String> json){
		User queryUser = new User();
		queryUser.setId(json.get("id"));
		User user = userService.findById(queryUser);
		return user==null?"获取失败":user.getName()+","+Integer.toString(user.getAge());
	}
}
