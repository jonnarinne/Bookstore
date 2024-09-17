package harjoitus.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import harjoitus.bookstore.domain.Category;
import harjoitus.bookstore.domain.CategoryRepository;
import harjoitus.bookstore.domain.Book;
import harjoitus.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
		public CommandLineRunner demoData(BookRepository bookRepository, CategoryRepository crepository) {
			return (args) -> {
				log.info("save some books");

				Category category1 = new Category("Horror");
				Category category2 = new Category("Historical");
				Category category3 = new Category("Poetry");
			
				crepository.save(category1);
				crepository.save(category2);
				crepository.save(category3);

				bookRepository.save(new Book("Humiseva harju", "Emily Bronte", 1847, 1234, 8));
				bookRepository.save(new Book("Seitsemän veljestä", "Aleksis Kivi", 1873, 4321, 6));
				bookRepository.save(new Book("Harry Potter ja Viisasten kivi", "J.K. Rowling", 1997, 5511, 7));
				bookRepository.save(new Book("Margarita", "Anni Kytömäki", 2020, 1122, 10));


				log.info("tulostetaan kirjat");
				for (Book book : bookRepository.findAll()) {
					log.info(book.toString());
				}

			};
		}

	}
