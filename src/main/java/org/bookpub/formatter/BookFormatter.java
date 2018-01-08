package org.bookpub.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.bookpub.entity.Book;
import org.bookpub.repository.BookRepository;
import org.springframework.format.Formatter;

public class BookFormatter implements Formatter<Book> {

	private BookRepository repository;
	
	public BookFormatter(BookRepository repository) {
		this.repository = repository;
	}

	@Override
	public String print(Book book, Locale locale) {
		return book.getIsbn();
	}

	@Override
	public Book parse(String bookIdentifier, Locale locale) throws ParseException {
		Book book = repository.findBookByIsbn(bookIdentifier);
		return book != null ? book : repository.findOne(Long.valueOf(bookIdentifier));
	}

}
