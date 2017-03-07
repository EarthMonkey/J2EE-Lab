package edu.nju.factory;

import edu.nju.dao.ScoreDao;
import edu.nju.dao.ScoreDaoImpl;

public class DaoFactory {

	public static ScoreDao getScoreDao() {
		return ScoreDaoImpl.getInstance();
	}

}
