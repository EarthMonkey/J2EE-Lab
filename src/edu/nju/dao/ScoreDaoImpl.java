package edu.nju.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import edu.nju.models.ScoresPO;

@Repository
public class ScoreDaoImpl implements ScoreDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createData(ScoresPO scoresPO) {
		sessionFactory.getCurrentSession().save(scoresPO);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public ArrayList<ScoresPO> find(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ScoresPO sp where sp.student_id=" + id;
		List<ScoresPO> list = session.createQuery(hql).list();
		
		return (ArrayList<ScoresPO>) list;
	}
}
