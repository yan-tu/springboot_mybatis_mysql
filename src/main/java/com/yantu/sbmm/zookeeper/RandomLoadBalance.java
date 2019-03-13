package com.yantu.sbmm.zookeeper;

import java.util.List;
import java.util.Random;

public class RandomLoadBalance extends AbstractLoadBalance{

	@Override
	protected String doSelect(List<String> serviceRepos) {
		int length = serviceRepos.size();
		Random random =  new Random();
		return serviceRepos.get(random.nextInt(length));
	}

}
