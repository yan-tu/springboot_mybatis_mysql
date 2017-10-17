package com.yantu.sbmm.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yantu.sbmm.dao.UserDAO;
import com.yantu.sbmm.pojo.User;
import com.yantu.sbmm.service.UserService;

@Service("userRedisImpl")
public class UserServiceRedisImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
//	@Cacheable(value="user1")//缓存名称
	@Cacheable(value = "user", key = "#root.targetClass + #id", unless = "#result eq null")
	@Override
	public User findById(String id) {
		System.out.println("=====================缓存中查找失败，从数据库中获取，id="+id);
		User user = new User();
		user.setId(id);
		return userDAO.findById(user);
	}
	
//	@CacheEvict(value="user1",condition="#result eq 1")
	@CacheEvict(value="user1",key = "#root.targetClass + #user.id")
	@Override
	public User updateUser(User user) {
		System.out.println("===================从数据库中更新，从缓存中删除,id="+user.getId());
		if(userDAO.updateUser(user) > 0){
			return user;
		}
		return null;
	}

//	@CachePut(value="user1",condition="#result eq 1")
	@CachePut(value="user1",key = "#root.targetClass + #user.id")
	@Override
	public User addUser(User user) {
		System.out.println("===================新增用户到数据库,id="+user.getId());
		User oldUser = userDAO.findById(user);
		if(null != oldUser) return null;
		if(userDAO.insertUser(user) > 0){
			return user;
		}
		return null;
	}

//	@CacheEvict(value="user1",condition="#result eq 1")
	@CacheEvict(value="user1",key = "#root.targetClass + #id")
	@Override
	public boolean deleteFromCache(String id) {
		System.out.println("===================从数据库中删除，从缓存中删除,id="+id);
		return userDAO.deleteUserById(id)>0;
	}



	
}
