package com.yantu.sbmm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SbmmApplication.class)
public class TestRedis {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Test
	public void test() throws Exception{
		stringRedisTemplate.opsForValue().set("aaa", "111");
		System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
		//Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}
	
}
