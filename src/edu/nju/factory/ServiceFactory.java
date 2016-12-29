package edu.nju.factory;

import edu.nju.service.CheckScoreService;
import edu.nju.service.impl.CheckScoreServiceImpl;

public class ServiceFactory {
	
	public static CheckScoreService getScoreService() {
		return CheckScoreServiceImpl.getInstance();
	}
}
