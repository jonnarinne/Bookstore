package harjoitus.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import harjoitus.bookstore.domain.Book;
import harjoitus.bookstore.domain.BookRepository;
import harjoitus.bookstore.domain.CategoryRepository;


@Controller
public class BookController {

	@Autowired
	private CategoryRepository crepository; 

	@Autowired
	private BookRepository bookRepository;

	private static final Logger log = LoggerFactory.getLogger(BookController.class);


	 @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }


	// Näytetään kaikki kirjat
    @GetMapping("/booklist")
	public String showBooks(Model model) {
		log.info("Read books from database..");
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}

	// Lisätään kirja
	@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/newbook")
	public String addBook(Model model) {
		log.info("Lets go to create a book....");
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	// Tallennetaan uusi tai muokattu kirja
	@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveBook")
	public String saveBook(@ModelAttribute("book") Book book) {
		bookRepository.save(book);
		return "redirect:booklist";
	}

	// Poistetaan kirja
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("delete/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		log.info("delete book " + id);
		bookRepository.deleteById(id);
		return "redirect:/booklist";
	}

	// Muokataan kirjaa
	@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("editbook/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("editbook", bookRepository.findById(id));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}
    
}
