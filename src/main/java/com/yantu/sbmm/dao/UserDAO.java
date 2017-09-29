package com.yantu.sbmm.dao;

import org.springframework.stereotype.Repository;

import com.yantu.sbmm.pojo.User;

@Repository
public interface UserDAO {
	User findById(User user);
}
