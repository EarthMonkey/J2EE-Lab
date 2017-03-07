package edu.nju.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;

import edu.nju.models.ScoresPO;

@SuppressWarnings("deprecation")
public class ScoreDaoImpl implements ScoreDao {

	private Configuration config;
	private ServiceRegistry serviceRegistry;
	private SessionFactory sessionFactory;
	private Session session;
	private static ScoreDaoImpl scoreDao = new ScoreDaoImpl();

	public static ScoreDaoImpl getInstance() {
		return scoreDao;
	}

	@Override
	public void createData() {

		for (int i = 0; i < 30; i++) {
			try {
				config = new Configuration().configure();
				config.addAnnotatedClass(ScoresPO.class);
				serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
				sessionFactory = config.buildSessionFactory(serviceRegistry);
				session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				// session.save(scorePO);
				tx.commit();
				session.close();
				sessionFactory.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public ArrayList<ScoresPO> find(int id) {
		List list = null;
		try {
			config = new Configuration().configure();
			config.addAnnotatedClass(ScoresPO.class);
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(ScoresPO.class);
			list = criteria.list();
			criteria.add(Expression.eq("student_id", id));
			list = criteria.list();

			tx.commit();
			session.close();
			sessionFactory.close();
		} catch (HibernateException e) {
			System.out.println("sessionFactory 创建失败！");
			e.printStackTrace();
		}

		return (ArrayList<ScoresPO>) list;
	}
}
