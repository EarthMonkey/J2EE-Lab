package edu.nju.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ScoresTable")
public class ScoresPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private int student_id;
	@Id
	private String course_id;
	
	private int score;
	
	public ScoresPO() {
		this.course_id = "";
		this.score = -1;
	}

	public ScoresPO(String course, int score) {
		super();
		this.course_id = course;
		this.score = score;
	}
	
	public ScoresPO(int student_id, String course_id, int score) {
		this.student_id = student_id;
		this.course_id = course_id;
		this.score = score;
	}
	
	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
