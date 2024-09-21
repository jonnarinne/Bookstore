package harjoitus.bookstore.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import harjoitus.bookstore.domain.Book;
import harjoitus.bookstore.domain.BookRepository;
import harjoitus.bookstore.domain.CategoryRepository;


@RestController
public class RestBookController {

    private static final Logger log = LoggerFactory.getLogger(RestBookController.class);

	@Autowired
	BookRepository bookRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/books")
	public Iterable<Book> getBooks() {
		log.info("fetch books from db and return to client as json");
		return bookRepository.findAll();

	}

	@GetMapping("/book/{id}")
	public Optional<Book> getOneBook(@PathVariable("id") Long bookId) {
		log.info("fetch one book from db and return to client as json " + bookId);
		return bookRepository.findById(bookId);
	}

    @PostMapping("/book")
	Book newBook(@RequestBody Book newBook) {
		log.info("save a new book " + newBook);
		return bookRepository.save(newBook);
	}

    @PutMapping("/book/{id}")
	Book editBook(@RequestBody Book editedBook, @PathVariable Long id) {
		log.info("editBook = " + editedBook);
		log.info("edit book, id = " + id);
		editedBook.setId(id);
		log.info("editBook = " + editedBook);
		return bookRepository.save(editedBook);
	}

}



