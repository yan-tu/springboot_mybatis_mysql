package com.yantu.sbmm.zookeeper;

import java.io.IOException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class ServiceRegistryImpl implements ServiceRegistry {

	private CuratorFramework curatorFramework = null;

	private static final String REGISTRY_ROOT = "/registry";//namespace

	{
		//sessionTimeoutMs表示在这段时间内没有收到心跳包表示会话超时
		curatorFramework = CuratorFrameworkFactory.builder()
				.connectString("127.0.0.1:2181").sessionTimeoutMs(5000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 0)).build();
		curatorFramework.start();
	}

	@Override
	public void register(String serviceName, String serviceAdd) {
		String servicePath = REGISTRY_ROOT+"/"+serviceName;
		//判断节点（product-service）是否存在，不存在则创建
		try {
			if(curatorFramework.checkExists().forPath(servicePath) == null){
				curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
				forPath(servicePath,"0".getBytes());
			}

			//创建协议地址
			String addressPath =  "/"+serviceAdd;
			curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath);
			System.out.println("节点注册成功："+addressPath);
			// 临时节点和持久化节点区别，临时节点在失效之后会被删除
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ServiceRegistry serviceRegistry = new ServiceRegistryImpl();
		serviceRegistry.register("product-service", "192.168.11.111:20880");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}//阻塞
	}

}
