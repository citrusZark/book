package org.bookpub;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bookpub.entity.Author;
import org.bookpub.entity.Book;
import org.bookpub.entity.Publisher;
import org.bookpub.repository.AuthorRepository;
import org.bookpub.repository.BookRepository;
import org.bookpub.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.LOWEST_PRECEDENCE - 15)
public class StartupRunner implements CommandLineRunner {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private DataSource ds;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired private AuthorRepository authorRepository;
	@Autowired private PublisherRepository publisherRepository;

	@Override
	public void run(String... args) throws Exception {
		logger.info("hello");
		logger.info("DataSource: "+ds.toString());
		
//		Author author = new Author("Alex", "Antonov");
//		author = authorRepository.save(author);
//		Publisher publisher = new Publisher("pactkpub");
//		publisher = publisherRepository.save(publisher);
//		Book book = new Book("978-1-78528-415-1", "Spring boot recipes", author, publisher);
//		book = bookRepository.save(book);
		
//		logger.info("Number of books: "+bookRepository.count());
	}
	
}
