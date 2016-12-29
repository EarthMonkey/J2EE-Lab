package edu.nju.models;

public class ScoresPO {

	private String course;
	private int score;

	public ScoresPO() {
		this.course = "";
		this.score = -1;
	}

	public ScoresPO(String course, int score) {
		super();
		this.course = course;
		this.score = score;
	}

	public String getCourse() {
		return course;
	}

	public int getScore() {
		return score;
	}

}
