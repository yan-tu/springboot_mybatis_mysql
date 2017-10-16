package com.yantu.sbmm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yantu.sbmm.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDAOTest {
	
	@Autowired
	private UserDAO userDAO;
	
	//@Test
	public void findUserTest(){
		User testUser = new User();
		testUser.setId("1001");
		User user = userDAO.findById(testUser);
		System.out.println(user.getName());
	}

	@Test
	public void updateUserTest(){
		User testUser = new User();
		testUser.setId("1001");
		testUser.setName("唯我独尊");
		testUser.setAge(30);
		int result = userDAO.updateUser(testUser);
		System.out.println("是否完成更新："+result);
	}

}
