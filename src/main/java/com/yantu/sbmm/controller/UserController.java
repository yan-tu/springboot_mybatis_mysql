package com.yantu.sbmm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.StringUtils;
import com.yantu.sbmm.pojo.User;
import com.yantu.sbmm.service.UserService;
/**
 * 
 * @author YanTu
 * @date:2017年10月1日
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	@Qualifier("user")
	private UserService userService;
	
//	@RequestMapping("/findUser")
//	@Cacheable(value="user-key", key="#json + 'user'")
//	public JsonResult findUser(@RequestParam Map<String,String> json){
//		JsonResult result = new JsonResult();
//		User queryUser = new User();
//		queryUser.setId(json.get("id"));
//		User user = userService.findById(queryUser);
//		result = user==null?new JsonResult(ResultEnum.NO_VALUE,"获取失败",null):new JsonResult(ResultEnum.SUCCESS,"获取成功",user);
//		return result;
//		//return user==null?"获取失败":user.getName()+","+Integer.toString(user.getAge());
//	}
	
	//@Cacheable(value="user-key")
	@RequestMapping("/findUserById")
	public User findUser(@RequestParam Map<String,String> json){
		String id = StringUtils.isNullOrEmpty(json.get("id"))?null:json.get("id");
		return userService.findById(id);
		//return user==null?"获取失败":user.getName()+","+Integer.toString(user.getAge());
	}
	
	@RequestMapping("/deleteUserById")
	public void delete(@RequestParam Map<String,String> json){
		String id = StringUtils.isNullOrEmpty(json.get("id"))?null:json.get("id");
		userService.deleteFromCache(id);
	}
	
	
	@RequestMapping("/updateUser")
	@CachePut(key="user.id",value="user")
	public User updateUser(@RequestParam Map<String,String> json){
		
//		JSONObject jsonObject = JSONObject.parseObject(json);
//		jsonObject.toJavaObject(json, );
//		User queryUser = json.get("user");
//		queryUser.setId(json.get("id"));
//		User user = userService.findById(queryUser);
//		return user;
		return null;
		//return user==null?"获取失败":user.getName()+","+Integer.toString(user.getAge());
	}
	
	
	
	
	
}
