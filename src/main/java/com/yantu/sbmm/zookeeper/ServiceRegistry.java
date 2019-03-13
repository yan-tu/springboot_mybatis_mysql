package com.yantu.sbmm.zookeeper;

public interface ServiceRegistry {
	/**
	 * 把服务地址注册到zookeeper上
	 * @author:YanTu
	 * @param serviceName
	 * @param serviceAdd
	 * @date:2018年8月29日
	 */
	void register(String serviceName,String serviceAdd);
}
