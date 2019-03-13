package com.yantu.sbmm.zookeeper;

import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ServiceDiscovery {
	List<String> serviceRepos = new ArrayList<>();//缓存服务端地址的集合
	
	private CuratorFramework curatorFramework = null;
	
	LoadBalance  loadBanBalance = null;
	
	private static final String REGISTRY_ROOT = "/registry";//namespace
	
	{
		loadBanBalance = new RandomLoadBalance();
		//sessionTimeoutMs表示在这段时间内没有收到心跳包表示会话超时
		curatorFramework = CuratorFrameworkFactory.builder()
				.connectString("127.0.0.1:2181").sessionTimeoutMs(5000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 0)).build();
		curatorFramework.start();
	}
	
	private void init(String serviceName) throws Exception {	
		String path = REGISTRY_ROOT+"/"+serviceName;
		try {
			serviceRepos  = curatorFramework.getChildren().forPath(path);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		registerWatcher(path);
	}
	
	private void registerWatcher(String path) throws Exception{
		PathChildrenCache pathChildrenCache =  new PathChildrenCache(curatorFramework, path, true);
		PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework ctFramework, PathChildrenCacheEvent pathChildrenCacheEvent)
					throws Exception {
				serviceRepos = ctFramework.getChildren().forPath(path);
			}
		};
		
		pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
		pathChildrenCache.start();
	}
	
	//获取终端地址(负载均衡)
	public String gerServiceEndPoint(){
		return loadBanBalance.selectHost(serviceRepos);
	}
	
	public static void main(String[] args) throws Exception {
		ServiceDiscovery serviceDiscovery = new ServiceDiscovery();
		serviceDiscovery.init("product-service");
		for (int i = 0; i < 20; i++) {
			System.out.println("发现服务:"+serviceDiscovery.gerServiceEndPoint());
			Thread.sleep(4000);
		}
	}
}
