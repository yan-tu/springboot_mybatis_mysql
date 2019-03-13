package com.yantu.sbmm.zookeeper;

import java.util.List;

import org.springframework.util.CollectionUtils;

public abstract class AbstractLoadBalance implements LoadBalance{
	@Override
	public String selectHost(List<String> serviceRepos) {
		if(CollectionUtils.isEmpty(serviceRepos)){
			return null;
		}else if(serviceRepos.size() == 1){
			return serviceRepos.get(0);
		}
		return doSelect(serviceRepos);
	}

	protected abstract String doSelect(List<String> serviceRepos);
}
