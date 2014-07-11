package org.hejin.newapp.dao.impl;

import java.util.List;

import org.hejin.newapp.dao.BookDao;
import org.hejin.newapp.model.open.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("BookDao")
public class BookDaoImpl implements BookDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public List<Book> findBooks() {
		// TODO Auto-generated method stub
		Session s = sessionFactory.getCurrentSession();
		Criteria crit = s.createCriteria(Book.class);
		return crit.list();
	}

}
