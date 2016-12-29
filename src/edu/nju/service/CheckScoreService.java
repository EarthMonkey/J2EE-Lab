package edu.nju.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.models.ScoresPO;

public interface CheckScoreService {

	public ArrayList<ScoresPO> getScore(int id);

	public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;
}
