package com.yantu.sbmm.zookeeper;

import java.util.List;

public interface LoadBalance {
	String selectHost(List<String> serviceRepos);
}
