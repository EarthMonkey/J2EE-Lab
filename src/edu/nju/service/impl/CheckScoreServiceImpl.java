package edu.nju.service.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.dao.ScoreDao;
import edu.nju.models.ScoresPO;
import edu.nju.service.CheckScoreService;

@Service
public class CheckScoreServiceImpl implements CheckScoreService {

	@Autowired
	private ScoreDao scoreDao;
	
	@Override
	public ArrayList<ScoresPO> checkScore(int id) {
		return scoreDao.find(id);
	}

	@Override
	public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = req.getRequestDispatcher(resp.encodeURL(page));
		dispatcher.forward(req, resp);
	}

}
