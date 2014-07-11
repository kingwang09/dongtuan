package org.hejin.newapp.web.pages;

import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Property;
import org.hejin.newapp.model.open.Book;
import org.hejin.newapp.service.BookService;

public class BookPage {

	@Inject
	private BookService bookService;
	
	@Property
	private Book book;
	
	public List<Book> getBooks(){
		return bookService.findBooks();
	}
}
