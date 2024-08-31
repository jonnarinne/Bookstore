package harjoitus.bookstore.web;

import org.springframework.web.bind.annotation.GetMapping;

public class BookController {

    @GetMapping("/index")
    public String returnMessage() {
        return "Bookstore";
    }
    
}
