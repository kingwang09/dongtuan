package org.hejin.newapp.dao;

import java.util.List;

import org.hejin.newapp.model.open.Book;

public interface BookDao {
	public List<Book> findBooks();
}
