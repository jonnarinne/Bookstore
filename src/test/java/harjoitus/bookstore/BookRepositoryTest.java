package harjoitus.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;

import harjoitus.bookstore.domain.Book;
import harjoitus.bookstore.domain.Category;
import harjoitus.bookstore.domain.BookRepository;
import harjoitus.bookstore.domain.CategoryRepository;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
	private CategoryRepository crepository; 

	@Autowired
	private BookRepository bookRepository;

    @Test
    public void createNewBook() {
    	Category category = new Category("Scifi");
    	crepository.save(category);
    	Book book = new Book("Kirja", "Kirjailija", 2024, 1111, 30);
    	bookRepository.save(book);
    	assertThat(book.getId()).isNotNull();
    }
    
    @Test
    public void deleteNewBook() {
		List<Book> books = bookRepository.findByTitle("Harry Potter");
		Book book = books.get(0);
		bookRepository.delete(book);
		List<Book> newBooks = bookRepository.findByTitle("Harry Potter");
		assertThat(newBooks).hasSize(0);
     }
    
}
