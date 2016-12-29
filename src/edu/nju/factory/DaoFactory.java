package edu.nju.factory;

import edu.nju.dao.ScoreDao;
import edu.nju.dao.impl.ScoreDaoImpl;

public class DaoFactory {

	public static ScoreDao getScoreDao() {
		return ScoreDaoImpl.getInstance();
	}

}
