package com.yantu.sbmm.zookeeper;

import java.io.IOException;

public class ServiceRegistry2 {

	
	public static void main(String[] args) {
		ServiceRegistry serviceRegistry = new ServiceRegistryImpl();
		serviceRegistry.register("product-service", "192.168.11.112:20881");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}//阻塞
	}

}
