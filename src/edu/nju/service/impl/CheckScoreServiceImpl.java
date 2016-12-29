package edu.nju.service.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.factory.DaoFactory;
import edu.nju.models.ScoresPO;
import edu.nju.service.CheckScoreService;

public class CheckScoreServiceImpl implements CheckScoreService {

	private static CheckScoreService scoreService = new CheckScoreServiceImpl();

	public static CheckScoreService getInstance() {
		return scoreService;
	}
	
	
	@Override
	public ArrayList<ScoresPO> getScore(int id) {
		return DaoFactory.getScoreDao().find(id);
	}

	@Override
	public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
