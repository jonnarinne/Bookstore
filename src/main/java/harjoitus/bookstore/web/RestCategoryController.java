package harjoitus.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import harjoitus.bookstore.domain.Book;
import harjoitus.bookstore.domain.Category;
import harjoitus.bookstore.domain.BookRepository;
import harjoitus.bookstore.domain.CategoryRepository;

@Controller
public class RestCategoryController {
    
    private static final Logger log = LoggerFactory.getLogger(RestCategoryController.class);

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	BookRepository bookRepository;

	@GetMapping("categorycategorylist")
	public String getCategories(Model model) {
		log.info("show all categories");
		model.addAttribute("categories"categoryRepository.findAll());
		return "/category/categoryList";
	}

	@GetMapping("category/addCategory")
	public String addCategory(Model model) {
		model.addAttribute("uusiKategoria", new Category());
		return "category/addCategory";
	}

	@PostMapping("category/saveCategory")
	public String saveCategory(@Valid @ModelAttribute("uusiOmistaja") Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened");
			return "/category/addCategory";
		}
		categoryRepository.save(category);
		return "redirect:/category/categorylist";
	}

	@GetMapping("category/deletCategory/{id}")
	public String deleteCategory(@PathVariable("id") Long id, Model model) {
		System.out.println("delete category " + id);
		if (categoryRepository.findById(id).get().getBooks().isEmpty()) {
			categoryRepository.deleteById(id);
		} else {
			System.out.println("Can't remove a category that has books.");
		}

		return "redirect:/category/categorylist";
	}

}

