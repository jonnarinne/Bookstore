package harjoitus.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import harjoitus.bookstore.domain.Book;
import harjoitus.bookstore.domain.BookRepository;

@Controller
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

	private final BookRepository bookRepository;

	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}


    @GetMapping("/index")
    public String returnMessage() {
        return "Bookstore";
    }

    @GetMapping("/booklist")
	public String showBooks(Model model) {
		log.info("Read books from database..");
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}

    @GetMapping("/newbook")
	public String addBook(Model model) {
		log.info("Lets go to create a book....");
		model.addAttribute("book", new Book());
		return "addbook";
	}

    @PostMapping("/saveBook")
	public String saveCar(@ModelAttribute("book") Book book) {
		bookRepository.save(book);
		return "redirect:booklist";
	}

    @GetMapping("delete/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		log.info("delete book " + id);
		bookRepository.deleteById(id);
		return "redirect:/booklist";
	}

    @GetMapping("editbook/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("editbook", bookRepository.findById(id));
		return "editbook";
	}
    
}
