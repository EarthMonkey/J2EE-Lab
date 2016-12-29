package edu.nju.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nju.dao.DaoHelper;
import edu.nju.dao.ScoreDao;
import edu.nju.database.DBConnection;
import edu.nju.models.ScoresPO;

public class ScoreDaoImpl implements ScoreDao {

	private static ScoreDaoImpl scoreDao = new ScoreDaoImpl();
	private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();
	
	public static ScoreDaoImpl getInstance() {
		return scoreDao;
	}
	
	@Override
	public void createData() {

		for (int i = 0; i < 30; i++) {
			Connection con = daoHelper.getConnection();
			PreparedStatement pstmt = null;
			
			String sql = "insert into ScoresTable (course_id,student_id,score) values(?,?,?)";
			try {
				pstmt = (PreparedStatement) con.prepareStatement(sql);
				pstmt.setString(1, "期中考试");
				pstmt.setInt(2, i+1);

				int score = 83 + (int)(Math.random() * 10) % 10;
				pstmt.setInt(3, score);
				pstmt.executeUpdate();

				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				daoHelper.closeConnection(con);
				daoHelper.closePreparedStatement(pstmt);
			}
		}
		
	}

	@Override
	public ArrayList<ScoresPO> find(int id) {

		ArrayList<ScoresPO> scoreslist = new ArrayList<>();
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select course_id, score from ScoresTable where student_id ='" + id + "'";
		try {
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ScoresPO sPo = new ScoresPO(rs.getString(1), rs.getInt(2));
				scoreslist.add(sPo);
			}

			con.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(pstmt);
			daoHelper.closeResult(rs);
		}
		return scoreslist;
	}
}
