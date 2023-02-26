package SB2023.Bookstore.domain;

import org.springframework.data.repository.CrudRepository;

// Book on entiteetti

public interface BookRepository extends CrudRepository <Book, Long> {

}
