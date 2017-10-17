package com.yantu.sbmm.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yantu.sbmm.dao.UserDAO;
import com.yantu.sbmm.pojo.User;
import com.yantu.sbmm.service.UserService;

@Service("userTemplateImpl")
public class UserServiceRedisTemplateImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	@Cacheable(value="user1")//缓存名称
	@Override
	public User findById(String id) {
		System.out.println("=====================缓存中查找失败，从数据库中获取，id="+id);
		User user = new User();
		user.setId(id);
		return userDAO.findById(user);
	}
	
	

	@CacheEvict(value="user1")
	@Override
	public int updateUser(User user) {
		System.out.println("===================从数据库中更新，从缓存中删除,id="+user.getId());
		return userDAO.updateUser(user);
	}


	@CacheEvict(value="user1")
	@Override
	public void deleteFromCache(String id) {
		System.out.println("===================从数据库中删除，从缓存中删除,id="+id);
	}
	
	
}
