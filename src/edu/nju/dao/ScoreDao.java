package edu.nju.dao;

import java.util.ArrayList;

import edu.nju.models.ScoresPO;

public interface ScoreDao {
	
	public void createData();

	public ArrayList<ScoresPO> find(String id);
	
}
