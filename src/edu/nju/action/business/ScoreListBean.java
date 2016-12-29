package edu.nju.action.business;

import java.io.Serializable;
import java.util.ArrayList;

import edu.nju.models.ScoresPO;

public class ScoreListBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<ScoresPO> scoreList = new ArrayList<>();
	private ArrayList<ScoresPO> absentList = new ArrayList<>();
	
	public ArrayList<ScoresPO> getScoreList() {
		return scoreList;
	}
	
	public void setScoreList(ArrayList<ScoresPO> scoreList) {
		this.scoreList = scoreList;
	}
	
	public ArrayList<ScoresPO> getAbsentList() {
		return absentList;
	}
	
	public void setAbsentList(ArrayList<ScoresPO> scoreList) {
		this.absentList = scoreList;
	}
}
