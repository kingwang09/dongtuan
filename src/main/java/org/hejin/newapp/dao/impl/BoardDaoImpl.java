package org.hejin.newapp.dao.impl;

import java.util.Collection;

import org.hejin.newapp.dao.BoardDao;
import org.hejin.newapp.model.Board;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("BoardDao")
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public Collection<Board> getBoards() {
		// TODO Auto-generated method stub
		Session s = sessionFactory.getCurrentSession();
		Criteria c = s.createCriteria(Board.class);
		return c.list();
	}

}
