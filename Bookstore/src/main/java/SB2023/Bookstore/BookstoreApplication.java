package SB2023.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import SB2023.Bookstore.domain.Book;
import SB2023.Bookstore.domain.BookRepository;


@SpringBootApplication
public class BookstoreApplication {
		
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);	
	public static void main (String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstore(BookRepository bookRepository) {
		return (args) -> {
			
			log.info("create books");
			// public Book(String title, String author, int publishingYear, int price, int ranking)
			bookRepository.save(new Book("Seitsemän veljestä", "Aleksis Kivi", 1870, 20, 1));
			bookRepository.save(new Book("Tuntematon sotilas", "Väinö Linna", 1954, 15, 2));
		
			log.info("fetch all books from the database");
			for (Book book : bookRepository.findAll()) {
				System.out.println("kirja: " + book.toString());
			}
		};
		
	}
}
		
	
