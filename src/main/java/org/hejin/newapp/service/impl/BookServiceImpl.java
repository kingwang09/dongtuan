package org.hejin.newapp.service.impl;

import java.util.List;

import org.hejin.newapp.dao.BookDao;
import org.hejin.newapp.model.open.Book;
import org.hejin.newapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BookService")
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookDao bookDao;
	
	public List<Book> findBooks() {
		// TODO Auto-generated method stub
		return bookDao.findBooks();
	}

}
