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

    // Testi uuden kirjan luomiselle
    @Test
    public void createNewBook() {
        // Luodaan uusi kategoria ja tallennetaan se tietokantaan
        Category category = new Category("Scifi");
        crepository.save(category);

        // Luodaan uusi kirja ja asetetaan kategoria
        Book book = new Book("Kirja", "Kirjailija", 2024, 1111, 30);
        book.setCategory(category); // Asetetaan kategoria
        bookRepository.save(book);

        // Varmistetaan, että kirja sai tietokantaan tallennuksen jälkeen ID:n
        assertThat(book.getId()).isNotNull();
    }

    // Testi olemassa olevan kirjan kategorian lisäämiselle
    @Test
    public void addCategoryToExistingBook() {
        // Luodaan uusi kategoria ja tallennetaan se tietokantaan
        Category category = new Category("Fantasy");
        crepository.save(category);

        // Oletetaan, että tietokannassa on jo olemassa kirja ilman kategoriaa
        Book existingBook = new Book("Harry Potter", "J.K. Rowling", 1997, 12345, 20);
        bookRepository.save(existingBook); // Tallennetaan kirja ilman kategoriaa

        // Lisätään kategoria olemassa olevalle kirjalle
        existingBook.setCategory(category);
        bookRepository.save(existingBook); // Päivitetään kirja

        // Haetaan kirja ja varmistetaan, että kategoria on lisätty
        Book updatedBook = bookRepository.findById(existingBook.getId()).orElse(null);
        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getCategory()).isEqualTo(category); // Varmistetaan, että kategoria on sama
    }

    // Testi kirjan poistamiselle
    @Test
    public void deleteBook() {
        // Luodaan kategoria ja lisätään "Harry Potter" -kirja testitietokantaan
        Category category = new Category("Fantasy");
        crepository.save(category);
        
        // Luodaan "Harry Potter" -kirja ja asetetaan kategoria
        Book book = new Book("Harry Potter", "J.K. Rowling", 1997, 12345, 20);
        book.setCategory(category); // Asetetaan kategoria
        bookRepository.save(book);

        // Haetaan kirja otsikon perusteella (tämän pitäisi palauttaa lista)
        List<Book> books = bookRepository.findByTitle("Harry Potter");

        // Tarkistetaan, että ainakin yksi kirja löytyi ennen kuin jatketaan
        assertThat(books).isNotEmpty();

        // Otetaan ensimmäinen löytynyt kirja
        Book bookToDelete = books.get(0);

        // Poistetaan kirja tietokannasta
        bookRepository.delete(bookToDelete);

        // Haetaan kirja uudelleen ja varmistetaan, että sitä ei enää ole tietokannassa
        List<Book> newBooks = bookRepository.findByTitle("Harry Potter");
        assertThat(newBooks).hasSize(0);
    }
}
