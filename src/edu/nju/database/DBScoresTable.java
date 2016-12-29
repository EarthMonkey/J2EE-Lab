package edu.nju.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nju.models.ScoresPO;

public class DBScoresTable {

	public static int insertData() {

		int res = 0;
		for (int i = 0; i < 30; i++) {
			Connection con = DBConnection.getConnection();
			
			String sql = "insert into ScoresTable (course_id,student_id,score) values(?,?,?)";
			PreparedStatement pstmt;
			try {
				pstmt = (PreparedStatement) con.prepareStatement(sql);
				pstmt.setString(1, "期中考试");
				pstmt.setInt(2, i+1);

				int score = 83 + (int)(Math.random() * 10) % 10;
				pstmt.setInt(3, score);
				res = pstmt.executeUpdate();

				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public static ArrayList<ScoresPO> checkScore(int studennt_id) {
		
		ArrayList<ScoresPO> scoreslist = new ArrayList<>();
		
		Connection con = DBConnection.getConnection();
		String sql = "select course_id, score from ScoresTable where student_id ='" + studennt_id + "'";
		PreparedStatement pstmt;

		try {

			pstmt = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ScoresPO sPo = new ScoresPO(rs.getString(1), rs.getInt(2));
				scoreslist.add(sPo);
			}

			con.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return scoreslist;
	}
}
