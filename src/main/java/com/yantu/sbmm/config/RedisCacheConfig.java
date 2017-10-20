package com.yantu.sbmm.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration//SpringBoot注解，启动时会注入
@EnableCaching //启用缓存
public class RedisCacheConfig extends CachingConfigurerSupport{
	
	/**
	 * 缓存管理器
	 * @param redisTemplate
	 * @return
	 */
	@Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        return rcm;
    }
    
    /**
     * redis模板操作类
     * @param factory 由springboot注入
     * @return
     */
	@Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
    	RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
    	redisTemplate.setConnectionFactory(factory);
    	//key序列化方式，在定义了key生成策略之后再加以下代码
//    	RedisSerializer<String> redisSerializer = new StringRedisSerializer();
//    	redisTemplate.setKeySerializer(redisSerializer);
//    	redisTemplate.setHashKeySerializer(redisSerializer);
    	return redisTemplate;
    }
	
	/**
	 * 自定义缓存key生成器
	 * key生成规则：类名+方法名+所有参数值生成唯一的key
	 */
//	@Override
//	public KeyGenerator keyGenerator() {
//		System.out.println("创建自定义缓存key...");
//		return new KeyGenerator(){
//			@Override
//			public Object generate(Object obj, Method med, Object... objs) {
//				StringBuilder sb = new StringBuilder();
//				sb.append(obj.getClass().getName());//类名
////				sb.append(med.getName());//方法名
////				for (Object o : objs) {//参数值
////                   sb.append(o.toString());
////				}
//				// System.out.println("自定义缓存key="+sb.toString());
//				return sb.toString();
//			}
//			
//		};
//	}
}
