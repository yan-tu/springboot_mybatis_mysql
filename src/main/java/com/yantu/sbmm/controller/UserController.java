package com.yantu.sbmm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yantu.sbmm.common.enums.ResultEnum;
import com.yantu.sbmm.common.json.JsonResult;
import com.yantu.sbmm.pojo.User;
import com.yantu.sbmm.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	@Qualifier("user")
	private UserService userService;
	
	@RequestMapping("/findUser")
	public JsonResult findUser(@RequestParam Map<String,String> json){
		JsonResult result = new JsonResult();
		User queryUser = new User();
		queryUser.setId(json.get("id"));
		User user = userService.findById(queryUser);
		result = user==null?new JsonResult(ResultEnum.NO_VALUE,"获取失败",null):new JsonResult(ResultEnum.SUCCESS,"获取成功",user);
		return result;
		//return user==null?"获取失败":user.getName()+","+Integer.toString(user.getAge());
	}
}
