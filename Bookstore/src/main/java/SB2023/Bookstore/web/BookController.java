package SB2023.Bookstore.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BookController {
	
	@@GetMapping("/index")
	public String addBooks(@RequestParam(name="title") String title,
			@RequestParam(name="author") String author,
			@RequestParam(name="publicationYear") int publicationYear,
			@RequestParam(name="isbn") String isbn,
			@RequestParam(name="author") int price,
			)
		  //return
	}
